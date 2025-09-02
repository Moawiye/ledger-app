#!/bin/bash

echo "🚀 Starting Ledger App..."

# Wait a moment for the system to stabilize
sleep 5

# Start the Spring Boot application
cd backend
java -jar target/ledger-backend-0.0.1-SNAPSHOT.jar
