package de.bio.hazard.securemessage.client.servicefacade;

import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.webservice.user.UserWebservice;
import de.bio.hazard.securemessage.webservice.user.UserWebserviceService;


//TODO NicoH; Ausarbeiten
@Service
public class UserServiceFacade {

	private UserWebservice userWSPort;
	private UserWebserviceService userWS;

//	public byte[] getPublicKeyByUsername(String pUsername) {
//		
//		UserWebserviceDTO lcDTO = new UserWebserviceDTO();
//		lcDTO.set
//
//		UserWebserviceReturnDTO lcReturn= getUserWSPort().getPublicKeyByUsername(pUsername);
//		
//		ServerPublicKeyDTO lcSPKDTO = getUserWSPort().getServerPublicKey();
//		return lcSPKDTO.getServerPublicKey();
//	}

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
