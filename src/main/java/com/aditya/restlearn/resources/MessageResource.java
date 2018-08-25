package com.aditya.restlearn.resources;

import com.aditya.restlearn.model.Message;
import com.aditya.restlearn.resources.beans.MessageFilterBean;
import com.aditya.restlearn.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();


	//localhost:8080/restlearn/webapi/messages?year=2018
	//localhost:8080/restlearn/webapi/messages?start=0&size=1
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
			@QueryParam("start") int start,
			@QueryParam("size") int size) {
		
		if(year > 0) {
			return messageService.getAllMesssagesForYear(year);
		}
		if(start>=0 && size >= 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessage();
	}


	//localhost:8080/restlearn/webapi/messages/bean?start=0&size=1
	//localhost:8080/restlearn/webapi/messages/bean?year=2018
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("bean")
	public List<Message> getMessagesByBean(@BeanParam MessageFilterBean messageFilterBean){
		if(messageFilterBean.getYear() > 0) {
			return messageService.getAllMesssagesForYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart() >= 0 && messageFilterBean.getSize()>0) {
			return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return messageService.getAllMessage();
	}


	//localhost:8080/restlearn/webapi/messages/5
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}


	//localhost:8080/restlearn/webapi/messages
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build())
				.entity(newMessage)
				.build();
		//return messageService.addMessage(message);
	}

	//localhost:8080/restlearn/webapi/messages/11
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}


	//localhost:8080/restlearn/webapi/messages/11
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMesssage(@PathParam("messageId") long messageId) {
		 messageService.removeMessage(messageId); 
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	

}
