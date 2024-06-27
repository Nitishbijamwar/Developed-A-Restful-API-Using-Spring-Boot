

## JAVA Version 

openjdk 17.0.1 2021-10-19

OpenJDK Runtime Environment (build 17.0.1+12-39)

OpenJDK 64-Bit Server VM (build 17.0.1+12-39, mixed mode, sharing)

## Maven version

Apache Maven 3.9.6

## Build project 

mvn clean install

# Objective

The goal of this project is to learn how to develop a RESTful API with Spring Boot, using a microservices architecture. 

I created multiple endpoints for a specific element, implement CRUD (Create, Read, Update, Delete) methods, and return DTOs (Data Transfer Objects). 

The database will be in-memory for the purposes of this project.

Each web service  include JUnit testing.

# Architecture 


- RestController -> Service -> DAO
- RestController: Uses the Service to handle HTTP requests and returns responses to the client
- Service: Uses the DAO to retrieve data, applies business logic, and formats the data into DTOs.
- DAO (Data Access Object): Retrieves data from the database or memory application


# Users

## End points 

- GET /users: Retrieves all users.
- GET /users/{id}: Retrieves a user by their ID.
- POST /users: Adds a new user.
- PUT /users/{id}: Updates an existing user.
- DELETE /users/{id}: Deletes a user by their ID.

## User data 

- id (UUID)
- username (String)
- email (String)
- password (String encrypted)
- firstName (String)
- lastName (String)

## User dto 

- id (UUID)
- username (String)
- email (String)
- password (String : return a default password like Tra1n1ng@1234)
- firstName (String)
- lastName (String)
