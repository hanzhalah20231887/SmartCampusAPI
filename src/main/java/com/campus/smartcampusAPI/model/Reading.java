package com.campus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reading {
    private String id;
    private double value;
    private String timestamp;
    private String unit;
    
    public Reading() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    public Reading(String id, double value, String unit) {
        this.id = id;
        this.value = value;
        this.unit = unit;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}