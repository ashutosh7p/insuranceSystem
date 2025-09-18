@echo off
echo ========================================
echo Building Docker Containers for Insurance System
echo ========================================
echo.

REM Check if Docker is installed
docker --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Docker is not installed or not running
    echo Please install Docker Desktop from https://www.docker.com/products/docker-desktop
    pause
    exit /b 1
)

echo Building MySQL container...
docker build -f Dockerfile.mysql -t insurance-mysql .
if errorlevel 1 (
    echo ERROR: Failed to build MySQL container
    pause
    exit /b 1
)

echo.
echo Building Java application container...
docker build -f Dockerfile -t insurance-app .
if errorlevel 1 (
    echo ERROR: Failed to build application container
    pause
    exit /b 1
)

echo.
echo ========================================
echo Build completed successfully!
echo ========================================
echo.
echo To run the containers, use: docker-run.bat
echo.
pause
