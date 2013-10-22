package de.bio.hazard.securemessage.client.servicefacade;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import de.bio.hazard.securemessage.webservice.message.EncryptionExceptionBiohazard_Exception;
import de.bio.hazard.securemessage.webservice.message.MessageContentKeyWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageContentWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageReceiverWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebservice;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceService;
import de.bio.hazard.securemessage.webservice.message.RequestMessageWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.RequestMessageWebserviceReturnDTO;

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

    public List<Message> getMessages(CommunicationKeyHelper pCommunicationKey) throws EncryptionExceptionBiohazard {
	RequestMessageWebserviceDTO lcRequestMessageWebserviceDTO = encryptRequestMessageWebserviceDTO(pCommunicationKey);
	List<Message> lcReturn = new ArrayList<Message>();
	try {
	    RequestMessageWebserviceReturnDTO lcRequestMessageWebserviceReturnDTO = getMessageWSPort().getMessages(lcRequestMessageWebserviceDTO);
	    lcRequestMessageWebserviceReturnDTO = decryptRequestMessageWebserviceReturnDTO(lcRequestMessageWebserviceReturnDTO, pCommunicationKey);
	    lcReturn = transformRequestMessageReturnDTOToMessagelist(lcRequestMessageWebserviceReturnDTO, pCommunicationKey);
	}
	catch (EncryptionExceptionBiohazard_Exception e) {
	    e.printStackTrace();
	    // TODO SebastianS; Logging
	    throw new EncryptionExceptionBiohazard();
	}
	return lcReturn;
    }

    private RequestMessageWebserviceReturnDTO decryptRequestMessageWebserviceReturnDTO(RequestMessageWebserviceReturnDTO pRequestMessageWebserviceReturnDTO, CommunicationKeyHelper pCommunicationKey)
	    throws EncryptionExceptionBiohazard {
	RequestMessageWebserviceReturnDTO lcRequestMessageWebserviceReturnDTO = pRequestMessageWebserviceReturnDTO;
	try {
	    //TODO NicoH; DEBUG!
	    System.err.println("encryptedKey: "+lcRequestMessageWebserviceReturnDTO.getSymEncryptionKey());
	    byte[] lcSymmetricKey = encryptionObjectModifier.asymmetricDecryptToByte(lcRequestMessageWebserviceReturnDTO.getSymEncryptionKey(), pCommunicationKey.getDevicePrivateKey(), true);

	    for (MessageWebserviceDTO lcMessage : lcRequestMessageWebserviceReturnDTO.getMessages()) {
		for (MessageContentWebserviceDTO lcMessageContent : lcMessage.getContent()) {
		    lcMessageContent.setData(encryptionObjectModifier.symmetricDecrypt(lcMessageContent.getData(), lcSymmetricKey));
		    lcMessageContent.setFilename(encryptionObjectModifier.symmetricDecrypt(lcMessageContent.getFilename(), lcSymmetricKey));
		    for (MessageContentKeyWebserviceDTO lcMessageContentKey : lcMessageContent.getSymmetricKeys()) {
			lcMessageContentKey.setSymmetricEncryptionKey(encryptionObjectModifier.symmetricDecrypt(lcMessageContentKey.getSymmetricEncryptionKey(), lcSymmetricKey));
		    }
		}
	    }
	}
	catch (Exception e) {
	    // TODO SebastianS; Logging
	    e.printStackTrace();
	    throw new EncryptionExceptionBiohazard();
	}
	return lcRequestMessageWebserviceReturnDTO;
    }

    private RequestMessageWebserviceDTO encryptRequestMessageWebserviceDTO(CommunicationKeyHelper pCommunicationKey) throws EncryptionExceptionBiohazard {
	RequestMessageWebserviceDTO lcRequestMessageWebserviceDTO = new RequestMessageWebserviceDTO();
	try {
	    byte[] lcSymmetricKey = symmetricKeygen.getKey(128);
	    lcRequestMessageWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(lcSymmetricKey, pCommunicationKey.getServerPublicKey(), false));
	    lcRequestMessageWebserviceDTO.setTokenId(encryptionObjectModifier.symmetricEncrypt(pCommunicationKey.getTokenId(), lcSymmetricKey));
	}
	catch (Exception e) {
	    // TODO SebastianS; Logging
	    e.printStackTrace();
	    throw new EncryptionExceptionBiohazard();
	}
	return lcRequestMessageWebserviceDTO;

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

    private List<Message> transformRequestMessageReturnDTOToMessagelist(RequestMessageWebserviceReturnDTO pRequestMessageWebserviceReturnDTO, CommunicationKeyHelper pCommunicationKey)
	    throws EncryptionExceptionBiohazard {
	List<Message> lcReturn = new ArrayList<Message>();
	try {
	    for (MessageWebserviceDTO lcMessageDTO : pRequestMessageWebserviceReturnDTO.getMessages()) {
		Message lcMessage = new Message();
		// TODO Receiver einbauen (wenn serverseitig implementiert)
		for (MessageContentWebserviceDTO lcContentDTO : lcMessageDTO.getContent()) {
		    MessageContent lcContent = transformDTOToMessageContent(lcContentDTO, pCommunicationKey);
		    lcMessage.getContent().add(lcContent);
		}
	    }
	}
	catch (Exception e) {
	    // TODO SebastianS; Logging
	    e.printStackTrace();
	    throw new EncryptionExceptionBiohazard();
	}
	return lcReturn;
    }

    private MessageContent transformDTOToMessageContent(MessageContentWebserviceDTO pContentDTO, CommunicationKeyHelper pCommunicationKey) throws IOException, InvalidKeyException,
	    IllegalBlockSizeException, BadPaddingException {
	MessageContent lcMessageContent = new MessageContent();
	byte[] lcSymmetricKey = encryptionObjectModifier.asymmetricDecryptToByte(pContentDTO.getSymmetricKeys().get(0).getSymmetricEncryptionKey(), pCommunicationKey.getUserPrivateKey(), true);
	lcMessageContent.setData(encryptionObjectModifier.symmetricDecryptToByte(pContentDTO.getData(), lcSymmetricKey));
	lcMessageContent.setFilename(encryptionObjectModifier.symmetricDecrypt(pContentDTO.getData(), lcSymmetricKey));
	return lcMessageContent;
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
