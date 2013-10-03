package de.bio.hazard.securemessage.client.service;

import java.security.KeyPair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.helper.AuthenticationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.NewDeviceKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.NewUserKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebservice;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebserviceReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewUserWebservice;
import de.bio.hazard.securemessage.tecframework.encryption.asymmetric.AsymmetricKeygen;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;

@Service
public class Global {

	private AuthenticationService authenticationService;
	private BasisInfoService basisInfoService;
	private AsymmetricKeygen asymmetricKeygen;

	private KeyPair keyPairMessaging;
	private KeyPair keyPairDevice;

	byte[] serverPublicKey;

	Logger logger = LoggerFactory.getLogger(Global.class);

	@Autowired
	public Global(BasisInfoService pBasisInfoService,
			AuthenticationService pAuthenticationService,
			AsymmetricKeygen pAsymmetricKeygen) {
		authenticationService = pAuthenticationService;
		basisInfoService = pBasisInfoService;
		asymmetricKeygen = pAsymmetricKeygen;
		keyPairMessaging = asymmetricKeygen.getKeyPair(1024);
		keyPairDevice = asymmetricKeygen.getKeyPair(1024);

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
						keyPairDevice.getPrivate().getEncoded());
		System.err.println("TokenId: " + lcTokenId);
	}

	private NewDeviceKeyHelper getNewDeviceKeyHelper() {
		NewDeviceKeyHelper lcKeyHelper = new NewDeviceKeyHelper();
		lcKeyHelper
				.setDevicePrivateKey(keyPairDevice.getPrivate().getEncoded());
		lcKeyHelper.setDevicePublicKey(keyPairDevice.getPublic().getEncoded());
		lcKeyHelper.setServerPublicKey(serverPublicKey);
		return lcKeyHelper;
	}

	private NewUserKeyHelper getNewUserKeyHelper() {
		NewUserKeyHelper lcKeyHelper = new NewUserKeyHelper();
		lcKeyHelper
				.setDevicePrivateKey(keyPairDevice.getPrivate().getEncoded());
		lcKeyHelper.setPublicKeyForMessaging(keyPairMessaging.getPublic()
				.getEncoded());
		lcKeyHelper.setServerPublicKey(serverPublicKey);
		return lcKeyHelper;
	}

	private AuthenticationKeyHelper getAuthenticationKeyHelper() {
		AuthenticationKeyHelper lcKeyHelper = new AuthenticationKeyHelper();
		lcKeyHelper
				.setDevicePrivateKey(keyPairDevice.getPrivate().getEncoded());
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
