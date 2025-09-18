@echo off
echo ========================================
echo Running Insurance System with Docker
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

echo Starting containers using docker-compose...
echo.

docker-compose up -d

if errorlevel 1 (
    echo ERROR: Failed to start containers
    echo Make sure you have run docker-build.bat first
    pause
    exit /b 1
)

echo.
echo ========================================
echo Containers started successfully!
echo ========================================
echo.
echo MySQL Database:
echo   - Host: localhost
echo   - Port: 3307
echo   - Database: insurance_db
echo   - Username: insurance_user
echo   - Password: insurance123
echo   - Root Password: root123
echo.
echo To view logs: docker-compose logs -f
echo To stop containers: docker-compose down
echo To remove containers and data: docker-compose down -v
echo.
echo The application is running in the container.
echo To interact with it: docker exec -it insurance-app bash
echo.
pause
