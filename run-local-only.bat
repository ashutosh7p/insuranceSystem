@echo off
echo ========================================
echo Running Insurance System Locally
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed locally
    echo Please install Java JDK from:
    echo https://adoptium.net/ or https://www.oracle.com/java/technologies/downloads/
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
    if errorlevel 1 (
        echo ERROR: Compilation failed
        pause
        exit /b 1
    )
)

echo.
echo ========================================
echo Starting MySQL Database in Docker
echo ========================================
echo.

REM Start MySQL container
docker run -d --name insurance-mysql-local -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=insurance_db -e MYSQL_USER=root -e MYSQL_PASSWORD="" -p 3306:3306 mysql:8.0 >nul 2>&1

if errorlevel 1 (
    echo MySQL container already exists, starting it...
    docker start insurance-mysql-local >nul 2>&1
)

echo Waiting for MySQL to be ready (20 seconds)...
timeout /t 20 /nobreak >nul

echo.
echo Importing database schema...
docker exec -i insurance-mysql-local mysql -u root -proot123 insurance_db < database_setup.sql 2>nul

echo.
echo ========================================
echo Starting Insurance Management System GUI
echo ========================================
echo.
echo Database Connection:
echo   Host: localhost
echo   Port: 3306
echo   Database: insurance_db
echo   Username: root
echo   Password: (empty)
echo.
echo Default Login:
echo   Username: admin
echo   Password: admin
echo.
echo ========================================
echo.

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
