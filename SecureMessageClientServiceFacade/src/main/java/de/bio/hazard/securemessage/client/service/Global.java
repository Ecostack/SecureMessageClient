package de.bio.hazard.securemessage.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.helper.AuthenticationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.CommunicationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.NewDeviceKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.NewUserKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebservice;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebserviceReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewUserWebservice;
import de.bio.hazard.securemessage.client.servicefacade.model.message.Message;
import de.bio.hazard.securemessage.client.servicefacade.model.message.MessageContent;
import de.bio.hazard.securemessage.client.servicefacade.model.message.MessageReceiver;
import de.bio.hazard.securemessage.client.servicefacade.model.message.helper.MessageReceiverType;
import de.bio.hazard.securemessage.tecframework.encryption.asymmetric.AsymmetricKey;
import de.bio.hazard.securemessage.tecframework.encryption.asymmetric.AsymmetricKeygen;
import de.bio.hazard.securemessage.tecframework.exception.MessageExceptionBiohazard;

@Service
public class Global {

	private AuthenticationService authenticationService;
	private BasisInfoService basisInfoService;
	private UserService userService;
	private MessageService messageService;
	private AsymmetricKeygen asymmetricKeygen;

	private AsymmetricKey keyPairMessaging;
	private AsymmetricKey keyPairDevice;

	byte[] serverPublicKey;

	Logger logger = LoggerFactory.getLogger(Global.class);

	@Autowired
	public Global(BasisInfoService pBasisInfoService,
			AuthenticationService pAuthenticationService,
			UserService pUserService,
			MessageService pMessageService,
			AsymmetricKeygen pAsymmetricKeygen) {
		authenticationService = pAuthenticationService;
		basisInfoService = pBasisInfoService;
		asymmetricKeygen = pAsymmetricKeygen;
		userService = pUserService;
		messageService = pMessageService;
		keyPairMessaging = asymmetricKeygen.getKey(1024);
		keyPairDevice = asymmetricKeygen.getKey(1024);

		serverPublicKey = basisInfoService.getServerPublicKey();
		logger.debug("obtained public key of server");

		// Now Start doing something!!!

		NewUserWebservice lcUser = createNewUser();
		NewDeviceWebservice lcDevice = createNewDevice();

		authenticationService.addNewUser(lcUser, getNewUserKeyHelper());

		NewDeviceWebserviceReturn lcReturn = authenticationService
				.addNewDevice(lcDevice, getNewDeviceKeyHelper());

		String lcTokenId = authenticationService
				.authenticateAndObtainAuthToken(lcReturn.getDeviceId(),
						lcUser.getUsername(), lcUser.getPassword(),
						keyPairDevice.getPrivateKey());
		System.err.println("TokenId: " + lcTokenId);
		
		System.err.println("Start send Message");
		Message lcMessage = createMessage();
		
		try {
		    messageService.sendMessage(lcMessage, getMessageKeyHelper(lcTokenId));
		}
		catch (MessageExceptionBiohazard e) {
		    e.printStackTrace();
		}
		System.err.println("End send Message");
		
	}

	private Message createMessage() {
	    Message lcMessage = new Message();
	    
	    MessageContent lcMessageContent = new MessageContent();
	    lcMessageContent.setData("Hallo Welt".getBytes());
	    lcMessageContent.setFilename("");
	    lcMessage.getContent().add(lcMessageContent);
	    
	    lcMessageContent = new MessageContent();
	    lcMessageContent.setData("Text 1234".getBytes());
	    lcMessageContent.setFilename("");
	    lcMessage.getContent().add(lcMessageContent);
	    
	    MessageReceiver lcMessageReceiver = new MessageReceiver();
	    lcMessageReceiver.setMessageReceiverType(MessageReceiverType.TO);
	    lcMessageReceiver.setUsername("test");
	    lcMessage.getReceiver().add(lcMessageReceiver);
	    
	    lcMessageReceiver = new MessageReceiver();
	    lcMessageReceiver.setMessageReceiverType(MessageReceiverType.CC);
	    lcMessageReceiver.setUsername("Admin");
	    lcMessage.getReceiver().add(lcMessageReceiver);
	    
	    return lcMessage;
	}

	private CommunicationKeyHelper getMessageKeyHelper(String pTokenId) {
	    CommunicationKeyHelper lcKeyHelper = new CommunicationKeyHelper();
	    lcKeyHelper.setTokenId(pTokenId);
	    lcKeyHelper.setDevicePrivateKey(keyPairDevice.getPrivateKey());
	    lcKeyHelper.setServerPublicKey(serverPublicKey);
	    return lcKeyHelper;
	}

	private NewDeviceKeyHelper getNewDeviceKeyHelper() {
		NewDeviceKeyHelper lcKeyHelper = new NewDeviceKeyHelper();
		lcKeyHelper
				.setDevicePrivateKey(keyPairDevice.getPrivateKey());
		lcKeyHelper.setDevicePublicKey(keyPairDevice.getPublicKey());
		lcKeyHelper.setServerPublicKey(serverPublicKey);
		return lcKeyHelper;
	}

	private NewUserKeyHelper getNewUserKeyHelper() {
		NewUserKeyHelper lcKeyHelper = new NewUserKeyHelper();
		lcKeyHelper
				.setDevicePrivateKey(keyPairDevice.getPrivateKey());
		lcKeyHelper.setPublicKeyForMessaging(keyPairMessaging.getPublicKey());
		lcKeyHelper.setServerPublicKey(serverPublicKey);
		return lcKeyHelper;
	}

	private AuthenticationKeyHelper getAuthenticationKeyHelper() {
		AuthenticationKeyHelper lcKeyHelper = new AuthenticationKeyHelper();
		lcKeyHelper.setDevicePrivateKey(keyPairDevice.getPrivateKey());
		lcKeyHelper.setServerPublicKey(serverPublicKey);
		return lcKeyHelper;
	}

	private NewDeviceWebservice createNewDevice() {
		NewDeviceWebservice lcNewDevice = new NewDeviceWebservice();
		lcNewDevice.setPassword(createNewUser().getPassword());
		lcNewDevice.setUsername(createNewUser().getUsername());
		lcNewDevice.setDevicename("myDevice");
		lcNewDevice.setPublicKeyForDevice(getNewDeviceKeyHelper()
				.getDevicePublicKey());

		return lcNewDevice;
	}

	private NewUserWebservice createNewUser() {
		NewUserWebservice lcNewUser = new NewUserWebservice();
		lcNewUser.setEmail("MyMail@mailing.ru");
		lcNewUser.setTelephone("00");
		lcNewUser.setName("rofl");
		lcNewUser.setPassword("mypw");
		lcNewUser.setPrename("rufold");
		lcNewUser.setUsername("myusername");
		lcNewUser.setPublicKeyForMessaging(getNewUserKeyHelper()
				.getPublicKeyForMessaging());

		return lcNewUser;
	}

}
