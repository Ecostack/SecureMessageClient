package de.bio.hazard.securemessage.webserviceclient.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.TestCase;
import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.tecframework.encryption.symmetric.SymmetricKeygen;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebservice;
import de.bio.hazard.securemessage.webservice.authentication.AuthenticationWebserviceService;
import de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceDTO;
import de.bio.hazard.securemessage.webservice.authentication.NewUserWebserviceDTO;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebservice;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebserviceService;
import de.bio.hazard.securemessage.webservice.basisinfo.ServerPublicKeyDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebservice;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceDTO;
import de.bio.hazard.securemessage.webservice.message.MessageWebserviceService;

public class TestWerbservice extends TestCase {

//    public void testMessage() {
//	try {
//	    MessageWebserviceService lcEndpointService = new MessageWebserviceService(new URL("http://localhost:8080/messageWebservice"));
//
//	    MessageWebservice lcEndpoint = lcEndpointService.getMessageWebservicePort();
//
//	    MessageWebserviceDTO lcMessageWebserviceDTO = new MessageWebserviceDTO();
//	    lcMessageWebserviceDTO.setMessageText("Mein Text vom WS");
//	    lcMessageWebserviceDTO.setSubject("Mein Subject vom WS");
//
//	    lcEndpoint.addMessage(lcMessageWebserviceDTO);
//	}
//	catch (Exception e) {
//	    e.printStackTrace();
//	    assertTrue(false);
//	}
//    }

    public void testBasisInfo() {
	try {
	    ServerPublicKeyDTO lcSPKDTO = getServerPublicKey();

	    System.err.println("SeverPublicKey length: " + lcSPKDTO.getServerPublicKey());
	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(false);
	}
    }

    private ServerPublicKeyDTO getServerPublicKey() throws MalformedURLException {
	BasisInfoWebserviceService lcEndpointService = new BasisInfoWebserviceService(new URL("http://localhost:8080/basisInfoWebservice"));

	BasisInfoWebservice lcEndpoint = lcEndpointService.getBasisInfoWebservicePort();

	ServerPublicKeyDTO lcSPKDTO = lcEndpoint.getServerPublicKey();
	return lcSPKDTO;
    }

    public void testAuthNewUserFail() {
	try {
	    AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(new URL("http://localhost:8080/authenticationWebservice"));

	    AuthenticationWebservice lcEndpoint = lcEndpointService.getAuthenticationWebservicePort();

	    NewUserWebserviceDTO lcNewUserWebserviceDTO = new NewUserWebserviceDTO();
	    lcNewUserWebserviceDTO.setEmail("Hallo");
	    lcNewUserWebserviceDTO.setUsername("hans");

	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
	    assertFalse(true);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(true);
	}
    }

    public void testAuthNewUserCorrect() {
	try {
	    AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(new URL("http://localhost:8080/authenticationWebservice"));
	    AuthenticationWebservice lcEndpoint = lcEndpointService.getAuthenticationWebservicePort();

	    SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
	    byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
	    byte[] lcServerPublicKey = getServerPublicKey().getServerPublicKey();

	    NewUserWebserviceDTO lcNewUserWebserviceDTO = createEncryptedNewUserWebserviceDTO("Username", "myPa$$word", "Mustermann", "Max", "mail@mail.mail", "0123456789",
		    "PublicAsymetricKey (User:Username)", lcSymKey, lcServerPublicKey);

	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(false);
	}
    }

