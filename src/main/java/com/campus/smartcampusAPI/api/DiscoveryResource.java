package com.campus.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscovery() {
        Map<String, Object> info = new HashMap<>();
        info.put("version", "1.0.0");
        info.put("apiVersion", "v1");
        info.put("contact", "smartcampus@university.edu");
        
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        info.put("resources", resources);
        
        return Response.ok(info).build();
    }
}