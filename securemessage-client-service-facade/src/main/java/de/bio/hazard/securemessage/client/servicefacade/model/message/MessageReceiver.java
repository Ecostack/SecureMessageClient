package de.bio.hazard.securemessage.client.servicefacade.model.message;

import de.bio.hazard.securemessage.client.servicefacade.model.message.helper.MessageReceiverType;

public class MessageReceiver {
	private String username;

	private MessageReceiverType messageReceiverType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MessageReceiverType getMessageReceiverType() {
		return messageReceiverType;
	}

	public void setMessageReceiverType(MessageReceiverType messageReceiverType) {
		this.messageReceiverType = messageReceiverType;
	}
}
