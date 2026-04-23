package com.campus.api;

import com.campus.exception.LinkedResourceNotFoundException;
import com.campus.model.Sensor;
import com.campus.service.RoomService;
import com.campus.service.SensorService;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    
    private final SensorService sensorService = SensorService.getInstance();
    private final RoomService roomService = RoomService.getInstance();
    
    @GET
    public Response getSensors(@QueryParam("type") String type) {
        return Response.ok(sensorService.getSensorsByType(type)).build();
    }
    
    @POST
    public Response createSensor(Sensor sensor, @Context UriInfo uriInfo) {
        if (!roomService.roomExists(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException("Room", sensor.getRoomId());
        }
        Sensor created = sensorService.createSensor(sensor);
        URI location = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
        return Response.created(location).entity(created).build();
    }
    
    @GET
    @Path("/{sensorId}")
    public Response getSensor(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensorService.getSensor(sensorId);
        if (sensor == null) {
            return Response.status(404).entity("{\"error\":\"Sensor not found\"}").build();
        }
        return Response.ok(sensor).build();
    }
    
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        if (!sensorService.sensorExists(sensorId)) {
            throw new LinkedResourceNotFoundException("Sensor", sensorId);
        }
        return new SensorReadingResource(sensorId);
    }
    
    @PUT
    @Path("/{sensorId}/maintenance")
    public Response setMaintenance(@PathParam("sensorId") String sensorId) {
        sensorService.updateSensorStatus(sensorId, "MAINTENANCE");
        return Response.ok("{\"status\":\"MAINTENANCE mode activated\"}").build();
    }
    
    @PUT
    @Path("/{sensorId}/status")
    public Response updateStatus(@PathParam("sensorId") String sensorId,
                                  @QueryParam("mode") String mode) {
        if (mode == null || mode.isEmpty()) {
            return Response.status(400)
                    .entity("{\"error\":\"Missing mode parameter. Use ?mode=ACTIVE or ?mode=MAINTENANCE\"}")
                    .build();
        }

        if (!mode.equals("ACTIVE") && !mode.equals("MAINTENANCE")) {
            return Response.status(400)
                    .entity("{\"error\":\"Invalid mode. Allowed: ACTIVE, MAINTENANCE\"}")
                    .build();
        }

        sensorService.updateSensorStatus(sensorId, mode);
        return Response.ok("{\"sensorId\":\"" + sensorId + "\", \"status\":\"" + mode + "\"}")
                .build();
    }
}