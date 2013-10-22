package de.bio.hazard.securemessage.client.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.MessageServiceFacade;
import de.bio.hazard.securemessage.client.servicefacade.helper.CommunicationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.message.Message;
import de.bio.hazard.securemessage.client.servicefacade.model.message.MessageReceiver;
import de.bio.hazard.securemessage.tecframework.exception.EncryptionExceptionBiohazard;
import de.bio.hazard.securemessage.tecframework.exception.MessageExceptionBiohazard;

@Service
public class MessageService {

	@Autowired
	private MessageServiceFacade messageServiceFacade;

	@Autowired
	private BasisInfoService basisInfoService;

	@Autowired
	private UserService userService;

	public void sendMessage(Message pMessage,
			CommunicationKeyHelper pCommunicationKey)
			throws MessageExceptionBiohazard {
		HashMap<String, byte[]> lcUserPublicKeys = getUserPublicKeys(
				pMessage.getReceiver(), pCommunicationKey);
		try {
			messageServiceFacade.sendMessage(pMessage, lcUserPublicKeys,
					pCommunicationKey);
		} catch (EncryptionExceptionBiohazard exb) {
			exb.printStackTrace();
			// TODO SebastianS; Logging
			throw new MessageExceptionBiohazard();
		}
	}

	public List<Message> getMessages(CommunicationKeyHelper pCommunicationKey)
			throws MessageExceptionBiohazard {
		try {
			return messageServiceFacade.getMessages(pCommunicationKey);
		} catch (EncryptionExceptionBiohazard exb) {
			exb.printStackTrace();
			// TODO SebastianS; Logging
			throw new MessageExceptionBiohazard();
		}
	}

	private HashMap<String, byte[]> getUserPublicKeys(
			List<MessageReceiver> pReceiver,
			CommunicationKeyHelper pCommunicationKey) {
		HashMap<String, byte[]> lcUserPublicKeys = new HashMap<String, byte[]>();

		for (MessageReceiver lcUser : pReceiver) {
			byte[] lcUserPublicKey = userService.getServerPublicKey(
					lcUser.getUsername(), pCommunicationKey);
			lcUserPublicKeys.put(lcUser.getUsername(), lcUserPublicKey);
		}
		return lcUserPublicKeys;
	}

}
