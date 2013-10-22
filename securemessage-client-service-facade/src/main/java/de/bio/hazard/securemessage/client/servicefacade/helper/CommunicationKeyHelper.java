package de.bio.hazard.securemessage.client.servicefacade.helper;

public class CommunicationKeyHelper extends KeyHelper {

	private String tokenId;
	private byte[] userPrivateKey;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public byte[] getUserPrivateKey() {
		return userPrivateKey;
	}

	public void setUserPrivateKey(byte[] userPrivateKey) {
		this.userPrivateKey = userPrivateKey;
	}
}