    public void testAuthNewUserDoubleFail() {
	try {
	    AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(new URL("http://localhost:8080/authenticationWebservice"));
	    AuthenticationWebservice lcEndpoint = lcEndpointService.getAuthenticationWebservicePort();

	    SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
	    byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
	    byte[] lcServerPublicKey = getServerPublicKey().getServerPublicKey();

	    NewUserWebserviceDTO lcNewUserWebserviceDTO = createEncryptedNewUserWebserviceDTO("Double", "abc123", "Meier", "Karl-Heinz", "heinz.meier.at.mail.at", "myPhoneNumber",
		    "PublicAsymetricKeyMeier", lcSymKey, lcServerPublicKey);

	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
	    // Fail here
	    // TODO SebastianS Wird trotz Fehler die ID hochgezählt? ist das richtig und gut? ;D
	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);
	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(true);
	}
    }

    public void testAuthNewUserWithDevice() {
	try {
	    AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(new URL("http://localhost:8080/authenticationWebservice"));
	    AuthenticationWebservice lcEndpoint = lcEndpointService.getAuthenticationWebservicePort();

	    SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
	    byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
	    byte[] lcServerPublicKey = getServerPublicKey().getServerPublicKey();

	    String lcUsername = "UserDevicetest";
	    String lcPassword = "MyPass";

	    NewUserWebserviceDTO lcNewUserWebserviceDTO = createEncryptedNewUserWebserviceDTO(lcUsername, lcPassword, "with Device", "user", "ich hab gar keine Mail :)", "123123",
		    "PublicAsymetricKeyUserWithDevice", lcSymKey, lcServerPublicKey);
	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);

	    lcSymKey = lcSymmetricKeygen.getKey(128);
	    NewDeviceWebserviceDTO lcNewDeviceWebserviceDTO = createEncryptedNewDeviceWebserviceDTO("TestUserDevice", lcUsername, lcPassword, "DevicePublicKey", lcSymKey, lcServerPublicKey);
	    lcEndpoint.addNewDevice(lcNewDeviceWebserviceDTO);

	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(false);
	}
    }

    
    public void testAuthNewUserWithTwoDevices() {
	try {
	    AuthenticationWebserviceService lcEndpointService = new AuthenticationWebserviceService(new URL("http://localhost:8080/authenticationWebservice"));
	    AuthenticationWebservice lcEndpoint = lcEndpointService.getAuthenticationWebservicePort();

	    SymmetricKeygen lcSymmetricKeygen = new SymmetricKeygen();
	    byte[] lcSymKey = lcSymmetricKeygen.getKey(128);
	    byte[] lcServerPublicKey = getServerPublicKey().getServerPublicKey();

	    String lcUsername = "User2Devices";
	    String lcPassword = "blabla";

	    NewUserWebserviceDTO lcNewUserWebserviceDTO = createEncryptedNewUserWebserviceDTO(lcUsername, lcPassword, "with 2 Devices", "user", "..,-", "9897654",
		    "PublicAsymetricKeyUserWith2Devices", lcSymKey, lcServerPublicKey);
	    lcEndpoint.addNewUser(lcNewUserWebserviceDTO);

	    lcSymKey = lcSymmetricKeygen.getKey(128);
	    NewDeviceWebserviceDTO lcNewDeviceWebserviceDTO = createEncryptedNewDeviceWebserviceDTO("Test2UserDevicesD1", lcUsername, lcPassword, "DevicePublicKey64832", lcSymKey, lcServerPublicKey);
	    lcEndpoint.addNewDevice(lcNewDeviceWebserviceDTO);

	    lcSymKey = lcSymmetricKeygen.getKey(128);
	    lcNewDeviceWebserviceDTO = createEncryptedNewDeviceWebserviceDTO("Test2UserDevicesD2", lcUsername, lcPassword, "DevicePublicKey73263", lcSymKey, lcServerPublicKey);
	    lcEndpoint.addNewDevice(lcNewDeviceWebserviceDTO);

	}
	catch (Exception e) {
	    e.printStackTrace();
	    assertTrue(false);
	}
    }

    private NewDeviceWebserviceDTO createEncryptedNewDeviceWebserviceDTO(String pDevicename, String pUsername, String pPassword, String pDevicePublicKey, byte[] lcSymKey, byte[] lcServerPublicKey)
	    throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	EncryptionObjectModifier encryptionObjectModifier = new EncryptionObjectModifier();
	NewDeviceWebserviceDTO lcNewDeviceWebserviceDTO = new NewDeviceWebserviceDTO();
	lcNewDeviceWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(lcSymKey, lcServerPublicKey, false));
	lcNewDeviceWebserviceDTO.setDevicename(encryptionObjectModifier.symmetricEncrypt(pDevicename, lcSymKey));
	lcNewDeviceWebserviceDTO.setUsername(encryptionObjectModifier.symmetricEncrypt(pUsername, lcSymKey));
	lcNewDeviceWebserviceDTO.setPassword(encryptionObjectModifier.symmetricEncrypt(pPassword, lcSymKey));
	lcNewDeviceWebserviceDTO.setPublicKeyForDevice(encryptionObjectModifier.symmetricEncrypt(pDevicePublicKey, lcSymKey));
	return lcNewDeviceWebserviceDTO;
    }

    private NewUserWebserviceDTO createEncryptedNewUserWebserviceDTO(String pUsername, String pPassword, String pName, String pPrename, String pEmail, String pMobilenumber, String pUserPublicKey,
	    byte[] pSymKey, byte[] pServerPublicKey) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException,
	    NoSuchPaddingException {
	EncryptionObjectModifier encryptionObjectModifier = new EncryptionObjectModifier();
	NewUserWebserviceDTO lcNewUserWebserviceDTO = new NewUserWebserviceDTO();
	lcNewUserWebserviceDTO.setSymEncryptionKey(encryptionObjectModifier.asymmetricEncrypt(pSymKey, pServerPublicKey, false));
	lcNewUserWebserviceDTO.setEmail(encryptionObjectModifier.symmetricEncrypt(pEmail, pSymKey));
	lcNewUserWebserviceDTO.setUsername(encryptionObjectModifier.symmetricEncrypt(pUsername, pSymKey));
	lcNewUserWebserviceDTO.setTelephone(encryptionObjectModifier.symmetricEncrypt(pMobilenumber, pSymKey));
	lcNewUserWebserviceDTO.setName(encryptionObjectModifier.symmetricEncrypt(pName, pSymKey));
	lcNewUserWebserviceDTO.setPrename(encryptionObjectModifier.symmetricEncrypt(pPrename, pSymKey));
	lcNewUserWebserviceDTO.setPassword(encryptionObjectModifier.symmetricEncrypt(pPassword, pSymKey));
	lcNewUserWebserviceDTO.setPublicKeyForMessaging(encryptionObjectModifier.symmetricEncrypt(pUserPublicKey, pSymKey));
	return lcNewUserWebserviceDTO;
    }

}
