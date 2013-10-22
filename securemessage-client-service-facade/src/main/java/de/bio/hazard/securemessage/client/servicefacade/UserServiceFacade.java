package de.bio.hazard.securemessage.client.servicefacade;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.helper.CommunicationKeyHelper;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.tecframework.exception.EncryptionExceptionBiohazard;
import de.bio.hazard.securemessage.webservice.user.UserNotFoundException_Exception;
import de.bio.hazard.securemessage.webservice.user.UserWebservice;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceDTO;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceReturnDTO;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceService;

@Service
public class UserServiceFacade {

	private UserWebservice userWSPort;
	private UserWebserviceService userWS;

	@Autowired
	private EncryptionObjectModifier encryptionObjectModifier;

	@Autowired
	private SymmetricKeygen symmetricKeygen;

	public byte[] getPublicKeyByUsername(String pUsername,
			CommunicationKeyHelper pCommunicationKey)
			throws EncryptionExceptionBiohazard,
			UserNotFoundException_Exception {
		try {
			UserWebserviceDTO lcDTO = new UserWebserviceDTO();
			lcDTO.setUsername(pUsername);
			lcDTO.setMobilenumber("");
			lcDTO = encryptUserWebserviceDTO(lcDTO, pCommunicationKey);
			UserWebserviceReturnDTO lcReturn = getUserWSPort()
					.getPublicKeyByUsername(lcDTO);
			lcReturn = decryptUserWebserviceReturnDTO(lcReturn,
					pCommunicationKey);
			return encryptionObjectModifier.decodeBase64ToByte(lcReturn
					.getPublicKey());
		} catch (IOException e) {
			// TODO SebastianS; Logging
			throw new EncryptionExceptionBiohazard();
		}
	}

	private UserWebserviceReturnDTO decryptUserWebserviceReturnDTO(
			UserWebserviceReturnDTO pReturn,
			CommunicationKeyHelper pCommunicationKey)
			throws EncryptionExceptionBiohazard {
		UserWebserviceReturnDTO lcUserWebserviceReturnDTO = pReturn;
		try {
			byte[] lcSymmetricKey = encryptionObjectModifier
					.asymmetricDecryptToByte(
							lcUserWebserviceReturnDTO.getSymEncryptionKey(),
							pCommunicationKey.getDevicePrivateKey(), true);
			lcUserWebserviceReturnDTO.setPublicKey(encryptionObjectModifier
					.symmetricDecrypt(lcUserWebserviceReturnDTO.getPublicKey(),
							lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
		return lcUserWebserviceReturnDTO;
	}

	private UserWebserviceDTO encryptUserWebserviceDTO(UserWebserviceDTO pDTO,
			CommunicationKeyHelper pCommunicationKey)
			throws EncryptionExceptionBiohazard {
		UserWebserviceDTO lcUserWebserviceDTO = pDTO;
		try {
			byte[] lcSymmetricKey = symmetricKeygen.getKey(128);
			lcUserWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier
					.asymmetricEncrypt(lcSymmetricKey,
							pCommunicationKey.getServerPublicKey(), false));
			lcUserWebserviceDTO.setUsername(encryptionObjectModifier
					.symmetricEncrypt(lcUserWebserviceDTO.getUsername(),
							lcSymmetricKey));
			lcUserWebserviceDTO.setMobilenumber(encryptionObjectModifier
					.symmetricEncrypt(lcUserWebserviceDTO.getMobilenumber(),
							lcSymmetricKey));
			lcUserWebserviceDTO.setTokenId(encryptionObjectModifier
					.symmetricEncrypt(pCommunicationKey.getTokenId(),
							lcSymmetricKey));
		} catch (Exception e) {
			// TODO SebastianS; Logging
			e.printStackTrace();
			throw new EncryptionExceptionBiohazard();
		}
		return lcUserWebserviceDTO;
	}

	private UserWebservice getUserWSPort() {
		if (userWSPort == null) {
			userWSPort = getUserWS().getUserWebservicePort();
		}
		return userWSPort;
	}

	private UserWebserviceService getUserWS() {
		if (userWS == null) {
			userWS = new UserWebserviceService();
		}
		return userWS;
	}
}
