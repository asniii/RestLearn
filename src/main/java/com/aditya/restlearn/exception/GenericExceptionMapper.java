package com.aditya.restlearn.exception;

import com.aditya.restlearn.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//This was for example.

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "http://google.com");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}
