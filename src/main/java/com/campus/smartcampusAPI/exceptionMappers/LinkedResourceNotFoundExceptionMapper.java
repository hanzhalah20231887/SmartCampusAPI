package com.campus.exception.mappers;

import com.campus.exception.LinkedResourceNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    
    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {
        String json = String.format(
            "{\"error\":\"Unprocessable Entity\",\"message\":\"%s\",\"resourceType\":\"%s\",\"resourceId\":\"%s\"}",
            ex.getMessage(), ex.getResourceType(), ex.getResourceId()
        );
        return Response.status(422)
                .entity(json)
                .type("application/json")
                .build();
    }
}