# Insurance Management System - Setup Instructions

## Prerequisites Installation

### Step 1: Install Java Development Kit (JDK)

1. Download JDK 8 or higher from:
   - Oracle: https://www.oracle.com/java/technologies/downloads/
   - OpenJDK: https://adoptium.net/

2. Install JDK:
   - Run the installer
   - Follow the installation wizard
   - Note the installation path (e.g., `C:\Program Files\Java\jdk-17`)

3. Set up Environment Variables:
   - Open System Properties → Advanced → Environment Variables
   - Add new System Variable:
     - Variable name: `JAVA_HOME`
     - Variable value: `C:\Program Files\Java\jdk-17` (your JDK path)
   - Edit PATH variable and add: `%JAVA_HOME%\bin`

4. Verify installation:
   ```
   java -version
   javac -version
   ```

### Step 2: Install MySQL

1. Download MySQL Community Server from:
   https://dev.mysql.com/downloads/mysql/

2. Install MySQL:
   - Run the installer
   - Choose "Developer Default" setup
   - Set root password (remember this!)
   - Keep MySQL running as Windows Service

3. Install MySQL Workbench (optional but recommended):
   https://dev.mysql.com/downloads/workbench/

### Step 3: Download MySQL Connector/J

1. Download from: https://dev.mysql.com/downloads/connector/j/
2. Extract the JAR file (mysql-connector-java-8.0.33.jar)
3. Copy to: `d:\java\InsuranceSystem\lib\`

## Database Setup

1. Open Command Prompt as Administrator
2. Navigate to project directory:
   ```
   cd d:\java\InsuranceSystem
   ```

3. Connect to MySQL:
   ```
   mysql -u root -p
   ```
   Enter your MySQL root password when prompted

4. Run the database setup:
   ```sql
   source database_setup.sql;
   ```

5. Verify database creation:
   ```sql
   SHOW DATABASES;
   USE insurance_db;
   SHOW TABLES;
   ```

6. Exit MySQL:
   ```sql
   exit;
   ```

## Compilation and Running

### Option 1: Using Batch Files (Recommended)

1. Double-click `compile.bat` to compile the project
2. Double-click `run.bat` to run the application

### Option 2: Manual Compilation

1. Open Command Prompt in project directory
2. Compile all Java files:
   ```
   javac -cp ".;lib\mysql-connector-java-8.0.33.jar" src\portal\*.java
   ```

3. Run the application:
   ```
   java -cp ".;src;lib\mysql-connector-java-8.0.33.jar" portal.Login
   ```

## Troubleshooting

### Java Not Found
- Ensure JDK is installed
- Check JAVA_HOME environment variable
- Restart Command Prompt after setting environment variables

### MySQL Connection Failed
- Ensure MySQL service is running:
  ```
  net start MySQL80
  ```
- Check MySQL credentials in the code
- Verify firewall settings

### Class Not Found Error
- Ensure MySQL Connector JAR is in lib folder
- Check classpath in compile/run commands

### Database Not Found
- Run database_setup.sql script
- Verify database name is 'insurance_db'

## Quick Test

After setup, test with:
- Username: admin
- Password: admin

You should see the main menu with options for:
- Customer Registration
- Customer Modification
- Policy Registration
- Policy Modification
