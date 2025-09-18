# Java Application Dockerfile for Insurance Management System
FROM openjdk:8-jdk-alpine

# Install required packages
RUN apk add --no-cache bash

# Set working directory
WORKDIR /app

# Create lib directory
RUN mkdir -p lib

# Download MySQL Connector JAR using ADD command
ADD https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar lib/mysql-connector-java-8.0.33.jar

# Copy source files
COPY src/ src/
COPY pom.xml .

# Compile the application (use : for Unix classpath separator)
RUN javac -cp ".:lib/mysql-connector-java-8.0.33.jar" -d . src/portal/*.java

# Create startup script
RUN echo '#!/bin/bash' > /app/start.sh && \
    echo 'echo "Waiting for MySQL to be ready..."' >> /app/start.sh && \
    echo 'sleep 10' >> /app/start.sh && \
    echo 'java -cp ".:lib/mysql-connector-java-8.0.33.jar" portal.Login' >> /app/start.sh && \
    chmod +x /app/start.sh

# Expose any ports if needed (for future web interface)
EXPOSE 8080

# Run the application
CMD ["/app/start.sh"]
