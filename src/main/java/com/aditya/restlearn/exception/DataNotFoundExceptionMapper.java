package com.aditya.restlearn.exception;

import com.aditya.restlearn.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),404,"www.google.com");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
