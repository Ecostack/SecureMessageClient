package de.bio.hazard.securemessage.client.servicefacade.helper;

public class NewDeviceKeyHelper extends KeyHelper {
	private byte[] devicePublicKey;

	public byte[] getDevicePublicKey() {
		return devicePublicKey;
	}

	public void setDevicePublicKey(byte[] devicePublicKey) {
		this.devicePublicKey = devicePublicKey;
	}
}
