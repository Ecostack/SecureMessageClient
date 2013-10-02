package de.bio.hazard.securemessage.client.servicefacade.helper;

public class AuthenticationKeyHelper {
	private byte[] serverPublicKey;
	private byte[] devicePrivateKey;
	public byte[] getServerPublicKey() {
		return serverPublicKey;
	}
	public void setServerPublicKey(byte[] serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}
	public byte[] getDevicePrivateKey() {
		return devicePrivateKey;
	}

	public void setDevicePrivateKey(byte[] devicePrivateKey) {
		this.devicePrivateKey = devicePrivateKey;
	}
}
