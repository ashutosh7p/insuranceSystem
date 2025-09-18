@echo off
echo ========================================
echo Running Insurance System Locally with Docker MySQL
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

echo Starting MySQL container only...
docker run -d --name insurance-mysql-local ^
    -e MYSQL_ROOT_PASSWORD=root123 ^
    -e MYSQL_DATABASE=insurance_db ^
    -e MYSQL_USER=insurance_user ^
    -e MYSQL_PASSWORD=insurance123 ^
    -p 3307:3306 ^
    -v %cd%/database_setup.sql:/docker-entrypoint-initdb.d/init.sql ^
    mysql:8.0

if errorlevel 1 (
    echo MySQL container already exists, starting it...
    docker start insurance-mysql-local
)

echo Waiting for MySQL to be ready (15 seconds)...
timeout /t 15 /nobreak >nul

echo.
echo ========================================
echo MySQL is running on localhost:3307
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed locally
    echo Please install Java JDK to run the GUI application
    pause
    exit /b 1
)

REM Check if MySQL connector exists
if not exist "lib\mysql-connector-java-8.0.33.jar" (
    echo Creating lib directory...
    mkdir lib 2>nul
    echo.
    echo Downloading MySQL Connector...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar' -OutFile 'lib\mysql-connector-java-8.0.33.jar'"
)

REM Compile if needed
if not exist "build\portal\Login.class" (
    echo Compiling application...
    mkdir build 2>nul
    javac -cp ".;lib\mysql-connector-java-8.0.33.jar" -d build src\portal\*.java
)

echo.
echo Starting Insurance Management System GUI...
echo.
echo Database Connection:
echo   Host: localhost
echo   Port: 3307
echo   Database: insurance_db
echo   Username: insurance_user
echo   Password: insurance123
echo.
echo Default Login:
echo   Username: admin
echo   Password: admin
echo.

REM Set environment variables for database connection
set DB_HOST=localhost
set DB_PORT=3307
set DB_NAME=insurance_db
set DB_USER=insurance_user
set DB_PASSWORD=insurance123

REM Run the GUI application locally
java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login

echo.
echo Application closed.
echo.
echo Stop MySQL container? (Y/N)
set /p choice=
if /i "%choice%"=="Y" (
    docker stop insurance-mysql-local
    docker rm insurance-mysql-local
    echo MySQL container stopped and removed.
)

pause
