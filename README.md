# Smart Campus API

A RESTful API for managing university campus rooms, sensors, and sensor readings using JAX-RS (Jersey) on Apache Tomcat. 
Built as part of the 5COSC022C Client-Server Architectures coursework.

## Author Info
- **Name:** [Hanzhalah Misfath]
- **Student ID:** [20231887 / W2120250]
- **Module:** 5COSC022C Client-Server Architectures
- **Date:** 23rd April 2026
- **Git Repository:** https://github.com/hanzhalah20231887/SmartCampusAPI.git

## Table of Contents
1. [Technology Stack](#technology-stack)
2. [Setup Instructions](#setup-instructions)
3. [API Endpoints](#api-endpoints)
4. [Sample curl Commands](#sample-curl-commands)
5. [Error Responses](#error-responses)
6. [Report Answers](#report-answers)

## Technology Stack used
- Java 11
- JAX-RS (Jersey 2.35)
- Apache Tomcat 9/10
- Maven 3.6+
- No database (in-memory HashMap storage)

## Setup Instructions

### Prerequisites

Before you begin, ensure you have the following installed:

bash
 1. Clone the repository
git clone https://github.com/hanzhalah20231887/SmartCampusAPI
cd SmartCampusAPI

 2. Build the WAR file
mvn clean package

 3. Copy WAR to Tomcat webapps directory
cp target/SmartCampusAPI.war $TOMCAT_HOME/webapps/

 4. Start Tomcat
$TOMCAT_HOME/bin/startup.sh

 On Windows:
 %TOMCAT_HOME%\bin\startup.bat

 5. Test the API
curl http://localhost:8080/SmartCampusAPI/api/v1/ . 

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/` | API discovery |
| GET | `/api/v1/rooms` | List all rooms |
| POST | `/api/v1/rooms` | Create a room |
| GET | `/api/v1/rooms/{id}` | Get room by ID |
| DELETE | `/api/v1/rooms/{id}` | Delete a room |
| GET | `/api/v1/sensors` | List all sensors |
| GET | `/api/v1/sensors?type={type}` | Filter sensors by type |
| POST | `/api/v1/sensors` | Create a sensor |
| GET | `/api/v1/sensors/{id}` | Get sensor by ID |
| GET | `/api/v1/sensors/{id}/readings` | Get reading history |
| POST | `/api/v1/sensors/{id}/readings` | Add a reading |
| PUT | `/api/v1/sensors/{id}/maintenance` | Set maintenance mode |
| PUT | `/api/v1/sensors/{id}/active` | Set active mode |

## Sample Curl Commands

# Discovery
curl http://localhost:8080/SmartCampusAPI/api/v1/

# Create room
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"Lab A","building":"Science","floor":2}'

# Create sensor
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d '{"type":"CO2","roomId":"room1"}'

# Filter sensors
curl "http://localhost:8080/SmartCampusAPI/api/v1/sensors?type=CO2"

# Add reading
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/sensor1/readings \
  -H "Content-Type: application/json" \
  -d '{"value":425.5,"unit":"ppm"}'

# Get readings
curl http://localhost:8080/SmartCampusAPI/api/v1/sensors/sensor1/readings

## Error Responses

| Status | Scenario | Response |
|--------|----------|----------|
| 404 | Resource not found | `{"error":"Not found"}` |
| 409 | Delete room with sensors | `{"error":"Conflict","message":"Room has active sensors"}` |
| 422 | Sensor with invalid roomId | `{"error":"Unprocessable Entity","message":"Room does not exist"}` |
| 403 | Add reading in maintenance mode | `{"error":"Forbidden","message":"Sensor in maintenance mode"}` |
| 500 | Unexpected error | `{"error":"Internal Server Error","message":"Unexpected error"}` |

## Report Answers

#Question 1.1: JAX-RS Lifecycle
JAX-RS resources are request-scoped - a new instance per request. Shared services must be thread-safe. I used ConcurrentHashMap and synchronized methods to prevent race conditions.

#Question 1.2: HATEOAS Benefits
HATEOAS makes APIs self-discoverable. My discovery endpoint returns resource links. Benefits: clients don't hardcode URLs, server can change URLs without breaking clients.

#Question 2.1: ID-only vs Full Objects
IDs only saves bandwidth but requires multiple requests. Full objects use more bandwidth but single request. I return full objects as dataset is small.

#Question 2.2: DELETE Idempotency
DELETE is idempotent. First request returns 204, subsequent requests return 404. Server state after multiple requests is same as after one.

#Question 3.1: @Consumes Mismatch
JAX-RS returns 415 Unsupported Media Type automatically. Request never reaches my resource method.

#Question 3.2: QueryParam vs PathParam
Query parameters are optional, support multiple filters, and ordering doesn't matter. Path parameters imply hierarchy and are rigid. QueryParam is superior for filtering.

#Question 4.1: Sub-Resource Locator Benefits
Separates concerns, improves maintainability, enables independent testing, and prevents massive controller classes.

#Question 5.1: 422 vs 404
404 means "URL doesn't exist". 422 means "URL exists but request data has semantic errors". For invalid roomId reference, 422 is correct.

#Question 5.2: Stack Trace Security Risks
Stack traces expose file paths, library versions, and framework details. Attackers use this to find known vulnerabilities. My global exception mapper hides all stack traces from clients.