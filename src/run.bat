@echo off
cls
C:

echo Compiling project, please wait...
javac index.java
echo Done!
cls
java index

:loop
pause
cls
javac index.java
java index

goto loop
goto C