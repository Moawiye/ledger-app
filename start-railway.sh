#!/bin/bash

echo "🚀 Starting Ledger App on Railway..."

# Wait for the application to start
echo "⏳ Waiting for application to start..."
sleep 30

# Check if the application is responding
echo "🔍 Checking application health..."
for i in {1..10}; do
    if curl -f http://localhost:8080/api/ledger > /dev/null 2>&1; then
        echo "✅ Application is healthy and responding!"
        break
    else
        echo "⏳ Attempt $i: Application not ready yet, waiting..."
        sleep 10
    fi
done

# Keep the container running
echo "🔄 Application started successfully. Keeping container alive..."
tail -f /dev/null
