
# Tracker
Project for tracking vehicle movements.

## How to start all components in development mode:
(server, front-end, python script:)

### Server setup - in the java server root folder:
Code repository: https://github.com/MrGhostCar/tracker <br>
To start DB (postgis) the only dependency needed for the server, port 5435 must be free:
```
docker compose up
```
Set up a run configuration in IntelliJ for TrackerApplication and run that. <br>
(Or alternatvely you can start the server from command line:) 
```
./mvnw spring-boot:run
```
### Front-end setup - in front-end root folder:
Code repository: https://github.com/MrGhostCar/tracker-fr <br>
Install front-end dependencies:
```
npm install
```
To start front-end service:
```
npm run start
```
Once the map is displayed, each vehicle-marker can be clicked, to show it's ID.

### Python script launch - in python script folder (same as in homework pdf): 
Install:
```
cd vehicles
python3 -m venv venv
. venv/bin/activate
pip install -r requirements.txt
```
Usage:
```
cd vehicles
. venv/bin/activate
python vehicles.py 127.0.0.1 5000
```

## Documentation
Doc available in swaggerUI: <br>
http://localhost:5000/swagger-ui/index.html <br>
The OpenAPI specification is available as JSON at:<br>
http://localhost:5000/v3/api-docs