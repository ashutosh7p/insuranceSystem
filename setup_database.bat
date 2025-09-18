@echo off
echo ========================================
echo Database Setup for Insurance System
echo ========================================
echo.

echo This script will create the insurance_db database
echo and required tables with sample data.
echo.
echo Please enter your MySQL root password when prompted.
echo.

mysql -u root -p < database_setup.sql

if errorlevel 1 (
    echo.
    echo ERROR: Database setup failed!
    echo Please ensure MySQL is installed and running.
    echo Check your MySQL root password.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Database setup completed successfully!
echo ========================================
echo.
echo Database: insurance_db
echo Tables created: n_customer, n_policy
echo Sample data inserted: 3 customers with policies
echo.
pause
