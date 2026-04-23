package com.campus.exception;

public class LinkedResourceNotFoundException extends RuntimeException {
    private String resourceType;
    private String resourceId;
    
    public LinkedResourceNotFoundException(String resourceType, String resourceId) {
        super(resourceType + " with ID '" + resourceId + "' does not exist");
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }
    
    public String getResourceType() { return resourceType; }
    public String getResourceId() { return resourceId; }
}