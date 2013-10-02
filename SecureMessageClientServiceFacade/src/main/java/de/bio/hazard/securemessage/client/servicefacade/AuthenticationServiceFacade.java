package de.bio.hazard.securemessage.client.servicefacade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepOne;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepOneReturn;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepTwo;
import de.bio.hazard.securemessage.client.model.authentication.AuthenticationStepTwoReturn;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepOneDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepOneReturnDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepTwoDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepTwoReturnDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebservice;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebserviceService;
import de.bio.hazard.securemessage.webservice.authentication.DeviceNotFoundException_Exception;
import de.bio.hazard.securemessage.webservice.authentication.EncryptionException_Exception;

@Service
public class AuthenticationServiceFacade {

	private AuthenticationWebservice authWSPort;
	private AuthenticationWebserviceService authWS;

	@Autowired
	private EncryptionObjectModifier encryptionObjectModifier;

	public AuthenticationStepOneReturn authenticateStepOne(
			AuthenticationStepOne lcStepOne)
			throws DeviceNotFoundException_Exception,
			EncryptionException_Exception {
		AuthenticationStepOneDTO lcAuthenticationStepOneDTO = transformStepOneToDTO(lcStepOne);

		encryptStepOneDTO(lcAuthenticationStepOneDTO);

		AuthenticationStepOneReturnDTO lcAuthenticationStepOneReturnDTO = getAuthWSPort()
				.authenticateStepOne(lcAuthenticationStepOneDTO);

		decryptStepOneReturnDTO(lcAuthenticationStepOneReturnDTO);

		AuthenticationStepOneReturn lcReturn = transformStepOneDTOToService(lcAuthenticationStepOneReturnDTO);

		return lcReturn;
	}

	public AuthenticationStepTwoReturn authenticateStepTwo(
			AuthenticationStepTwo lcStepTwo)
			throws DeviceNotFoundException_Exception,
			EncryptionException_Exception {
		AuthenticationStepTwoDTO lcAuthenticationStepTwoDTO = transformStepTwoToDTO(lcStepTwo);

		encryptStepTwoDTO(lcAuthenticationStepTwoDTO);

		AuthenticationStepTwoReturnDTO lcAuthenticationStepTwoReturnDTO = getAuthWSPort()
				.authenticateStepTwo(lcAuthenticationStepTwoDTO);

		decryptStepTwoReturnDTO(lcAuthenticationStepTwoReturnDTO);

		AuthenticationStepTwoReturn lcReturn = transformStepTwoDTOToService(lcAuthenticationStepTwoReturnDTO);

		return lcReturn;
	}

	private void encryptStepOneDTO(AuthenticationStepOneDTO p) {

	}

	private void decryptStepOneReturnDTO(AuthenticationStepOneReturnDTO p) {

	}

	private void encryptStepTwoDTO(AuthenticationStepTwoDTO p) {

	}

	private void decryptStepTwoReturnDTO(AuthenticationStepTwoReturnDTO p) {

	}

	private AuthenticationStepOneDTO transformStepOneToDTO(
			AuthenticationStepOne pStepOne) {
		AuthenticationStepOneDTO lcResult = new AuthenticationStepOneDTO();
		lcResult.setDate(pStepOne.getDate());
		lcResult.setDeviceId(pStepOne.getDeviceId());
		lcResult.setPassword(pStepOne.getPassword());
		lcResult.setUsername(pStepOne.getUsername());
		return lcResult;
	}

	private AuthenticationStepOneReturn transformStepOneDTOToService(
			AuthenticationStepOneReturnDTO pStepOne) {
		AuthenticationStepOneReturn lcResult = new AuthenticationStepOneReturn();
		lcResult.setHandshakeId(pStepOne.getHandshakeId());
		lcResult.setRandomHashedValue(pStepOne.getRandomHashedValue());
		return lcResult;
	}

	private AuthenticationStepTwoDTO transformStepTwoToDTO(
			AuthenticationStepTwo pStepOne) {
		AuthenticationStepTwoDTO lcResult = new AuthenticationStepTwoDTO();
		lcResult.setDate(pStepOne.getDate());
		lcResult.setHandshakeId(pStepOne.getHandshakeId());
		lcResult.setRandomHashedValue(pStepOne.getRandomHashedValue());
		return lcResult;
	}

	private AuthenticationStepTwoReturn transformStepTwoDTOToService(
			AuthenticationStepTwoReturnDTO pStepOne) {
		AuthenticationStepTwoReturn lcResult = new AuthenticationStepTwoReturn();
		lcResult.setTokenId(pStepOne.getTokenId());
		return lcResult;
	}

	public AuthenticationWebservice getAuthWSPort() {
		if (authWSPort != null) {
			authWSPort = getAuthWS().getAuthenticationWebservicePort();
		}
		return authWSPort;
	}

	public AuthenticationWebserviceService getAuthWS() {
		if (authWS != null) {
			authWS = new AuthenticationWebserviceService();
		}
		return authWS;
	}

}
