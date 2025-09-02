#!/bin/bash

echo "🚀 Starting Ledger App Migration..."

# Function to check if a port is in use
check_port() {
    if lsof -Pi :$1 -sTCP:LISTEN -t >/dev/null ; then
        echo "❌ Port $1 is already in use. Please free up port $1 and try again."
        exit 1
    fi
}

# Check ports
echo "🔍 Checking ports..."
check_port 8080
check_port 5173

echo "✅ Ports are available"

# Start backend in background
echo "🔧 Starting Spring Boot backend..."
cd backend
./mvnw spring-boot:run > ../backend.log 2>&1 &
BACKEND_PID=$!

# Wait for backend to start
echo "⏳ Waiting for backend to start..."
sleep 10

# Check if backend is running
if curl -s http://localhost:8080/api/ledger > /dev/null; then
    echo "✅ Backend is running on http://localhost:8080"
else
    echo "❌ Backend failed to start. Check backend.log for details."
    kill $BACKEND_PID 2>/dev/null
    exit 1
fi

# Start frontend
echo "🎨 Starting React frontend..."
cd ../frontend

# Install dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
    echo "📦 Installing frontend dependencies..."
    npm install
fi

echo "✅ Frontend is starting on http://localhost:5173"
echo "🌐 Open http://localhost:5173 in your browser"
echo ""
echo "📊 Backend API: http://localhost:8080"
echo "🗄️  H2 Console: http://localhost:8080/h2-console"
echo ""
echo "Press Ctrl+C to stop both applications"

# Function to cleanup on exit
cleanup() {
    echo ""
    echo "🛑 Stopping applications..."
    kill $BACKEND_PID 2>/dev/null
    echo "✅ Applications stopped"
    exit 0
}

# Set trap to cleanup on script exit
trap cleanup SIGINT SIGTERM

# Wait for user to stop
wait
