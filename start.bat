@echo off
echo ========================================
echo Insurance Management System
echo ========================================
echo.

set DB_HOST=localhost
set DB_PORT=3307
set DB_NAME=insurance_db
set DB_USER=root
set DB_PASSWORD=root123

echo Starting application...
echo Database: localhost:3307
echo.

java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login

pause
