@echo off
REM -------------------------------------------------------
REM CONFIGURATION: Update the path to your MySQL Connector JAR
REM -------------------------------------------------------
set MYSQL_JAR="C:\path\to\your\mysql-connector-j-8.x.x.jar"

if not exist %MYSQL_JAR% (
    echo ERROR: MySQL JAR file not found at: %MYSQL_JAR%
    echo Please edit this batch file and set MYSQL_JAR to the correct path.
    pause
    exit /b
)

cd ..\..\..
echo Compiling Project...
javac -cp .;%MYSQL_JAR% com/school/gui/MainGUI.java

echo Starting Application...
java -cp .;%MYSQL_JAR% com.school.gui.MainGUI
pause

cd "C:\Users\ZACK ZULUPP\OneDrive\Documents\SchoolManagementSystem" ; java -cp "out;lib\mysql-connector-j-9.5.0.jar" com.school.gui.MainGUI