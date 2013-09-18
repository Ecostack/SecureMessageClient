package de.bio.hazard.securemessage.webserviceclient.test;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebservice;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebserviceService;
import de.bio.hazard.securemessage.webservice.authentication.NewUserWebserviceDTO;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebservice;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebserviceService;
import de.bio.hazard.securemessage.webservice.basisinfo.ServerPublicKeyDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebservice;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceService;

public class TestWerbservice extends TestCase {

	public void testMessage() {
		try {
			MessageWebserviceService lcEndpointService = new MessageWebserviceService(
					new URL("http://localhost:8080/messageWebservice"));

			MessageWebservice lcEndpoint = lcEndpointService
					.getMessageWebservicePort();

			MessageWebserviceDTO lcMessageWebserviceDTO = new MessageWebserviceDTO();
			lcMessageWebserviceDTO.setMessageText("Mein Text vom WS");
			lcMessageWebserviceDTO.setSubject("Mein Subject vom WS");

			lcEndpoint.addMessage(lcMessageWebserviceDTO);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testBasisInfo() {
		try {
			ServerPublicKeyDTO lcSPKDTO = getServerPublicKey();

			System.err.println("SeverPublicKey length: "
					+ lcSPKDTO.getServerPublicKey());
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void testAuthNewUserFail() {
		try {
			AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(
					new URL("http://localhost:8080/authenticationWebservice"));

			AuthenticationWebservice lcEndpoint = lcEndpointService
					.getAuthenticationWebservicePort();

			NewUserWebserviceDTO lcNewUserWebserviceDTO = new NewUserWebserviceDTO();
			lcNewUserWebserviceDTO.setEmail("Hallo");
			lcNewUserWebserviceDTO.setUsername("hans");

			lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
			assertFalse(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	
	public void testAuthNewUserCorrect() {
		try {
			AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(
					new URL("http://localhost:8080/authenticationWebservice"));

			AuthenticationWebservice lcEndpoint = lcEndpointService
					.getAuthenticationWebservicePort();
			
			SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
			
			EncryptionObjectModifier encryptionObjectModifier=new EncryptionObjectModifier();
			
			byte[] lcSymKey= lcSymmetricKeygen.getKey(128);
			byte[] lcSPKDTO = getServerPublicKey().getServerPublicKey();
			
			
			NewUserWebserviceDTO lcNewUserWebserviceDTO = new NewUserWebserviceDTO();
			lcNewUserWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(lcSymKey, lcSPKDTO, false));
			lcNewUserWebserviceDTO.setEmail(encryptionObjectModifier.symmetricEncrypt("HalloCorrect", lcSymKey));
			lcNewUserWebserviceDTO.setUsername(encryptionObjectModifier.symmetricEncrypt("hansCorrect", lcSymKey));
			lcNewUserWebserviceDTO.setMobilenumber(encryptionObjectModifier.symmetricEncrypt("1234Correct", lcSymKey));
			lcNewUserWebserviceDTO.setName(encryptionObjectModifier.symmetricEncrypt("MustermannCorrect", lcSymKey));
			lcNewUserWebserviceDTO.setPrename(encryptionObjectModifier.symmetricEncrypt("MaxCorrect", lcSymKey));
			lcNewUserWebserviceDTO.setPassword(encryptionObjectModifier.symmetricEncrypt("MeinPasswortCorrect", lcSymKey));
			lcNewUserWebserviceDTO.setPublicKeyForMessaging(encryptionObjectModifier.symmetricEncrypt("my public keyCorrect", lcSymKey));

			lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
