@echo off
setlocal
cd /d "%~dp0"
set "JAR=%~dp0ActivadorHasNet.jar"
if not exist "%JAR%" set "JAR=%~dp0salida\Activador\ActivadorHasNet.jar"
if not exist "%JAR%" (
    echo No se encontro ActivadorHasNet.jar. Compile HasNet primero.
    pause
    exit /b 1
)
where java >nul 2>&1
if errorlevel 1 (
    echo No se encontro Java en este equipo.
    pause
    exit /b 1
)

set "CREDENCIALES=%~dp0credenciales-paquete.properties"
echo Se creara el paquete de instalacion en:
echo   %~dp0hasnet-instalacion.dat
echo.
if exist "%CREDENCIALES%" (
    java -cp "%JAR%" Activacion.PaqueteInstalacion crear "%~dp0hasnet-instalacion.dat" --credenciales "%CREDENCIALES%"
) else (
    java -cp "%JAR%" Activacion.PaqueteInstalacion crear "%~dp0hasnet-instalacion.dat"
)
if not errorlevel 1 if exist "%CREDENCIALES%" (
    del /q "%CREDENCIALES%"
    echo Se elimino el archivo temporal de credenciales.
)
echo.
pause