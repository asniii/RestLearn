package com.aditya.restlearn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aditya.restlearn.database.DatabaseClass;
import com.aditya.restlearn.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1,"Hello world","koushik"));
		messages.put(2L, new Message(2, "Hello jersey","aditya"));
	}
	
	public List<Message> getAllMessage(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}

}
