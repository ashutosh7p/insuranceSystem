# Docker Setup for Insurance Management System

## Overview
This setup provides a complete containerized environment for the Insurance Management System with MySQL database.

## Prerequisites
- Docker Desktop installed (https://www.docker.com/products/docker-desktop)
- Docker Desktop running

## Quick Start

### Option 1: Using Docker Compose (Recommended)
```bash
# Build and run all containers
docker-compose up -d

# View logs
docker-compose logs -f

# Stop containers
docker-compose down

# Remove containers and data
docker-compose down -v
```

### Option 2: Using Batch Scripts
1. **Build containers**: Double-click `docker-build.bat`
2. **Run containers**: Double-click `docker-run.bat`
3. **Standalone version**: Double-click `docker-standalone.bat`

## Container Details

### MySQL Container
- **Image**: MySQL 8.0
- **Container Name**: insurance-mysql
- **Port**: 3307 (mapped from 3306)
- **Database**: insurance_db
- **Credentials**:
  - Root: root / root123
  - User: insurance_user / insurance123
- **Data**: Persisted in Docker volume

### Application Container
- **Image**: OpenJDK 8
- **Container Name**: insurance-app
- **Dependencies**: MySQL Connector JAR (auto-downloaded)
- **Network**: Linked to MySQL container

## Files Created

### Docker Configuration Files
- `Dockerfile` - Java application container
- `Dockerfile.mysql` - MySQL database container
- `Dockerfile.app-standalone` - Standalone app with embedded connector
- `docker-compose.yml` - Orchestration configuration
- `.dockerignore` - Files to exclude from Docker context

### Batch Scripts
- `docker-build.bat` - Build Docker images
- `docker-run.bat` - Run containers with docker-compose
- `docker-standalone.bat` - Run standalone version

### Database Configuration
- `DatabaseConfig.java` - Dynamic database connection configuration
- Supports both local and Docker environments
- Auto-detects environment variables

## Database Connection

### From Docker Container
```
Host: mysql-db (or insurance-mysql-standalone)
Port: 3306
Database: insurance_db
User: insurance_user
Password: insurance123
```

### From Host Machine
```
Host: localhost
Port: 3307
Database: insurance_db
User: insurance_user
Password: insurance123
```

## Environment Variables
The application uses these environment variables when running in Docker:
- `DB_HOST` - Database host
- `DB_PORT` - Database port
- `DB_NAME` - Database name
- `DB_USER` - Database username
- `DB_PASSWORD` - Database password

## Commands Reference

### Build Images
```bash
# Build MySQL image
docker build -f Dockerfile.mysql -t insurance-mysql .

# Build application image
docker build -f Dockerfile -t insurance-app .
```

### Run Containers Manually
```bash
# Run MySQL
docker run -d --name insurance-mysql \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=insurance_db \
  -e MYSQL_USER=insurance_user \
  -e MYSQL_PASSWORD=insurance123 \
  -p 3307:3306 \
  mysql:8.0

# Run Application
docker run -it --rm --name insurance-app \
  --link insurance-mysql:mysql \
  -e DB_HOST=insurance-mysql \
  -e DB_PORT=3306 \
  -e DB_NAME=insurance_db \
  -e DB_USER=insurance_user \
  -e DB_PASSWORD=insurance123 \
  insurance-app
```

### Container Management
```bash
# List running containers
docker ps

# View container logs
docker logs insurance-mysql
docker logs insurance-app

# Access MySQL shell
docker exec -it insurance-mysql mysql -u root -proot123

# Access application container
docker exec -it insurance-app bash

# Stop containers
docker stop insurance-mysql insurance-app

# Remove containers
docker rm insurance-mysql insurance-app

# Remove images
docker rmi insurance-mysql insurance-app
```

## Troubleshooting

### Docker Not Running
- Start Docker Desktop
- Wait for Docker to fully initialize
- Run `docker info` to verify

### Port Already in Use
- MySQL port 3307 might be in use
- Change port in docker-compose.yml
- Or stop conflicting service

### Database Connection Failed
- Ensure MySQL container is healthy
- Check environment variables
- Verify network connectivity between containers

### GUI Application Issues
- For GUI support, additional X11 configuration may be needed
- Consider running application locally with Docker MySQL only

## Testing the Setup

1. Start containers:
   ```bash
   docker-compose up -d
   ```

2. Check container status:
   ```bash
   docker ps
   ```

3. View application logs:
   ```bash
   docker-compose logs insurance-app
   ```

4. Connect to MySQL:
   ```bash
   docker exec -it insurance-mysql mysql -u insurance_user -pinsurance123 insurance_db
   ```

5. Query sample data:
   ```sql
   SELECT * FROM n_customer;
   SELECT * FROM n_policy;
   ```

## Production Considerations

- Use secrets management for passwords
- Set up proper volume backups
- Configure resource limits
- Use specific version tags instead of 'latest'
- Set up health checks and monitoring
- Configure proper logging
- Use environment-specific configurations
