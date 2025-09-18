@echo off
echo ========================================
echo Insurance Management System - Quick Start
echo ========================================
echo.

REM Set environment variables for port 3307
set DB_HOST=localhost
set DB_PORT=3307
set DB_NAME=insurance_db
set DB_USER=root
set DB_PASSWORD=root123

REM Ensure MySQL is running
echo Checking MySQL container...
docker ps | find "insurance-mysql-local" >nul 2>&1
if errorlevel 1 (
    echo MySQL container not running. Starting it now...
    docker start insurance-mysql-local >nul 2>&1
    if errorlevel 1 (
        echo Creating new MySQL container on port 3307...
        docker run -d --name insurance-mysql-local -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=insurance_db -p 3307:3306 mysql:8.0
        echo Waiting for MySQL to initialize (20 seconds)...
        timeout /t 20 /nobreak >nul
    )
) else (
    echo MySQL is already running on port 3307
)

echo.
echo Setting up database...
docker exec insurance-mysql-local mysql -u root -proot123 -e "CREATE DATABASE IF NOT EXISTS insurance_db;" 2>nul
type database_setup.sql | docker exec -i insurance-mysql-local mysql -u root -proot123 insurance_db 2>nul

echo.
echo Compiling application...
if not exist build mkdir build
javac -cp ".;lib\mysql-connector-java-8.0.33.jar" -d build src\portal\*.java 2>nul

echo.
echo ========================================
echo Starting Application
echo ========================================
echo Database: localhost:3307/insurance_db
echo Login: admin / admin
echo ========================================
echo.

java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login

pause
