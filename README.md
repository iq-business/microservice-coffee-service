----------------------------
# Coffee Microservice

A microservice based coffee vending application in the Death Star Canteen.

----------------------------
## Pre-requisites to build the application from source:
- Java Jdk 1.8

----------------------------
## Instructions to build the application from source:

```
   > mvnw.cmd clean install
```
----------------------------
## Instructions to run the application:

### To run (No Service Registration)
```
  java -jar .\target\coffee-microservice-1.0.0.jar
```

### To run with Service Registration
```
  java -jar .\target\coffee-microservice-1.0.0.jar --service.registry.enabled=true --service.registry.baseUrl=http://localhost:9090 --coffee.service.baseUrl=http://localhost:9292
```
- ```--service.registry.enabled=true```        - True: enables service registration on startup
- ```--service.registry.baseUrl={host:port}``` - To set base url of Registry Service / API Gateway
- ```--coffee.service.baseUrl={host:port}```   - To set base url of this 'Coffee microservice' instance to be used for service registration.