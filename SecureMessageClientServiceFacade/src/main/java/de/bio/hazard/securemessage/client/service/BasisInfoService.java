package de.bio.hazard.securemessage.client.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.client.servicefacade.AuthenticationServiceFacade;
import de.bio.hazard.securemessage.client.servicefacade.BasisInfoServiceFacade;

@Service
public class BasisInfoService {

	@Autowired
	private BasisInfoServiceFacade basisInfoServiceFacade;

	private byte[] serverPublicKey = null;

	public byte[] getServerPublicKey() throws IOException {
		if (serverPublicKey == null) {
			serverPublicKey = basisInfoServiceFacade.getServerPublicKey();
		}
		return serverPublicKey;
	}

	public BasisInfoServiceFacade getBasisInfoServiceFacade() {
		return basisInfoServiceFacade;
	}

	public void setBasisInfoServiceFacade(
			BasisInfoServiceFacade basisInfoServiceFacade) {
		this.basisInfoServiceFacade = basisInfoServiceFacade;
	}

}
