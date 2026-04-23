package com.campus.exception.mappers;

import com.campus.exception.SensorUnavailableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    
    @Override
    public Response toResponse(SensorUnavailableException ex) {
        String json = String.format(
            "{\"error\":\"Forbidden\",\"message\":\"%s\",\"sensorId\":\"%s\",\"currentStatus\":\"%s\"}",
            ex.getMessage(), ex.getSensorId(), ex.getStatus()
        );
        return Response.status(Response.Status.FORBIDDEN)
                .entity(json)
                .type("application/json")
                .build();
    }
}