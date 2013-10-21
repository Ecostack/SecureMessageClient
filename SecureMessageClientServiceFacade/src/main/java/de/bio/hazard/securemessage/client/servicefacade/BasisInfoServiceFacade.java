package de.bio.hazard.securemessage.client.servicefacade;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bio.hazard.securemessage.tecframework.encryption.facade.helper.EncryptionObjectModifier;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebservice;
import de.bio.hazard.securemessage.webservice.basisinfo.BasisInfoWebserviceService;
import de.bio.hazard.securemessage.webservice.basisinfo.ServerPublicKeyDTO;

@Service
public class BasisInfoServiceFacade {

	private BasisInfoWebservice basisWSPort;
	private BasisInfoWebserviceService basisWS;
	
	@Autowired
	EncryptionObjectModifier encryptionObjectModifier;

	public byte[] getServerPublicKey() throws IOException {

		ServerPublicKeyDTO lcSPKDTO = getBasisWSPort().getServerPublicKey();
		
		
		return encryptionObjectModifier.decodeBase64ToByte(lcSPKDTO.getServerPublicKeyAsBase64());
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
