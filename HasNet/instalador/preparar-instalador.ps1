param(
    [string]$Version = (Get-Date -Format "yyyy.MM.dd"),
    [string]$HostBD = "127.0.0.1",
    [string]$UsuarioBD = "hasnet",
    [string]$BaseOrigen = "bdhasnet",
    [string]$MariaDB = "C:\Users\Ceiba\Desktop\Utensilios\mariadb",
    [string]$Jdk = "",
    [switch]$SinCompilar
)

$ErrorActionPreference = "Stop"
$instalador = $PSScriptRoot
$proyecto = Split-Path $instalador -Parent
$staging = Join-Path $instalador "staging"
$salida = Join-Path $instalador "salida"

function Paso($texto) { Write-Host ""; Write-Host "== $texto" -ForegroundColor Cyan }

function Buscar-Primero($rutas) {
    foreach ($ruta in $rutas) { if ($ruta -and (Test-Path $ruta)) { return (Resolve-Path $ruta).Path } }
    return $null
}

$iscc = Buscar-Primero @("$env:LOCALAPPDATA\Programs\Inno Setup 6\ISCC.exe", "C:\Program Files (x86)\Inno Setup 6\ISCC.exe", "C:\Program Files\Inno Setup 6\ISCC.exe")
if (-not $iscc) { throw "No se encontró Inno Setup 6 (ISCC.exe)." }

if (-not $Jdk) {
    $Jdk = Buscar-Primero @($env:JAVA_HOME, (Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory -Filter "jdk-8*" -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty FullName))
}
if (-not $Jdk -or -not (Test-Path (Join-Path $Jdk "jre\bin\javaw.exe"))) { throw "No se encontró un JDK 8 con carpeta jre. Indíquelo con -Jdk." }

$paquete = Join-Path $instalador "hasnet-instalacion.dat"
if (-not (Test-Path $paquete)) { throw "Falta $paquete. Créelo con crear-paquete.bat" }

$mysql = Join-Path $MariaDB "bin\mysql.exe"
$mysqldump = Join-Path $MariaDB "bin\mysqldump.exe"
if (-not (Test-Path $mysql)) { throw "No se encontró MariaDB en $MariaDB" }

if (-not $env:MYSQL_PWD) {
    $segura = Read-Host "Contraseña del usuario $UsuarioBD en la base de desarrollo" -AsSecureString
    $env:MYSQL_PWD = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($segura))
}

if (-not $SinCompilar) {
    Paso "Compilando HasNet"
    $ant = Buscar-Primero @((Get-Command ant -ErrorAction SilentlyContinue).Source, "C:\Program Files\NetBeans-12.6\netbeans\extide\ant\bin\ant.bat")
    if (-not $ant) { throw "No se encontró Ant. Compile HasNet en NetBeans y use -SinCompilar." }
    if (-not $env:JAVA_HOME) { $env:JAVA_HOME = $Jdk }
    & $ant -q -f (Join-Path $proyecto "build.xml") jar
    if ($LASTEXITCODE -ne 0) { throw "Falló la compilación de HasNet" }
}

Paso "Preparando carpeta de armado"
if (Test-Path $staging) { Remove-Item $staging -Recurse -Force }
New-Item -ItemType Directory -Force "$staging\app\lib", "$staging\app\imagenes\conf", "$staging\activador", "$staging\mariadb\bin", "$staging\mariadb\instalacion" | Out-Null

Copy-Item (Join-Path $proyecto "dist\HasNet.jar") "$staging\app\"
Copy-Item (Join-Path $proyecto "dist\lib\*") "$staging\app\lib\" -Recurse
Copy-Item (Join-Path $proyecto "icono.ico") "$staging\app\"
Copy-Item (Join-Path $proyecto "imagenes\codigoBarra.png") "$staging\app\imagenes\"
Copy-Item (Join-Path $proyecto "imagenes\conf\logoEmpresa.png") "$staging\app\imagenes\conf\"
Copy-Item (Join-Path $salida "Activador\ActivadorHasNet.jar") "$staging\activador\"
Copy-Item $paquete "$staging\activador\"

Paso "Copiando Java 8 ($Jdk\jre)"
Copy-Item (Join-Path $Jdk "jre") "$staging\jre" -Recurse

