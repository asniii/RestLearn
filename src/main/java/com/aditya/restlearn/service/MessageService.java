package com.aditya.restlearn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.aditya.restlearn.database.DatabaseClass;
import com.aditya.restlearn.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1,"Hello world","koushik"));
		messages.put(2L, new Message(2, "Hello jersey","aditya"));
		messages.put(3L, new Message(2, "Hello jersey1","aditya1"));
		messages.put(4L, new Message(2, "Hello jersey2","aditya2"));
		messages.put(5L, new Message(2, "Hello jersey3","aditya3"));
		messages.put(6L, new Message(2, "Hello jersey4","aditya4"));
		messages.put(7L, new Message(2, "Hello jersey5","aditya5"));
		messages.put(8L, new Message(2, "Hello jersey6","aditya6"));
		messages.put(9L, new Message(2, "Hello jersey7","aditya7"));

	}
	
	public List<Message> getAllMessage(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	public List<Message> getAllMesssagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if(start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	

	public Message getMessage(long id) {
		if(messages.containsKey(id)) {
			return messages.get(id);
		}
		return null;
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
