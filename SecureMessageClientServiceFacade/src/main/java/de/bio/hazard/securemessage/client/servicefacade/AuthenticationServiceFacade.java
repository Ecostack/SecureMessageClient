package de.bio.hazard.securemessage.client.servicefacade;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.helper.AuthenticationKeyHelper;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepOne;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepOneReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepTwo;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.AuthenticationStepTwoReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebservice;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewDeviceWebserviceReturn;
import de.bio.hazard.securemessage.client.servicefacade.model.authentication.NewUserWebservice;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.tecframework.exception.EncryptionExceptionBiohazard;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepOneDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepOneReturnDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepTwoDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepTwoReturnDTO;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebservice;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebserviceService;
import de.bio.hazard.securemessage.webservice.authentication.DeviceNotFoundException_Exception;
import de.bio.hazard.securemessage.webservice.authentication.EncryptionExceptionBiohazard_Exception;
import de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceDTO;
import de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceReturnDTO;
import de.bio.hazard.securemessage.webservice.authentication.NewUserWebserviceDTO;

@Service
public class AuthenticationServiceFacade {

	private AuthenticationWebservice authWSPort;
	private AuthenticationWebserviceService authWS;

	@Autowired
	private EncryptionObjectModifier encryptionObjectModifier;

	@Autowired
	private SymmetricKeygen symmetricKeygen;

	public void addNewDevice(NewDeviceWebservice pToAdd) {
		NewDeviceWebserviceDTO lcDTO = transformNewDeviceToDTO(pToAdd);
		NewDeviceWebserviceReturnDTO lcDTOReturn= getAuthWSPort().addNewDevice(lcDTO);
		lcDTOReturn = decryptNewDeviceWebserviceDTO(lcDTOReturn);
		
	}

	public void addNewUser(NewUserWebserviceDTO pToAdd)
			throws EncryptionExceptionBiohazard_Exception {
		getAuthWSPort().addNewUser(pToAdd);
	}

	public AuthenticationStepOneReturn authenticateStepOne(
			AuthenticationStepOne lcStepOne, AuthenticationKeyHelper pKeyHelper)
			throws DeviceNotFoundException_Exception,
			 EncryptionExceptionBiohazard,
			EncryptionExceptionBiohazard_Exception {
		AuthenticationStepOneDTO lcAuthenticationStepOneDTO = transformStepOneToDTO(lcStepOne);

		encryptStepOneDTO(lcAuthenticationStepOneDTO, pKeyHelper);

		AuthenticationStepOneReturnDTO lcAuthenticationStepOneReturnDTO = getAuthWSPort()
				.authenticateStepOne(lcAuthenticationStepOneDTO);

		decryptStepOneReturnDTO(lcAuthenticationStepOneReturnDTO, pKeyHelper);

		AuthenticationStepOneReturn lcReturn = transformStepOneDTOToService(lcAuthenticationStepOneReturnDTO);

		return lcReturn;
	}

	public AuthenticationStepTwoReturn authenticateStepTwo(
			AuthenticationStepTwo lcStepTwo, AuthenticationKeyHelper pKeyHelper)
			throws DeviceNotFoundException_Exception,
			EncryptionExceptionBiohazard,
			EncryptionExceptionBiohazard_Exception {
		AuthenticationStepTwoDTO lcAuthenticationStepTwoDTO = transformStepTwoToDTO(lcStepTwo);

		encryptStepTwoDTO(lcAuthenticationStepTwoDTO, pKeyHelper);

		AuthenticationStepTwoReturnDTO lcAuthenticationStepTwoReturnDTO = getAuthWSPort()
				.authenticateStepTwo(lcAuthenticationStepTwoDTO);

		decryptStepTwoReturnDTO(lcAuthenticationStepTwoReturnDTO, pKeyHelper);

		AuthenticationStepTwoReturn lcReturn = transformStepTwoDTOToService(lcAuthenticationStepTwoReturnDTO);

		return lcReturn;
	}
	
	private NewDeviceWebserviceReturnDTO decryptNewDeviceWebserviceDTO(
			NewDeviceWebserviceReturnDTO lcDTOReturn) {
		// TODO Auto-generated method stub
		return null;
	}

