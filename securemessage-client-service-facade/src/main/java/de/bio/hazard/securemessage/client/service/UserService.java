package de.bio.hazard.securemessage.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.UserServiceFacade;
import de.bio.hazard.securemessage.client.servicefacade.helper.CommunicationKeyHelper;
import de.bio.hazard.securemessage.tecframework.exception.EncryptionExceptionBiohazard;
import de.bio.hazard.securemessage.tecframework.exception.UserExceptionBiohazard;
import de.bio.hazard.securemessage.webservice.user.UserNotFoundException_Exception;

@Service
public class UserService {

    @Autowired
    private UserServiceFacade userServiceFacade;

    public byte[] getServerPublicKey(String pUsername, CommunicationKeyHelper pCommunicationKey) throws UserExceptionBiohazard {
	try {
	    return userServiceFacade.getPublicKeyByUsername(pUsername, pCommunicationKey);
	}
	catch (EncryptionExceptionBiohazard e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new UserExceptionBiohazard();
	}
	catch (UserNotFoundException_Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new UserExceptionBiohazard();
	}
    }
}