Paso "Copiando MariaDB"
$binarios = "mysqld.exe", "mysql.exe", "mysqldump.exe", "mysqladmin.exe", "mysqlcheck.exe", "mysql_upgrade.exe", "my_print_defaults.exe", "perror.exe"
foreach ($binario in $binarios) { Copy-Item (Join-Path $MariaDB "bin\$binario") "$staging\mariadb\bin\" }
Copy-Item (Join-Path $MariaDB "share") "$staging\mariadb\share" -Recurse
foreach ($licencia in "COPYING", "COPYING.thirdparty", "README") {
    if (Test-Path (Join-Path $MariaDB $licencia)) { Copy-Item (Join-Path $MariaDB $licencia) "$staging\mariadb\" }
}

Paso "Generando carpeta de datos limpia de MariaDB"
& (Join-Path $MariaDB "bin\mysql_install_db.exe") "--datadir=$staging\mariadb\data" | Out-Host
if ($LASTEXITCODE -ne 0) { throw "Falló mysql_install_db" }
Remove-Item "$staging\mariadb\data\my.ini" -ErrorAction SilentlyContinue

Paso "Generando la base de datos inicial desde $BaseOrigen"
$semilla = "bdsemillahasnet"
$conexion = @("-h", $HostBD, "-u", $UsuarioBD, "--default-character-set=utf8")
$tablas = & $mysql @conexion -N -e "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = '$BaseOrigen' AND TABLE_TYPE = 'BASE TABLE' ORDER BY TABLE_NAME"
$vistas = & $mysql @conexion -N -e "SELECT TABLE_NAME FROM information_schema.VIEWS WHERE TABLE_SCHEMA = '$BaseOrigen' ORDER BY TABLE_NAME"
if (-not $tablas) { throw "No se encontraron tablas en $BaseOrigen" }

$sentencias = New-Object System.Collections.Generic.List[string]
$sentencias.Add("DROP DATABASE IF EXISTS ``$semilla``;")
$sentencias.Add("CREATE DATABASE ``$semilla`` DEFAULT CHARACTER SET latin1;")
foreach ($tabla in $tablas) { $sentencias.Add("CREATE TABLE ``$semilla``.``$tabla`` LIKE ``$BaseOrigen``.``$tabla``;") }

$posteriores = New-Object System.Collections.Generic.List[string]
foreach ($linea in Get-Content (Join-Path $instalador "datos-iniciales.txt") -Encoding UTF8) {
    $texto = $linea.Trim()
    if (-not $texto -or $texto.StartsWith("#")) { continue }
    if ($texto.StartsWith("sql:")) { $posteriores.Add($texto.Substring(4).Trim().TrimEnd(";") + ";"); continue }
    $partes = $texto.Split("|", 2)
    $tabla = $partes[0].Trim().ToLower()
    if ($tablas -notcontains $tabla) { Write-Warning "La tabla $tabla de datos-iniciales.txt no existe en $BaseOrigen; se omite"; continue }
    $condicion = if ($partes.Count -gt 1) { " WHERE " + $partes[1].Trim() } else { "" }
    $sentencias.Add("INSERT INTO ``$semilla``.``$tabla`` SELECT * FROM ``$BaseOrigen``.``$tabla``$condicion;")
}
$sentencias.Add("USE ``$semilla``;")
$sentencias.AddRange($posteriores)

$archivoSemilla = Join-Path $staging "semilla.sql"
[IO.File]::WriteAllLines($archivoSemilla, $sentencias, (New-Object Text.UTF8Encoding($false)))
cmd /c "`"$mysql`" -h $HostBD -u $UsuarioBD --default-character-set=utf8 < `"$archivoSemilla`""
if ($LASTEXITCODE -ne 0) { throw "Falló la creación de la base semilla" }

$archivoTablas = Join-Path $staging "tablas.sql"
$archivoVistas = Join-Path $staging "vistas.sql"
& $mysqldump @conexion --skip-add-drop-table --skip-comments --single-transaction "--result-file=$archivoTablas" $semilla
if ($LASTEXITCODE -ne 0) { throw "Falló el volcado de tablas" }
if ($vistas) {
    & $mysqldump @conexion --no-data --skip-comments --single-transaction "--result-file=$archivoVistas" $BaseOrigen @vistas
    if ($LASTEXITCODE -ne 0) { throw "Falló el volcado de vistas" }
}
& $mysql @conexion -e "DROP DATABASE IF EXISTS ``$semilla``"

$definer = 'DEFINER=`[^`]*`@`[^`]*`\s*'
$esquema = New-Object System.Text.StringBuilder
$calificador = [regex]::Escape("``$BaseOrigen``.")
[void]$esquema.AppendLine("CREATE DATABASE IF NOT EXISTS ``bdhasnet`` DEFAULT CHARACTER SET latin1;")
[void]$esquema.AppendLine("USE ``bdhasnet``;")
[void]$esquema.AppendLine(([IO.File]::ReadAllText($archivoTablas, [Text.Encoding]::UTF8) -replace $definer, ''))
if ($vistas) { [void]$esquema.AppendLine(([IO.File]::ReadAllText($archivoVistas, [Text.Encoding]::UTF8) -replace $definer, '' -replace $calificador, '`bdhasnet`.')) }
[IO.File]::WriteAllText("$staging\mariadb\instalacion\esquema-inicial.sql", $esquema.ToString(), (New-Object Text.UTF8Encoding($false)))
Remove-Item $archivoSemilla, $archivoTablas, $archivoVistas -ErrorAction SilentlyContinue
Write-Host ("Base inicial: {0} tablas, {1} vistas" -f $tablas.Count, @($vistas).Count)

Paso "Compilando el instalador (versión $Version)"
New-Item -ItemType Directory -Force $salida | Out-Null
& $iscc "/DAppVersion=$Version" "/Q" (Join-Path $instalador "HasNet.iss")
if ($LASTEXITCODE -ne 0) { throw "Falló la compilación del instalador" }

$exe = Get-ChildItem $salida -Filter "HasNet-Setup-$Version.exe" | Select-Object -First 1
Write-Host ""
Write-Host ("Instalador listo: {0} ({1:N0} MB)" -f $exe.FullName, ($exe.Length / 1MB)) -ForegroundColor Green
