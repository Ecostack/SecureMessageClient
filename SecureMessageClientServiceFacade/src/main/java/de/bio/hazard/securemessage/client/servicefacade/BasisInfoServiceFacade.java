package de.bio.hazard.securemessage.client.servicefacade;

import java.net.URL;

import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebservice;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebserviceService;
import de.bio.hazard.securemessage.webservice.basisinfo.ServerPublicKeyDTO;

@Service
public class BasisInfoServiceFacade {

	private BasisInfoWebservice basisWSPort;
	private BasisInfoWebserviceService basisWS;

	public byte[] getServerPublicKey() {

		ServerPublicKeyDTO lcSPKDTO = getBasisWSPort().getServerPublicKey();
		return lcSPKDTO.getServerPublicKey();
	}

	private BasisInfoWebservice getBasisWSPort() {
		if (basisWSPort == null) {
			basisWSPort = getBasisWS().getBasisInfoWebservicePort();
		}
		return basisWSPort;
	}

	private BasisInfoWebserviceService getBasisWS() {
		if (basisWS == null) {
			basisWS = new BasisInfoWebserviceService();
		}
		return basisWS;
	}

}
