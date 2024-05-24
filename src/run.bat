@echo off
cls
C:

echo Compiling project, please wait...
javac index.java
echo Done!
java index

:loop
pause
javac index.java
java index

goto loop
goto C