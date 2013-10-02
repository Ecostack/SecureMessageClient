package de.bio.hazard.securemessage.client.servicefacade.model.authentication;

public class NewDeviceWebservice {

	private String username;
	private String password;
	private String devicename;
	private byte[] publicKeyForDevice;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public byte[] getPublicKeyForDevice() {
		return publicKeyForDevice;
	}

	public void setPublicKeyForDevice(byte[] publicKeyForDevice) {
		this.publicKeyForDevice = publicKeyForDevice;
	}

}
