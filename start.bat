@echo off
echo 🚀 Starting Ledger App Migration...

REM Check if ports are available (Windows version)
echo 🔍 Checking ports...

REM Start backend
echo 🔧 Starting Spring Boot backend...
cd backend
start "Spring Boot Backend" cmd /k "mvnw.cmd spring-boot:run"
cd ..

REM Wait for backend to start
echo ⏳ Waiting for backend to start...
timeout /t 10 /nobreak > nul

REM Start frontend
echo 🎨 Starting React frontend...
cd frontend

REM Install dependencies if node_modules doesn't exist
if not exist "node_modules" (
    echo 📦 Installing frontend dependencies...
    npm install
)

echo ✅ Frontend is starting on http://localhost:5173
echo 🌐 Open http://localhost:5173 in your browser
echo.
echo 📊 Backend API: http://localhost:8080
echo 🗄️  H2 Console: http://localhost:8080/h2-console
echo.
echo Press any key to stop this script (applications will continue running)
pause > nul
