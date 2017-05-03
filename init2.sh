#!/bin/bash
echo "Starting Coffee Micro-service..."
java -jar ./target/coffee-microservice-1.0.0.jar --service.registry.enabled=true --service.registry.baseUrl=http://localhost:9090 --coffee.service.baseUrl=http://localhost:9292
