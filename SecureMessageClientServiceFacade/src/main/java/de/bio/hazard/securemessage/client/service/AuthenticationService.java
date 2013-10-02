package de.bio.hazard.securemessage.client.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepOne;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepOneReturn;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepTwo;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepTwoReturn;
import de.bio.hazard.securemessage.client.servicefacade.AuthenticationServiceFacade;
import de.bio.hazard.securemessage.tecframework.data.validation.DateUtils;
import de.bio.hazard.securemessage.tecframework.exception.AuthenticationExceptionBiohazard;

@Service
public class AuthenticationService {

	@Autowired
	AuthenticationServiceFacade authenticationServiceFacade;

	public String authenticateMe() throws AuthenticationExceptionBiohazard {
		try {
			String lcDeviceId = "";
			String lcUsername = "";
			String lcPassword = "";

			AuthenticationStepOne lcStepOne = createStepOne(lcDeviceId,
					lcUsername, lcPassword);

			AuthenticationStepOneReturn lcStepOneReturn = authenticationServiceFacade
					.authenticateStepOne(lcStepOne);
			
			AuthenticationStepTwo lcStepTwo = createStepTwo(lcStepOneReturn);
			
			AuthenticationStepTwoReturn lcStepTwoReturn = authenticationServiceFacade.authenticateStepTwo(lcStepTwo);
			
			return lcStepTwoReturn.getTokenId();
		} catch (Exception e) {
			throw new AuthenticationExceptionBiohazard(e);
		}
	}
	
	private AuthenticationStepTwo createStepTwo(AuthenticationStepOneReturn pStepTwoReturn) throws ParseException {
		AuthenticationStepTwo lcResult = new AuthenticationStepTwo();
		
		Date lcDate = new Date();
		lcResult.setDate(DateUtils
				.transformDateMaxFormToString(lcDate));
		lcResult.setHandshakeId(pStepTwoReturn.getHandshakeId());
		lcResult.setRandomHashedValue(pStepTwoReturn.getRandomHashedValue());
		return lcResult;
	}

	private AuthenticationStepOne createStepOne(String pDeviceId,
			String pUsername, String pPassword) throws ParseException {
		AuthenticationStepOne lcResult = new AuthenticationStepOne();

		Date lcDate = new Date();
		lcResult.setDate(DateUtils
				.transformDateMaxFormToString(lcDate));
		lcResult.setDeviceId(pDeviceId);
		lcResult.setPassword(pPassword);
		lcResult.setUsername(pUsername);

		return lcResult;
	}
}
