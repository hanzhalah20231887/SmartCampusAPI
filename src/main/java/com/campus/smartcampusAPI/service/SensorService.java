package com.campus.service;

import com.campus.model.Sensor;
import com.campus.model.Reading;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SensorService {
    private static SensorService instance;
    private final Map<String, Sensor> sensors;
    private final RoomService roomService;
    
    private SensorService() {
        sensors = new ConcurrentHashMap<>();
        roomService = RoomService.getInstance();
        // Sample data
        Sensor sensor1 = new Sensor("sensor1", "CO2", "room1");
        sensors.put(sensor1.getId(), sensor1);
        roomService.addSensorToRoom("room1", sensor1.getId());
    }
    
    public static synchronized SensorService getInstance() {
        if (instance == null) {
            instance = new SensorService();
        }
        return instance;
    }
    
    public List<Sensor> getAllSensors() {
        return new ArrayList<>(sensors.values());
    }
    
    public List<Sensor> getSensorsByType(String type) {
        if (type == null || type.isEmpty()) {
            return getAllSensors();
        }
        return sensors.values().stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    public Sensor getSensor(String id) {
        return sensors.get(id);
    }
    
    public synchronized Sensor createSensor(Sensor sensor) {
        if (sensor.getId() == null || sensor.getId().isEmpty()) {
            sensor.setId(UUID.randomUUID().toString());
        }
        sensors.put(sensor.getId(), sensor);
        roomService.addSensorToRoom(sensor.getRoomId(), sensor.getId());
        return sensor;
    }
    
    public boolean sensorExists(String id) {
        return sensors.containsKey(id);
    }
    
    public synchronized Reading addReading(String sensorId, Reading reading) {
        Sensor sensor = sensors.get(sensorId);
        if (sensor == null) return null;
        if (reading.getId() == null || reading.getId().isEmpty()) {
            reading.setId(UUID.randomUUID().toString());
        }
        sensor.addReading(reading);
        return reading;
    }
    
    public List<Reading> getSensorReadings(String sensorId) {
        Sensor sensor = sensors.get(sensorId);
        return sensor != null ? sensor.getReadings() : new ArrayList<>();
    }
    
    public void updateSensorStatus(String sensorId, String status) {
        Sensor sensor = sensors.get(sensorId);
        if (sensor != null) {
            sensor.setStatus(status);
        }
    }
}