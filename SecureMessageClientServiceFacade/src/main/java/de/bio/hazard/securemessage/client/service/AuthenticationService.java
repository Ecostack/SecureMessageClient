package de.bio.hazard.securemessage.client.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.AuthenticationServiceFacade;
import de.bio.hazard.securemessage.client.servicefacade.helper.AuthenticationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.helper.NewDeviceKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepOne;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepOneReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepTwo;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepTwoReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebservice;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewUserWebservice;
import de.bio.hazard.securemessage.tecframework.data.validation.DateUtils;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.exception.AuthenticationExceptionBiohazard;
import de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceDTO;
import de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceReturnDTO;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationServiceFacade authenticationServiceFacade;

	@Autowired
	private BasisInfoService basisInfoService;

	@Autowired
	private EncryptionObjectModifier encryptionObjectModifier;

	public void addNewDevice(NewDeviceWebservice pNewDeviceWebservice,
			NewDeviceKeyHelper pKeyHelper) {
//		NewDeviceWebservice lcDeviceWebservice = new NewDeviceWebservice();
//		lcDeviceWebservice.setDevicename(pDevicename);
//		lcDeviceWebservice.setUsername(pUsername);
//		lcDeviceWebservice.setPassword(pPassword);
//		lcDeviceWebservice.setPublicKeyForDevice(encryptionObjectModifier
//				.encodeBase64(pDevicePublicKey));

		authenticationServiceFacade
				.addNewDevice(pNewDeviceWebservice, pKeyHelper);

	}

	public void addNewUser(NewUserWebservice pNewUserWebservice) {
//
//		authenticationServiceFacade
//				.addNewUser(pNewUserWebservice);

	}

	public String authenticateAndObtainAuthToken(String pDeiceId,
			String pUsername, String pPassword, byte[] pDevicePrivateKey)
			throws AuthenticationExceptionBiohazard {
		try {
			// String lcDeviceId = "";
			// String lcUsername = "";
			// String lcPassword = "";
			AuthenticationKeyHelper lcKeyHelper = createAuthenticationKeyHelper(pDevicePrivateKey);

			AuthenticationStepOne lcStepOne = createStepOne(pDeiceId,
					pUsername, pPassword);

			AuthenticationStepOneReturn lcStepOneReturn = authenticationServiceFacade
					.authenticateStepOne(lcStepOne, lcKeyHelper);

			AuthenticationStepTwo lcStepTwo = createStepTwo(lcStepOneReturn);

			AuthenticationStepTwoReturn lcStepTwoReturn = authenticationServiceFacade
					.authenticateStepTwo(lcStepTwo, lcKeyHelper);

			return lcStepTwoReturn.getTokenId();
		} catch (Exception e) {
			throw new AuthenticationExceptionBiohazard(e);
		}
	}

	private AuthenticationKeyHelper createAuthenticationKeyHelper(
			byte[] pDevicePrivateKey) {
		AuthenticationKeyHelper lcResult = new AuthenticationKeyHelper();
		lcResult.setDevicePrivateKey(pDevicePrivateKey);
		lcResult.setServerPublicKey(basisInfoService.getServerPublicKey());
		return lcResult;
	}

	private AuthenticationStepTwo createStepTwo(
			AuthenticationStepOneReturn pStepTwoReturn) throws ParseException {
		AuthenticationStepTwo lcResult = new AuthenticationStepTwo();

		Date lcDate = new Date();
		lcResult.setDate(DateUtils.transformDateMaxFormToString(lcDate));
		lcResult.setHandshakeId(pStepTwoReturn.getHandshakeId());
		lcResult.setRandomHashedValue(pStepTwoReturn.getRandomHashedValue());
		return lcResult;
	}

	private AuthenticationStepOne createStepOne(String pDeviceId,
			String pUsername, String pPassword) throws ParseException {
		AuthenticationStepOne lcResult = new AuthenticationStepOne();

		Date lcDate = new Date();
		lcResult.setDate(DateUtils.transformDateMaxFormToString(lcDate));
		lcResult.setDeviceId(pDeviceId);
		lcResult.setPassword(pPassword);
		lcResult.setUsername(pUsername);

		return lcResult;
	}
}
