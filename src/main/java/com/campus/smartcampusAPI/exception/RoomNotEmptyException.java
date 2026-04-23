package com.campus.exception;

public class RoomNotEmptyException extends RuntimeException {
    private String roomId;
    private int sensorCount;
    
    public RoomNotEmptyException(String roomId, int sensorCount) {
        super("Room " + roomId + " has " + sensorCount + " active sensors");
        this.roomId = roomId;
        this.sensorCount = sensorCount;
    }
    
    public String getRoomId() { return roomId; }
    public int getSensorCount() { return sensorCount; }
}