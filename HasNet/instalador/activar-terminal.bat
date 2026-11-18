@echo off
setlocal
net session >nul 2>&1
if %errorlevel% neq 0 (
    powershell -NoProfile -Command "Start-Process -FilePath '%~f0' -Verb RunAs"
    exit /b
)

set "CARPETA=%~dp0"
set "JAR=%CARPETA%ActivadorHasNet.jar"
if not exist "%JAR%" set "JAR=%CARPETA%salida\Activador\ActivadorHasNet.jar"
if not exist "%JAR%" (
    echo No se encontro ActivadorHasNet.jar en "%CARPETA%" ni en "%CARPETA%salida\Activador".
    echo Compile HasNet o copie activar-terminal.bat junto a ActivadorHasNet.jar.
    pause
    exit /b 1
)

for %%I in ("%JAR%") do cd /d "%%~dpI"
echo Iniciando la herramienta de activacion desde %CD%
java -jar "ActivadorHasNet.jar"
if errorlevel 1 (
    echo.
    echo La herramienta de activacion termino con error. Revise los mensajes anteriores.
    pause
)