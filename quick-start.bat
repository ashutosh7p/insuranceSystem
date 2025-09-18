@echo off
echo ========================================
echo Quick Start - Insurance Management System
echo ========================================
echo.

REM Set environment variables for port 3307
set DB_HOST=localhost
set DB_PORT=3307
set DB_NAME=insurance_db
set DB_USER=root
set DB_PASSWORD=root123

REM Check if MySQL container is running
docker ps 2>nul | findstr insurance-mysql-local >nul
if errorlevel 1 (
    echo Starting MySQL container on port 3307...
    docker run -d --name insurance-mysql-local -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=insurance_db -p 3307:3306 mysql:8.0
    echo Waiting for MySQL to start (20 seconds)...
    timeout /t 20 /nobreak >nul
    echo Importing database...
    type database_setup.sql | docker exec -i insurance-mysql-local mysql -u root -proot123 insurance_db 2>nul
) else (
    echo MySQL container is already running on port 3307
)

echo.
echo Compiling application...
javac -cp ".;lib\mysql-connector-java-8.0.33.jar" -d build src\portal\*.java 2>nul

echo.
echo ========================================
echo Starting GUI Application
echo ========================================
echo.
echo Database: localhost:3307/insurance_db
echo Login: admin / admin
echo.

REM Run with port 3307
java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login

pause
