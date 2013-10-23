package de.bio.hazard.securemessage.webserviceclient.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebservice;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebserviceService;
import de.bio.hazard.securemessage.webservice.authentication.NewUserWebserviceDTO;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebservice;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebserviceService;
import de.bio.hazard.securemessage.webservice.basisinfo.ServerPublicKeyDTO;
import de.bio.hazard.securemessage.webservice.user.UserNotFoundException_Exception;
import de.bio.hazard.securemessage.webservice.user.UserWebservice;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceDTO;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceReturnDTO;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceService;

public class TestUser {

	// @Test
	public void testSearchUserFail() {
		try {
			UserWebserviceService lcEndpointService = new UserWebserviceService(
					new URL("http://localhost:8080/userWebservice"));

			UserWebservice lcEndpoint = lcEndpointService
					.getUserWebservicePort();

			UserWebserviceDTO lcUserWebserviceDTO = new UserWebserviceDTO();
			// Dieser Test sollte allein schon nicht klappen, weil die Daten
			// nicht verschl�sselt sind
			lcUserWebserviceDTO.setUsername("ThisUserDoesNotExist");

			// UserWebserviceReturnDTO lcUserWebserviceReturnDTO = lcEndpoint
			// .getPublicKeyByUsername(lcUserWebserviceDTO);
			lcEndpoint.getPublicKeyByUsername(lcUserWebserviceDTO);
			assertTrue(false);
		} catch (UserNotFoundException_Exception e) {
			assertTrue(true);
		} catch (MalformedURLException e) {
			System.err.println("testSearchUserFail: URL not found");
			assertTrue(false);
		}
	}

	// @Test
	public void testSearchUserOK() {
		String lcUsername = "testUserSearch";
		String lcPublicKey = "PublicKey f�r User \"" + lcUsername + "\"";
		try {
			AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(
					new URL("http://localhost:8080/authenticationWebservice"));
			AuthenticationWebservice lcEndpoint = lcEndpointService
					.getAuthenticationWebservicePort();

			EncryptionObjectModifier encryptionObjectModifier = new EncryptionObjectModifier();

			SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
			byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
			byte[] lcServerPublicKey = encryptionObjectModifier
					.decodeBase64ToByte(getServerPublicKey()
							.getServerPublicKeyAsBase64());

			NewUserWebserviceDTO lcNewUserWebserviceDTO = createEncryptedNewUserWebserviceDTO(
					lcUsername, "myPa$$word", "Mustermann", "Max",
					"usersearch@test.test", "0123456789", lcPublicKey,
					lcSymKey, lcServerPublicKey);

			lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		try {
			UserWebserviceService lcEndpointService = new UserWebserviceService(
					new URL("http://localhost:8080/userWebservice"));
			UserWebservice lcEndpoint = lcEndpointService
					.getUserWebservicePort();
			UserWebserviceDTO lcUserWebserviceDTO = new UserWebserviceDTO();

			EncryptionObjectModifier encryptionObjectModifier = new EncryptionObjectModifier();

			SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
			byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
			byte[] lcServerPublicKey = encryptionObjectModifier
					.decodeBase64ToByte(getServerPublicKey()
							.getServerPublicKeyAsBase64());

			lcUserWebserviceDTO.setUsername(encryptionObjectModifier
					.symmetricEncrypt(lcUsername, lcSymKey));
			lcUserWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier
					.asymmetricEncrypt(lcSymKey, lcServerPublicKey, false));

			UserWebserviceReturnDTO lcUserWebserviceReturnDTO = lcEndpoint
					.getPublicKeyByUsername(lcUserWebserviceDTO);
			lcUserWebserviceReturnDTO.setPublicKey(encryptionObjectModifier
					.decodeBase64(lcUserWebserviceReturnDTO.getPublicKey()));
			System.out.println(lcUserWebserviceReturnDTO.getPublicKey());
		} catch (UserNotFoundException_Exception e) {
			System.err
					.println("testSearchUserOK: User not found - vorher \"TestWebservice\" aufrufen");
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	private ServerPublicKeyDTO getServerPublicKey()
			throws MalformedURLException {
		BasisInfoWebserviceService lcEndpointService = new BasisInfoWebserviceService(
				new URL("http://localhost:8080/basisInfoWebservice"));

		BasisInfoWebservice lcEndpoint = lcEndpointService
				.getBasisInfoWebservicePort();

		ServerPublicKeyDTO lcSPKDTO = lcEndpoint.getServerPublicKey();
		return lcSPKDTO;
	}

	private NewUserWebserviceDTO createEncryptedNewUserWebserviceDTO(
			String pUsername, String pPassword, String pName, String pPrename,
			String pEmail, String pMobilenumber, String pUserPublicKey,
			byte[] pSymKey, byte[] pServerPublicKey) throws IOException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException,
			NoSuchProviderException, NoSuchPaddingException {
		EncryptionObjectModifier encryptionObjectModifier = new EncryptionObjectModifier();
		NewUserWebserviceDTO lcNewUserWebserviceDTO = new NewUserWebserviceDTO();
		lcNewUserWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier
				.asymmetricEncrypt(pSymKey, pServerPublicKey, false));
		lcNewUserWebserviceDTO.setEmail(encryptionObjectModifier
				.symmetricEncrypt(pEmail, pSymKey));
		lcNewUserWebserviceDTO.setUsername(encryptionObjectModifier
				.symmetricEncrypt(pUsername, pSymKey));
		lcNewUserWebserviceDTO.setTelephone(encryptionObjectModifier
				.symmetricEncrypt(pMobilenumber, pSymKey));
		lcNewUserWebserviceDTO.setName(encryptionObjectModifier
				.symmetricEncrypt(pName, pSymKey));
		lcNewUserWebserviceDTO.setPrename(encryptionObjectModifier
				.symmetricEncrypt(pPrename, pSymKey));
		lcNewUserWebserviceDTO.setPassword(encryptionObjectModifier
				.symmetricEncrypt(pPassword, pSymKey));
		lcNewUserWebserviceDTO
				.setPublicKeyForMessaging(encryptionObjectModifier
						.symmetricEncrypt(pUserPublicKey, pSymKey));
		return lcNewUserWebserviceDTO;
	}
}
