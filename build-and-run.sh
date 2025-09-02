#!/bin/bash

echo "🔨 Building Ledger App..."

# Go to backend directory
cd backend

# Clean and build
echo "📦 Building with Maven..."
./mvnw clean package -DskipTests

# Check if JAR was created
if [ -f "target/ledger-backend-0.0.1-SNAPSHOT.jar" ]; then
    echo "✅ JAR file created successfully!"
    echo "📁 JAR location: $(pwd)/target/ledger-backend-0.0.1-SNAPSHOT.jar"
    echo "📊 JAR size: $(ls -lh target/ledger-backend-0.0.1-SNAPSHOT.jar | awk '{print $5}')"
    echo ""
    echo "🚀 Starting application..."
    java -jar target/ledger-backend-0.0.1-SNAPSHOT.jar
else
    echo "❌ JAR file not found!"
    echo "📁 Checking target directory:"
    ls -la target/
    echo ""
    echo "🔍 Checking for any JAR files:"
    find . -name "*.jar" 2>/dev/null
    exit 1
fi
