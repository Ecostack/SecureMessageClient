package de.bio.hazard.securemessage.client.servicefacade.helper;

public class NewUserKeyHelper extends KeyHelper {
	private byte[] publicKeyForMessaging;

	public byte[] getPublicKeyForMessaging() {
		return publicKeyForMessaging;
	}

	public void setPublicKeyForMessaging(byte[] publicKeyForMessaging) {
		this.publicKeyForMessaging = publicKeyForMessaging;
	}
}
