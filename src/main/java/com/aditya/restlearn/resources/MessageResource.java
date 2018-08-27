package com.aditya.restlearn.resources;

import com.aditya.restlearn.model.Message;
import com.aditya.restlearn.model.Profile;
import com.aditya.restlearn.resources.beans.MessageFilterBean;
import com.aditya.restlearn.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
// For xml output add the "Accept" header in postman( or whereever u want)
public class MessageResource {
	
	MessageService messageService = new MessageService();


	//localhost:8080/restlearn/webapi/messages?year=2018
	//localhost:8080/restlearn/webapi/messages?start=0&size=1
	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
			@QueryParam("start") int start,
			@QueryParam("size") int size) {
		
		if(year > 0) {
			return messageService.getAllMesssagesForYear(year);
		}
		if(start>=0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessage();
	}


	//localhost:8080/restlearn/webapi/messages/bean?start=0&size=1
	//localhost:8080/restlearn/webapi/messages/bean?year=2018
	@GET
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
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		String uri = getUriForSelf(uriInfo, message);
		message.addLink(uri,"self");

		String uriForProfile = getUriForProfile(uriInfo,message);
		message.addLink(uriForProfile,"profile");

		String uriForComments = getUriForComments(uriInfo,message);
		message.addLink(uriForComments,"comments");
		return message;
	}

	private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
	}

	private String getUriForProfile(@Context UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
	}


	//localhost:8080/restlearn/webapi/messages
	@POST
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
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}


	//localhost:8080/restlearn/webapi/messages/11
	@DELETE
	@Path("/{messageId}")
	public void deleteMesssage(@PathParam("messageId") long messageId) {
		 messageService.removeMessage(messageId); 
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	

}
