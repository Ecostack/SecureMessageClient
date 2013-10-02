package de.bio.hazard.securemessage.client.model.authentication;

public class AuthenticationStepTwo {

	private String randomHashedValue;
	private String handshakeId;
	private String date;

	public String getRandomHashedValue() {
		return randomHashedValue;
	}

	public void setRandomHashedValue(String randomHashedValue) {
		this.randomHashedValue = randomHashedValue;
	}

	public String getHandshakeId() {
		return handshakeId;
	}

	public void setHandshakeId(String handshakeId) {
		this.handshakeId = handshakeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
