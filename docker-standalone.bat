@echo off
echo ========================================
echo Running Standalone Insurance System with Docker
echo ========================================
echo.

REM Check if Docker is running
docker info >nul 2>&1
if errorlevel 1 (
    echo ERROR: Docker is not running
    echo Please start Docker Desktop first
    pause
    exit /b 1
)

echo Building standalone application container...
docker build -f Dockerfile.app-standalone -t insurance-standalone .
if errorlevel 1 (
    echo ERROR: Failed to build container
    pause
    exit /b 1
)

echo.
echo Starting MySQL container...
docker run -d --name insurance-mysql-standalone ^
    -e MYSQL_ROOT_PASSWORD=root123 ^
    -e MYSQL_DATABASE=insurance_db ^
    -e MYSQL_USER=insurance_user ^
    -e MYSQL_PASSWORD=insurance123 ^
    -p 3307:3306 ^
    -v %cd%/database_setup.sql:/docker-entrypoint-initdb.d/init.sql ^
    mysql:8.0

echo Waiting for MySQL to initialize (30 seconds)...
timeout /t 30 /nobreak >nul

echo.
echo Starting application container...
docker run -it --rm --name insurance-app-standalone ^
    --link insurance-mysql-standalone:mysql ^
    -e DB_HOST=insurance-mysql-standalone ^
    -e DB_PORT=3306 ^
    -e DB_NAME=insurance_db ^
    -e DB_USER=insurance_user ^
    -e DB_PASSWORD=insurance123 ^
    insurance-standalone

echo.
echo ========================================
echo Application closed
echo ========================================
echo.
echo Cleaning up containers...
docker stop insurance-mysql-standalone
docker rm insurance-mysql-standalone
echo.
pause
