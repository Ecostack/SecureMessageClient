package de.bio.hazard.securemessage.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Global {

	private AuthenticationService authenticationService;
	private BasisInfoService basisInfoService;

	@Autowired
	public Global(BasisInfoService pBasisInfoService,
			AuthenticationService pAuthenticationService) {
		authenticationService = pAuthenticationService;
		basisInfoService = pBasisInfoService;
		
		
		
		
		System.err.println("hallo ich bin global");
	}
}
