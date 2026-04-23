package com.campus.api;

import com.campus.exception.RoomNotEmptyException;
import com.campus.model.Room;
import com.campus.service.RoomService;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    
    private final RoomService roomService = RoomService.getInstance();
    
    @GET
    public Response getAllRooms() {
        return Response.ok(roomService.getAllRooms()).build();
    }
    
    @POST
    public Response createRoom(Room room, @Context UriInfo uriInfo) {
        Room created = roomService.createRoom(room);
        URI location = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
        return Response.created(location).entity(created).build();
    }
    
    @GET
    @Path("/{roomId}")
    public Response getRoom(@PathParam("roomId") String roomId) {
        Room room = roomService.getRoom(roomId);
        if (room == null) {
            return Response.status(404).entity("{\"error\":\"Room not found\"}").build();
        }
        return Response.ok(room).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = roomService.getRoom(roomId);
        if (room == null) {
            return Response.status(404).entity("{\"error\":\"Room not found\"}").build();
        }
        if (room.hasSensors()) {
            throw new RoomNotEmptyException(roomId, room.getSensorIds().size());
        }
        roomService.deleteRoom(roomId);
        return Response.noContent().build();
    }
}