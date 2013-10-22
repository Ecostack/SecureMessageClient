package de.bio.hazard.securemessage.client.servicefacade.model.message;

public class MessageContent {

	private byte[] data;
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
