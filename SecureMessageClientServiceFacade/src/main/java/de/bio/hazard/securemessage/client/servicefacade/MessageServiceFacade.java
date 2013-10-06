package de.bio.hazard.securemessage.client.servicefacade;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.helper.CommunicationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.message.Message;
import de.bio.hazard.securemessage.client.servicefacade.model.message.MessageContent;
import de.bio.hazard.securemessage.client.servicefacade.model.message.MessageReceiver;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.tecframework.exception.EncryptionExceptionBiohazard;
import de.bio.hazard.securemessage.webservice.message.MessageContentKeyWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageContentWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageReceiverWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebservice;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceService;

@Service
public class MessageServiceFacade {

    private MessageWebservice messageWSPort;
    private MessageWebserviceService messageWS;

    @Autowired
    private EncryptionObjectModifier encryptionObjectModifier;

    @Autowired
    private SymmetricKeygen symmetricKeygen;

    public void sendMessage(Message pMessage, HashMap<String, byte[]> pUserPublicKeys, CommunicationKeyHelper pMessageKey) throws EncryptionExceptionBiohazard {
	MessageWebserviceDTO lcMessageWebserviceDTO = transformMessageToDTO(pMessage, pUserPublicKeys);
	lcMessageWebserviceDTO = encryptMessageWebserviceDTO(lcMessageWebserviceDTO, pMessageKey);
	try {
	    getMessageWSPort().addMessage(lcMessageWebserviceDTO);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    // TODO SebastianS; Logging
	    throw new EncryptionExceptionBiohazard();
	}
    }

    private MessageWebserviceDTO encryptMessageWebserviceDTO(MessageWebserviceDTO pMessageWebserviceDTO, CommunicationKeyHelper pMessageKey) throws EncryptionExceptionBiohazard {
	MessageWebserviceDTO lcMessageWebserviceDTO = pMessageWebserviceDTO;
	try {
	    byte[] lcSymmetricKey = symmetricKeygen.getKey(128);
	    lcMessageWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(lcSymmetricKey, pMessageKey.getServerPublicKey(), false));
	    lcMessageWebserviceDTO.setTokenId(encryptionObjectModifier.symmetricEncrypt(pMessageKey.getTokenId(), lcSymmetricKey));
	    for (MessageContentWebserviceDTO mcwDTO : lcMessageWebserviceDTO.getContent()) {
		mcwDTO.setData(encryptionObjectModifier.symmetricEncrypt(mcwDTO.getData(), lcSymmetricKey));
		mcwDTO.setFilename(encryptionObjectModifier.symmetricEncrypt(mcwDTO.getFilename(), lcSymmetricKey));
		for (MessageContentKeyWebserviceDTO mckwDTO : mcwDTO.getSymmetricKeys()) {
		    mckwDTO.setUsername(encryptionObjectModifier.symmetricEncrypt(mckwDTO.getUsername(), lcSymmetricKey));
		    mckwDTO.setSymmetricEncryptionKey(encryptionObjectModifier.symmetricEncrypt(mckwDTO.getSymmetricEncryptionKey(), lcSymmetricKey));
		}
	    }
	}
	catch (Exception e) {
	    // TODO SebastianS; Logging
	    e.printStackTrace();
	    throw new EncryptionExceptionBiohazard();
	}
	return lcMessageWebserviceDTO;
    }

    private MessageWebserviceDTO transformMessageToDTO(Message pMessage, HashMap<String, byte[]> pUserPublicKeys) throws EncryptionExceptionBiohazard {
	MessageWebserviceDTO lcMessageWebserviceDTO = new MessageWebserviceDTO();
	try {
	    for (MessageReceiver lcReceiver : pMessage.getReceiver()) {
		MessageReceiverWebserviceDTO lcMessageReceiverDTO = new MessageReceiverWebserviceDTO();
		lcMessageReceiverDTO.setUsername(lcReceiver.getUsername());
		lcMessageReceiverDTO.setMessageReceiverType(lcReceiver.getMessageReceiverType().toString());
		lcMessageWebserviceDTO.getReceiver().add(lcMessageReceiverDTO);
	    }
	    for (MessageContent lcContent : pMessage.getContent()) {
		MessageContentWebserviceDTO lcMessageContentDTO = transformMessageContentToDTO(lcContent, pUserPublicKeys);
		lcMessageWebserviceDTO.getContent().add(lcMessageContentDTO);
	    }
	}
	catch (Exception e) {
	    // TODO SebastianS; Logging
	    e.printStackTrace();
	    throw new EncryptionExceptionBiohazard();
	}

	return lcMessageWebserviceDTO;
    }

    private MessageContentWebserviceDTO transformMessageContentToDTO(MessageContent lcContent, HashMap<String, byte[]> pUserPublicKeys) throws NoSuchAlgorithmException, NoSuchProviderException,
	    InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
	MessageContentWebserviceDTO lcMessageContentDTO = new MessageContentWebserviceDTO();
	byte[] lcSymmetricKey = symmetricKeygen.getKey(128);
	lcMessageContentDTO.setData(encryptionObjectModifier.symmetricEncrypt(lcContent.getData(), lcSymmetricKey));
	lcMessageContentDTO.setFilename(encryptionObjectModifier.symmetricEncrypt(lcContent.getFilename(), lcSymmetricKey));

	for (String lcContentReceiver : pUserPublicKeys.keySet()) {
	    MessageContentKeyWebserviceDTO lcMessageContentKeyWebserviceDTO = buildMessageContentKeyWebserviceDTO(pUserPublicKeys, lcContentReceiver, lcSymmetricKey);
	    lcMessageContentDTO.getSymmetricKeys().add(lcMessageContentKeyWebserviceDTO);
	}
	return lcMessageContentDTO;
    }

    private MessageContentKeyWebserviceDTO buildMessageContentKeyWebserviceDTO(HashMap<String, byte[]> pUserPublicKeys, String lcContentReceiver, byte[] lcSymmetricKey) throws IOException {
	MessageContentKeyWebserviceDTO lcMessageContentKeyWebserviceDTO = new MessageContentKeyWebserviceDTO();
	lcMessageContentKeyWebserviceDTO.setUsername(lcContentReceiver);
	lcMessageContentKeyWebserviceDTO.setSymmetricEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(lcSymmetricKey, pUserPublicKeys.get(lcContentReceiver), false));
	return lcMessageContentKeyWebserviceDTO;
    }

    private MessageWebservice getMessageWSPort() {
	if (messageWSPort == null) {
	    messageWSPort = getMessageWS().getMessageWebservicePort();
	}
	return messageWSPort;
    }

    private MessageWebserviceService getMessageWS() {
	if (messageWS == null) {
	    messageWS = new MessageWebserviceService();
	}
	return messageWS;
    }
}
