package de.bio.hazard.securemessage.client.servicefacade.model.message;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private List<MessageContent> content;

	private List<MessageReceiver> receiver;

	public List<MessageReceiver> getReceiver() {
		if (receiver == null) {
			receiver = new ArrayList<MessageReceiver>();
		}
		return receiver;
	}

	public List<MessageContent> getContent() {
		if (content == null) {
			content = new ArrayList<MessageContent>();
		}
		return content;
	}
}
