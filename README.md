# Smart Campus API

A RESTful API for managing university campus rooms, sensors, and sensor readings using JAX-RS (Jersey) on Apache Tomcat. 
Built as part of the 5COSC022C Client-Server Architectures coursework.

## Author Info
- **Name:** [Hanzhalah Misfath]
- **Student ID:** [20231887 / W2120250]
- **Module:** 5COSC022C Client-Server Architectures
- **Date:** 23rd April 2026

## Table of Contents
1. [Technology Stack](#technology-stack)
2. [Setup Instructions](#setup-instructions)
3. [API Endpoints](#api-endpoints)
4. [Sample curl Commands](#sample-curl-commands)
5. [Error Responses](#error-responses)
6. [Project Structure](#project-structure)
7. [Report Answers](#report-answers)

## Technology Stack used
- Java 11
- JAX-RS (Jersey 2.35)
- Apache Tomcat 9/10
- Maven 3.6+
- No database (in-memory HashMap storage)

## Setup Instructions

### Prerequisites

Before you begin, ensure you have the following installed:

```bash
 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/SmartCampusAPI.git
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
----

Method	Endpoint            Description
GET	/                   API discovery
GET	/rooms              List all rooms
POST	/rooms              Create room
GET	/rooms/{id}         Get room by ID
DELETE	/rooms/{id}         Delete room
GET	/sensors            List sensors
GET	/sensors?type={type}	Filter sensors by type
POST	/sensors                Create sensor
GET	/sensors/{id}/readings	Get reading history
POST	/sensors/{id}/readings	Add reading
PUT	/sensors/{id}/maintenance	Set maintenance mode
PUT	/sensors/{id}/active	Set active mode


