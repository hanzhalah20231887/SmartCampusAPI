package com.campus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private String id;
    private String type;
    private String roomId;
    private String status;
    private double currentValue;
    private List<Reading> readings;
    
    public Sensor() {
        this.readings = new ArrayList<>();
        this.status = "ACTIVE";
        this.currentValue = 0.0;
    }
    
    public Sensor(String id, String type, String roomId) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.status = "ACTIVE";
        this.currentValue = 0.0;
        this.readings = new ArrayList<>();
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    public List<Reading> getReadings() { return readings; }
    public void setReadings(List<Reading> readings) { this.readings = readings; }
    
    public void addReading(Reading reading) {
        this.readings.add(reading);
        this.currentValue = reading.getValue();
    }
}