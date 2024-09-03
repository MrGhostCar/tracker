
# Tracker
Project for tracking vehicle movements.

## How to start all components in development mode:
(server, front-end, python script:)

### Server setup - in the java server root folder:
Code repository: https://github.com/MrGhostCar/tracker <br>
To start DB (postgis) the only dependency needed for the server, port 5435 and 5430 must be free:
```
docker compose up
```
Set up a run configuration in IntelliJ for TrackerApplication and run that. <br>
(Or alternatvely you can start the server from command line:) 
```
./mvnw spring-boot:run
```
## Documentation
Doc available in swaggerUI: <br>
http://localhost:5000/swagger-ui/index.html <br>
The OpenAPI specification is available as JSON at:<br>
http://localhost:5000/v3/api-docs

## Possible future improvements
- Performance bottleneck investigations (client?)
- Setting up transaction boundaries in server
- Some full integration tests
- Get rid of test Postgis instance -> some in-memory testing DB supporting geometric Points would be appropriate
- Extract notification controller into a new microservice, dockerize both service
- Performance: set up DB Indexes for quicker Coordinate lookup
- Set up explicit SQL schema to support the above
- Nicer front-end layout, scrollable notification panel
