package com.campus.exception.mappers;

import com.campus.exception.RoomNotEmptyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    
    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        String json = String.format(
            "{\"error\":\"Conflict\",\"message\":\"%s\",\"roomId\":\"%s\",\"activeSensors\":%d}",
            ex.getMessage(), ex.getRoomId(), ex.getSensorCount()
        );
        return Response.status(Response.Status.CONFLICT)
                .entity(json)
                .type("application/json")
                .build();
    }
}