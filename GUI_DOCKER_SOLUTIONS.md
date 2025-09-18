# Solutions for Running GUI Application with Docker

## The Problem
The Java Swing GUI application cannot run inside Docker containers because they don't have display capabilities (X11 server). This causes the `HeadlessException`.

## Solution 1: Run GUI Locally with Docker MySQL (RECOMMENDED)

### Quick Start
```powershell
cd d:\java\InsuranceSystem
.\run-local-with-docker-db.bat
```

This will:
1. Start MySQL in Docker (port 3307)
2. Run the GUI application locally on Windows
3. Connect to the Docker MySQL database

### Manual Commands
```powershell
# Start only MySQL in Docker
docker-compose -f docker-compose-headless.yml up -d

# Compile and run GUI locally
javac -cp ".;lib\mysql-connector-java-8.0.33.jar" -d build src\portal\*.java
java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login
```

## Solution 2: Console Application in Docker

### Run Console Version
```powershell
# Build and run console version
docker-compose -f docker-compose-console.yml up -d

# Interact with console app
docker exec -it insurance-console sh -c "java -cp 'build:lib/mysql-connector-java-8.0.33.jar' portal.ConsoleApp"
```

## Solution 3: Stop Current Containers and Run Locally

### Step 1: Stop existing containers
```powershell
docker-compose down
```

### Step 2: Start MySQL only
```powershell
docker run -d --name mysql-only -p 3307:3306 ^
  -e MYSQL_ROOT_PASSWORD=root123 ^
  -e MYSQL_DATABASE=insurance_db ^
  -e MYSQL_USER=insurance_user ^
  -e MYSQL_PASSWORD=insurance123 ^
  mysql:8.0
```

### Step 3: Wait for MySQL to initialize
```powershell
timeout /t 15
```

### Step 4: Import database schema
```powershell
docker exec -i mysql-only mysql -u root -proot123 insurance_db < database_setup.sql
```

### Step 5: Run GUI application locally
```powershell
java -cp "build;lib\mysql-connector-java-8.0.33.jar" portal.Login
```

## Database Connection Details
- **Host**: localhost
- **Port**: 3307
- **Database**: insurance_db
- **Username**: insurance_user
- **Password**: insurance123

## Application Login
- **Username**: admin
- **Password**: admin
