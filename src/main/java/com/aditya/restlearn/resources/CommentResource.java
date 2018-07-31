package com.aditya.restlearn.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aditya.restlearn.model.Comment;
import com.aditya.restlearn.service.CommentService;

@Path("/")
public class CommentResource {
	
	private CommentService  commentService = new CommentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){ 
		return commentService.getAllComments(messageId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment addMessage(@PathParam("messageId") long messageId,Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messsageId") long messageId, @PathParam("commentId")long id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId")long messageId, @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}" )
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId);
	}
}
