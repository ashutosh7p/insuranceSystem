# Complete Commands to Run Insurance System with Docker

## Navigate to Project Directory First
```powershell
cd d:\java\InsuranceSystem
```

## Option 1: Using Docker Compose (Recommended)

### Build and Start All Containers
```powershell
# Navigate to project directory
cd d:\java\InsuranceSystem

# Build and start containers in background
docker-compose up -d

# Or build first, then run
docker-compose build
docker-compose up -d
```

### View Logs
```powershell
# View all logs
docker-compose logs

# Follow logs in real-time
docker-compose logs -f

# View specific container logs
docker-compose logs mysql-db
docker-compose logs insurance-app
```

### Stop and Remove Containers
```powershell
# Stop containers
docker-compose stop

# Stop and remove containers
docker-compose down

# Remove containers and volumes (deletes data)
docker-compose down -v
```

## Option 2: Using Batch Files

```powershell
# Navigate to project directory
cd d:\java\InsuranceSystem

# Build containers
.\docker-build.bat

# Run containers
.\docker-run.bat

# Or run standalone version
.\docker-standalone.bat
```

## Option 3: Manual Docker Commands

### Build Images
```powershell
# Navigate to project directory
cd d:\java\InsuranceSystem

# Build MySQL image
docker build -f Dockerfile.mysql -t insurance-mysql .

# Build application image
docker build -f Dockerfile -t insurance-app .
```

### Run MySQL Container
```powershell
docker run -d --name insurance-mysql `
  -e MYSQL_ROOT_PASSWORD=root123 `
  -e MYSQL_DATABASE=insurance_db `
  -e MYSQL_USER=insurance_user `
  -e MYSQL_PASSWORD=insurance123 `
  -p 3307:3306 `
  -v ${PWD}/database_setup.sql:/docker-entrypoint-initdb.d/init.sql `
  mysql:8.0
```

### Run Application Container
```powershell
docker run -it --rm --name insurance-app `
  --link insurance-mysql:mysql `
  -e DB_HOST=insurance-mysql `
  -e DB_PORT=3306 `
  -e DB_NAME=insurance_db `
  -e DB_USER=insurance_user `
  -e DB_PASSWORD=insurance123 `
  insurance-app
```

## Verify Installation

### Check Running Containers
```powershell
docker ps
```

### Test MySQL Connection
```powershell
# Connect to MySQL from host
docker exec -it insurance-mysql mysql -u insurance_user -pinsurance123 insurance_db

# Or using root
docker exec -it insurance-mysql mysql -u root -proot123
```

### View Database Tables
```sql
-- Once connected to MySQL
SHOW TABLES;
SELECT * FROM n_customer;
SELECT * FROM n_policy;
EXIT;
```

## Quick Start (Copy & Paste)

```powershell
# Complete sequence to start everything
cd d:\java\InsuranceSystem
docker-compose build
docker-compose up -d
docker ps
```

## Troubleshooting Commands

### If containers fail to start
```powershell
# Check logs
docker-compose logs

# Rebuild without cache
docker-compose build --no-cache
docker-compose up -d
```

### Clean up everything
```powershell
# Stop all containers
docker stop $(docker ps -aq)

# Remove all containers
docker rm $(docker ps -aq)

# Remove images
docker rmi insurance-mysql insurance-app

# Clean docker system
docker system prune -a
```
