@echo off
echo ========================================
echo Insurance Management System - Compiler
echo ========================================
echo.

REM Create necessary directories
if not exist "lib" mkdir lib
if not exist "build" mkdir build

echo Checking for Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK and set JAVA_HOME environment variable
    echo See SETUP_INSTRUCTIONS.md for details
    pause
    exit /b 1
)

echo.
echo Compiling Java files...
echo.

REM Check if MySQL connector exists
if not exist "lib\mysql-connector-java-8.0.33.jar" (
    echo WARNING: MySQL Connector JAR not found in lib folder
    echo Please download mysql-connector-java-8.0.33.jar
    echo and place it in the lib folder
    echo.
    echo Attempting to compile without MySQL connector...
    javac -d build src\portal\*.java
) else (
    javac -cp ".;lib\mysql-connector-java-8.0.33.jar" -d build src\portal\*.java
)

if errorlevel 1 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Compilation successful!
echo ========================================
echo.
echo You can now run the application using run.bat
echo.
pause
