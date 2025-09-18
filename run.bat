@echo off
echo ========================================
echo Insurance Management System
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK and set JAVA_HOME environment variable
    echo See SETUP_INSTRUCTIONS.md for details
    pause
    exit /b 1
)

REM Check if compiled classes exist
if not exist "build\portal\Login.class" (
    echo ERROR: Application not compiled!
    echo Please run compile.bat first
    pause
    exit /b 1
)

echo Starting Insurance Management System...
echo.
echo Default Login Credentials:
echo Username: admin
echo Password: admin
echo.
echo ========================================
echo.

REM Run the application
if exist "lib\mysql-connector-java-8.0.33.jar" (
    java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login
) else (
    echo WARNING: MySQL Connector JAR not found
    echo Running without database support...
    java -cp "build" portal.Login
)

if errorlevel 1 (
    echo.
    echo ERROR: Application crashed!
    echo Please check the error messages above.
)

echo.
pause
