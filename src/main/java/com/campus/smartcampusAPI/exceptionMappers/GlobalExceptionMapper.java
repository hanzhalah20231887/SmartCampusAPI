package com.campus.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable ex) {
        String errorId = UUID.randomUUID().toString();
        String json = String.format(
            "{\"error\":\"Internal Server Error\",\"message\":\"An unexpected error occurred\",\"errorId\":\"%s\"}",
            errorId
        );
        System.err.println("Error ID: " + errorId);
        ex.printStackTrace();
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(json)
                .type("application/json")
                .build();
    }
}