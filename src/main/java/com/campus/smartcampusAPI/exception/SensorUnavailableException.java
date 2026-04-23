package com.campus.exception;

public class SensorUnavailableException extends RuntimeException {
    private String sensorId;
    private String status;
    
    public SensorUnavailableException(String sensorId, String status) {
        super("Sensor " + sensorId + " is in " + status + " mode");
        this.sensorId = sensorId;
        this.status = status;
    }
    
    public String getSensorId() { return sensorId; }
    public String getStatus() { return status; }
}