	private void encryptStepOneDTO(
			AuthenticationStepOneDTO pAuthenticationStepOneDTO,
			AuthenticationKeyHelper pKeyHelper)
			throws EncryptionExceptionBiohazard {
		AuthenticationStepOneDTO lcAuthenticationStepOneDTO = pAuthenticationStepOneDTO;
		try {
			byte[] lcSymmetricKey = symmetricKeygen.getKey(128);

			lcAuthenticationStepOneDTO
					.setSymEncryptionKey(encryptionObjectModifier
							.asymmetricEncrypt(lcSymmetricKey,
									pKeyHelper.getServerPublicKey(), false));
			lcAuthenticationStepOneDTO.setDeviceId(encryptionObjectModifier
					.symmetricEncrypt(lcAuthenticationStepOneDTO.getDeviceId(),
							lcSymmetricKey));
			lcAuthenticationStepOneDTO.setPassword(encryptionObjectModifier
					.symmetricEncrypt(lcAuthenticationStepOneDTO.getPassword(),
							lcSymmetricKey));
			lcAuthenticationStepOneDTO.setDate(encryptionObjectModifier
					.symmetricEncrypt(lcAuthenticationStepOneDTO.getDate(),
							lcSymmetricKey));
			lcAuthenticationStepOneDTO.setUsername(encryptionObjectModifier
					.symmetricEncrypt(lcAuthenticationStepOneDTO.getUsername(),
							lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
	}

	private void decryptStepOneReturnDTO(
			AuthenticationStepOneReturnDTO pAuthenticationStepOneReturnDTO,
			AuthenticationKeyHelper pKeyHelper)
			throws EncryptionExceptionBiohazard {
		AuthenticationStepOneReturnDTO lcAuthenticationStepOneReturnDTO = pAuthenticationStepOneReturnDTO;
		try {
			byte[] lcSymmetricKey = symmetricKeygen.getKey(128);

			lcAuthenticationStepOneReturnDTO
					.setSymEncryptionKey(encryptionObjectModifier
							.asymmetricEncrypt(lcSymmetricKey,
									pKeyHelper.getDevicePrivateKey(), true));
			lcAuthenticationStepOneReturnDTO
					.setHandshakeId(encryptionObjectModifier.symmetricEncrypt(
							lcAuthenticationStepOneReturnDTO.getHandshakeId(),
							lcSymmetricKey));
			lcAuthenticationStepOneReturnDTO
					.setRandomHashedValue(encryptionObjectModifier
							.symmetricEncrypt(lcAuthenticationStepOneReturnDTO
									.getRandomHashedValue(), lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
	}

	private void encryptStepTwoDTO(
			AuthenticationStepTwoDTO pAuthenticationStepTwoDTO,
			AuthenticationKeyHelper pKeyHelper)
			throws EncryptionExceptionBiohazard {
		AuthenticationStepTwoDTO lcAuthenticationStepTwoDTO = pAuthenticationStepTwoDTO;
		try {
			byte[] lcSymmetricKey = symmetricKeygen.getKey(128);

			lcAuthenticationStepTwoDTO
					.setSymEncryptionKey(encryptionObjectModifier
							.asymmetricEncrypt(lcSymmetricKey,
									pKeyHelper.getServerPublicKey(), false));
			lcAuthenticationStepTwoDTO.setDate(encryptionObjectModifier
					.symmetricEncrypt(lcAuthenticationStepTwoDTO.getDate(),
							lcSymmetricKey));
			lcAuthenticationStepTwoDTO.setHandshakeId(encryptionObjectModifier
					.symmetricEncrypt(
							lcAuthenticationStepTwoDTO.getHandshakeId(),
							lcSymmetricKey));
			lcAuthenticationStepTwoDTO
					.setRandomHashedValue(encryptionObjectModifier
							.symmetricEncrypt(lcAuthenticationStepTwoDTO
									.getRandomHashedValue(), lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
	}

	private void decryptStepTwoReturnDTO(
			AuthenticationStepTwoReturnDTO pAuthenticationStepTwoReturnDTO,
			AuthenticationKeyHelper pKeyHelper)
			throws EncryptionExceptionBiohazard {
		AuthenticationStepTwoReturnDTO lcAuthenticationStepTwoReturnDTO = pAuthenticationStepTwoReturnDTO;
		try {
			byte[] lcSymmetricKey = symmetricKeygen.getKey(128);

			lcAuthenticationStepTwoReturnDTO
					.setSymEncryptionKey(encryptionObjectModifier
							.asymmetricEncrypt(lcSymmetricKey,
									pKeyHelper.getServerPublicKey(), false));
			lcAuthenticationStepTwoReturnDTO
					.setTokenId(encryptionObjectModifier.symmetricEncrypt(
							lcAuthenticationStepTwoReturnDTO.getTokenId(),
							lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
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

	private NewDeviceWebserviceDTO transformNewDeviceToDTO(
			NewDeviceWebservice pDevice) {
		NewDeviceWebserviceDTO lcResult = new NewDeviceWebserviceDTO();
		
		BeanUtils.copyProperties(pDevice, lcResult);
		return lcResult;
	}
	
	private NewDeviceWebserviceReturn transformNewDeviceToDTO(
			NewDeviceWebserviceReturnDTO pDeviceDTO) {
		NewDeviceWebserviceReturn lcResult = new NewDeviceWebserviceReturn();
		
		BeanUtils.copyProperties(pDeviceDTO, lcResult);
		return lcResult;
	}
	
	private NewUserWebserviceDTO transformNewDeviceToDTO(
			NewUserWebservice pNewUser) {
		NewUserWebserviceDTO lcResult = new NewUserWebserviceDTO();
		
		BeanUtils.copyProperties(pNewUser, lcResult);
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
