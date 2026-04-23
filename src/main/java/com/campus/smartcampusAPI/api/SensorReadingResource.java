package com.campus.api;

import com.campus.exception.SensorUnavailableException;
import com.campus.model.Reading;
import com.campus.service.SensorService;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    
    private final String sensorId;
    private final SensorService sensorService = SensorService.getInstance();
    
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }
    
    @GET
    public Response getReadings() {
        return Response.ok(sensorService.getSensorReadings(sensorId)).build();
    }
    
    @POST
    public Response addReading(Reading reading, @Context UriInfo uriInfo) {
        var sensor = sensorService.getSensor(sensorId);
        if (sensor != null && "MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException(sensorId, sensor.getStatus());
        }
        
        Reading created = sensorService.addReading(sensorId, reading);
        if (created == null) {
            return Response.status(404).entity("{\"error\":\"Sensor not found\"}").build();
        }
        
        URI location = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
        return Response.created(location).entity(created).build();
    }
}