package com.campus.service;

import com.campus.model.Room;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RoomService {
    private static RoomService instance;
    private final Map<String, Room> rooms;
    
    private RoomService() {
        rooms = new ConcurrentHashMap<>();
        // Sample data
        rooms.put("room1", new Room("room1", "Lecture Hall A", "Main Building", 1));
        rooms.put("room2", new Room("room2", "Lab B", "Science Block", 2));
    }
    
    public static synchronized RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService();
        }
        return instance;
    }
    
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }
    
    public Room getRoom(String id) {
        return rooms.get(id);
    }
    
    public synchronized Room createRoom(Room room) {
        if (room.getId() == null || room.getId().isEmpty()) {
            room.setId(UUID.randomUUID().toString());
        }
        rooms.put(room.getId(), room);
        return room;
    }
    
    public synchronized boolean deleteRoom(String id) {
        return rooms.remove(id) != null;
    }
    
    public boolean roomExists(String id) {
        return rooms.containsKey(id);
    }
    
    public boolean roomHasSensors(String roomId) {
        Room room = rooms.get(roomId);
        return room != null && room.hasSensors();
    }
    
    public void addSensorToRoom(String roomId, String sensorId) {
        Room room = rooms.get(roomId);
        if (room != null) {
            room.addSensorId(sensorId);
        }
    }
    
    public void removeSensorFromRoom(String roomId, String sensorId) {
        Room room = rooms.get(roomId);
        if (room != null) {
            room.removeSensorId(sensorId);
        }
    }
}