package de.bio.hazard.securemessage.client.service;

import java.io.IOException;
import java.util.List;

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
			AsymmetricKeygen pAsymmetricKeygen) throws IOException {
		authenticationService = pAuthenticationService;
		basisInfoService = pBasisInfoService;
		asymmetricKeygen = pAsymmetricKeygen;
		userService = pUserService;
		messageService = pMessageService;
		keyPairMessaging = asymmetricKeygen.getKey(1024);
		keyPairDevice = asymmetricKeygen.getKey(1024);

		System.err.println("START GLOBAL");
		
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
		    messageService.sendMessage(lcMessage, getMessageKeyHelper(lcTokenId, keyPairMessaging.getPrivateKey()));
		}
		catch (MessageExceptionBiohazard e) {
		    e.printStackTrace();
		}
		System.err.println("End send Message");		
		
		try {
		    completeMessageTest();
		}
		catch (MessageExceptionBiohazard e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	
	public void completeMessageTest() throws MessageExceptionBiohazard, IOException {
		Logger lcLogger = LoggerFactory.getLogger(this.getClass());
	    byte[] lcServerPublicKey = basisInfoService.getServerPublicKey();

		// --------------TESTUSER---------------------------------------

		AsymmetricKey lcKeyPairUser1 = asymmetricKeygen.getKey(1024);
		AsymmetricKey lcKeyPairUser1Device = asymmetricKeygen.getKey(1024);
		NewUserWebservice lcUser1 = new NewUserWebservice();
		lcUser1.setEmail("User1@Messaging.de");
		lcUser1.setTelephone("00491234");
		lcUser1.setName("MessagingUser1");
		lcUser1.setPassword("qwert");
		lcUser1.setPrename("User1");
		lcUser1.setUsername("TestMessagingUser1");
		lcUser1.setPublicKeyForMessaging(lcKeyPairUser1.getPublicKey());
		NewUserKeyHelper lcUserKeyHelper1 = new NewUserKeyHelper();
		lcUserKeyHelper1.setDevicePrivateKey(lcKeyPairUser1Device.getPrivateKey());
		lcUserKeyHelper1.setPublicKeyForMessaging(lcKeyPairUser1.getPublicKey());
		lcUserKeyHelper1.setServerPublicKey(lcServerPublicKey);

		AsymmetricKey lcKeyPairUser2 = asymmetricKeygen.getKey(1024);
		AsymmetricKey lcKeyPairUser2Device = asymmetricKeygen.getKey(1024);
		NewUserWebservice lcUser2 = new NewUserWebservice();
		lcUser2.setEmail("User2@Messaging.de");
		lcUser2.setTelephone("004912345");
		lcUser2.setName("MessagingUser2");
		lcUser2.setPassword("moep");
		lcUser2.setPrename("User2");
		lcUser2.setUsername("TestMessagingUser2");
		lcUser2.setPublicKeyForMessaging(lcKeyPairUser2.getPublicKey());
		NewUserKeyHelper lcUserKeyHelper2 = new NewUserKeyHelper();
		lcUserKeyHelper2.setDevicePrivateKey(lcKeyPairUser2Device.getPrivateKey());
		lcUserKeyHelper2.setPublicKeyForMessaging(lcKeyPairUser2.getPublicKey());
		lcUserKeyHelper2.setServerPublicKey(lcServerPublicKey);

		AsymmetricKey lcKeyPairUser3 = asymmetricKeygen.getKey(1024);
		AsymmetricKey lcKeyPairUser3Device = asymmetricKeygen.getKey(1024);
		NewUserWebservice lcUser3 = new NewUserWebservice();
		lcUser3.setEmail("User3@Messaging.de");
		lcUser3.setTelephone("00491212");
		lcUser3.setName("MessagingUser3");
		lcUser3.setPassword("myPassword");
		lcUser3.setPrename("User3");
		lcUser3.setUsername("TestMessagingUser3");
		lcUser3.setPublicKeyForMessaging(lcKeyPairUser3.getPublicKey());
		NewUserKeyHelper lcUserKeyHelper3 = new NewUserKeyHelper();
		lcUserKeyHelper3.setDevicePrivateKey(lcKeyPairUser3Device.getPrivateKey());
		lcUserKeyHelper3.setPublicKeyForMessaging(lcKeyPairUser3.getPublicKey());
		lcUserKeyHelper3.setServerPublicKey(lcServerPublicKey);

		authenticationService.addNewUser(lcUser1, lcUserKeyHelper1);
		authenticationService.addNewUser(lcUser2, lcUserKeyHelper2);
		authenticationService.addNewUser(lcUser3, lcUserKeyHelper3);

		// --------------DEVICE---------------------------------------

		NewDeviceKeyHelper lcUserKeyHelper1Device = new NewDeviceKeyHelper();
		lcUserKeyHelper1Device.setDevicePrivateKey(lcKeyPairUser1Device.getPrivateKey());
		lcUserKeyHelper1Device.setDevicePublicKey(lcKeyPairUser1Device.getPublicKey());
		lcUserKeyHelper1Device.setServerPublicKey(lcServerPublicKey);

		NewDeviceWebservice lcNewDevice1 = new NewDeviceWebservice();
		lcNewDevice1.setPassword(lcUser1.getPassword());
		lcNewDevice1.setUsername(lcUser1.getUsername());
		lcNewDevice1.setDevicename("myDeviceUser1");
		lcNewDevice1.setPublicKeyForDevice(lcUserKeyHelper1Device.getDevicePublicKey());

		NewDeviceWebserviceReturn lcDeviceReturn1 = authenticationService.addNewDevice(lcNewDevice1, lcUserKeyHelper1Device);

		NewDeviceKeyHelper lcUserKeyHelper3Device = new NewDeviceKeyHelper();
		lcUserKeyHelper3Device.setDevicePrivateKey(lcKeyPairUser1Device.getPrivateKey());
		lcUserKeyHelper3Device.setDevicePublicKey(lcKeyPairUser1Device.getPublicKey());
		lcUserKeyHelper3Device.setServerPublicKey(lcServerPublicKey);

		NewDeviceWebservice lcNewDevice3 = new NewDeviceWebservice();
		lcNewDevice3.setPassword(lcUser3.getPassword());
		lcNewDevice3.setUsername(lcUser3.getUsername());
		lcNewDevice3.setDevicename("myDeviceUser3");
		lcNewDevice3.setPublicKeyForDevice(lcUserKeyHelper1Device.getDevicePublicKey());

		NewDeviceWebserviceReturn lcDeviceReturn3 = authenticationService.addNewDevice(lcNewDevice3, lcUserKeyHelper3Device);

		// --------------ANMELDUNG---------------------------------------

		String lcTokenIdDevice1 = authenticationService.authenticateAndObtainAuthToken(lcDeviceReturn1.getDeviceId(), lcUser1.getUsername(), lcUser1.getPassword(),
			lcUserKeyHelper1Device.getDevicePrivateKey());
		System.err.println("TokenId Device1: " + lcTokenIdDevice1);

		String lcTokenIdDevice3 = authenticationService.authenticateAndObtainAuthToken(lcDeviceReturn3.getDeviceId(), lcUser3.getUsername(), lcUser3.getPassword(),
			lcUserKeyHelper3Device.getDevicePrivateKey());
		System.err.println("TokenId Device3: " + lcTokenIdDevice3);

		// --------------MESSAGE SENDEN---------------------------------------

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
		lcMessageReceiver.setUsername(lcUser2.getUsername());
		lcMessage.getReceiver().add(lcMessageReceiver);

		lcMessageReceiver = new MessageReceiver();
		lcMessageReceiver.setMessageReceiverType(MessageReceiverType.CC);
		lcMessageReceiver.setUsername(lcUser3.getUsername());
		lcMessage.getReceiver().add(lcMessageReceiver);

		CommunicationKeyHelper lcCommunicationKeyHelperUser1 = new CommunicationKeyHelper();
		lcCommunicationKeyHelperUser1.setDevicePrivateKey(lcKeyPairUser1Device.getPrivateKey());
		lcCommunicationKeyHelperUser1.setServerPublicKey(lcServerPublicKey);
		lcCommunicationKeyHelperUser1.setTokenId(lcTokenIdDevice1);
		lcCommunicationKeyHelperUser1.setUserPrivateKey(lcKeyPairUser1.getPrivateKey());

		messageService.sendMessage(lcMessage, lcCommunicationKeyHelperUser1);

		// --------------MESSAGE EMPFANGEN---------------------------------------
		lcLogger.error("Start receiving messages");


		CommunicationKeyHelper lcCommunicationKeyHelperUser3 = new CommunicationKeyHelper();
		lcCommunicationKeyHelperUser3.setDevicePrivateKey(lcKeyPairUser3Device.getPrivateKey());
		lcCommunicationKeyHelperUser3.setServerPublicKey(lcServerPublicKey);
		lcCommunicationKeyHelperUser3.setTokenId(lcTokenIdDevice3);
		lcCommunicationKeyHelperUser3.setUserPrivateKey(lcKeyPairUser3.getPrivateKey());
		
		List<Message> lcReturn = messageService.getMessages(lcCommunicationKeyHelperUser3);
		
		for(Message lcMessageReturn : lcReturn) {
		    for(MessageContent lcMessageContentReturn : lcMessageReturn.getContent()) {
		    	lcLogger.error("Messagedata : " +lcMessageContentReturn.getData());
		    }
		}
		
		lcLogger.error("Stop receiving messages");

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

	private CommunicationKeyHelper getMessageKeyHelper(String pTokenId, byte[] pUserPrivateKey) {
	    CommunicationKeyHelper lcKeyHelper = new CommunicationKeyHelper();
	    lcKeyHelper.setTokenId(pTokenId);
	    lcKeyHelper.setDevicePrivateKey(keyPairDevice.getPrivateKey());
	    lcKeyHelper.setServerPublicKey(serverPublicKey);
	    lcKeyHelper.setUserPrivateKey(pUserPrivateKey);
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
