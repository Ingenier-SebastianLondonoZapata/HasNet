#ifndef AppVersion
  #define AppVersion "1.0.0"
#endif
#define Staging "staging"
#define Servicio "HasNetDB"
#define ReglaFirewall "HasNet MariaDB"

[Setup]
AppId={{6F1C2E4B-8A3D-4F7E-9B21-5D0C7A9E3F48}
AppName=HasNet
AppVersion={#AppVersion}
AppVerName=HasNet {#AppVersion}
AppPublisher=HasNet
DefaultDirName=C:\HasNet
DisableDirPage=yes
DefaultGroupName=HasNet
DisableProgramGroupPage=yes
OutputDir=salida
OutputBaseFilename=HasNet-Setup-{#AppVersion}
SetupIconFile=..\icono.ico
UninstallDisplayIcon={app}\icono.ico
UninstallDisplayName=HasNet
Compression=lzma2/ultra64
SolidCompression=yes
LZMAUseSeparateProcess=yes
ArchitecturesAllowed=x64compatible
ArchitecturesInstallIn64BitMode=x64compatible
PrivilegesRequired=admin
WizardStyle=modern
CloseApplications=yes
RestartApplications=no

[Languages]
Name: "es"; MessagesFile: "compiler:Languages\Spanish.isl"

[Types]
Name: "servidor"; Description: "Servidor: aplicación y base de datos en este equipo"
Name: "extension"; Description: "Extensión: aplicación que se conecta a un servidor"

[Components]
Name: "app"; Description: "Aplicación HasNet"; Types: servidor extension; Flags: fixed
Name: "bd"; Description: "Base de datos MariaDB (se inicia con Windows)"; Types: servidor

[Dirs]
Name: "{app}\imagenes"; Permissions: users-modify
Name: "{app}\rips"; Permissions: users-modify

[Files]
Source: "{#Staging}\app\HasNet.jar"; DestDir: "{app}"; Flags: ignoreversion; Components: app
Source: "{#Staging}\app\icono.ico"; DestDir: "{app}"; Flags: ignoreversion; Components: app
Source: "{#Staging}\app\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs; Components: app
Source: "{#Staging}\app\imagenes\codigoBarra.png"; DestDir: "{app}\imagenes"; Flags: onlyifdoesntexist uninsneveruninstall; Components: app
Source: "{#Staging}\app\imagenes\conf\logoEmpresa.png"; DestDir: "{app}\imagenes\conf"; Flags: onlyifdoesntexist uninsneveruninstall; Components: app
Source: "{#Staging}\jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs; Components: app

Source: "{#Staging}\mariadb\bin\*"; DestDir: "{app}\mariadb\bin"; Flags: ignoreversion; Components: bd; AfterInstall: EscribirConfiguracionMariaDB
Source: "{#Staging}\mariadb\share\*"; DestDir: "{app}\mariadb\share"; Flags: ignoreversion recursesubdirs createallsubdirs; Components: bd
Source: "{#Staging}\mariadb\COPYING*"; DestDir: "{app}\mariadb"; Flags: ignoreversion skipifsourcedoesntexist; Components: bd
Source: "{#Staging}\mariadb\data\*"; DestDir: "{app}\mariadb\data"; Flags: recursesubdirs createallsubdirs uninsneveruninstall; Components: bd; Check: EsBaseNueva
Source: "{#Staging}\mariadb\instalacion\esquema-inicial.sql"; DestDir: "{app}\mariadb\instalacion"; Flags: ignoreversion; Components: bd

Source: "{#Staging}\activador\*"; DestDir: "{tmp}\activador"; Flags: deleteafterinstall

[Icons]
Name: "{autodesktop}\HasNet"; Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\HasNet.jar"""; WorkingDir: "{app}"; IconFilename: "{app}\icono.ico"; Comment: "HasNet"
Name: "{group}\HasNet"; Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{app}\HasNet.jar"""; WorkingDir: "{app}"; IconFilename: "{app}\icono.ico"
Name: "{group}\Desinstalar HasNet"; Filename: "{uninstallexe}"

[Run]
Filename: "{app}\mariadb\bin\mysqld.exe"; Parameters: "--install {#Servicio} ""--defaults-file={app}\mariadb\my.ini"""; Flags: runhidden waituntilterminated; StatusMsg: "Registrando el servicio de base de datos..."; Components: bd; Check: not ServicioExiste
Filename: "{sys}\sc.exe"; Parameters: "config {#Servicio} start= auto"; Flags: runhidden waituntilterminated; Components: bd
Filename: "{sys}\net.exe"; Parameters: "start {#Servicio}"; Flags: runhidden waituntilterminated; StatusMsg: "Iniciando la base de datos..."; Components: bd
Filename: "{cmd}"; Parameters: "/c """"{app}\mariadb\bin\mysql.exe"" --user=root --default-character-set=utf8 < ""{app}\mariadb\instalacion\esquema-inicial.sql"" > ""{app}\mariadb\instalacion\importacion.log"" 2>&1"""; Flags: runhidden waituntilterminated; StatusMsg: "Creando la base de datos inicial (puede tardar unos minutos)..."; Components: bd; Check: EsBaseNueva
Filename: "{sys}\netsh.exe"; Parameters: "advfirewall firewall delete rule name=""{#ReglaFirewall}"""; Flags: runhidden waituntilterminated; Components: bd
Filename: "{sys}\netsh.exe"; Parameters: "advfirewall firewall add rule name=""{#ReglaFirewall}"" dir=in action=allow protocol=TCP localport=3306 remoteip=localsubnet profile=any"; Flags: runhidden waituntilterminated; StatusMsg: "Permitiendo conexiones de las extensiones..."; Components: bd
Filename: "{app}\jre\bin\javaw.exe"; Parameters: "-jar ""{tmp}\activador\ActivadorHasNet.jar"" {code:ArgumentosActivador}"; WorkingDir: "{tmp}\activador"; Description: "Activar esta terminal ahora"; Flags: postinstall waituntilterminated

[UninstallRun]
Filename: "{sys}\net.exe"; Parameters: "stop {#Servicio}"; Flags: runhidden waituntilterminated; RunOnceId: "DetenerBD"
Filename: "{app}\mariadb\bin\mysqld.exe"; Parameters: "--remove {#Servicio}"; Flags: runhidden waituntilterminated skipifdoesntexist; RunOnceId: "QuitarServicio"
Filename: "{sys}\netsh.exe"; Parameters: "advfirewall firewall delete rule name=""{#ReglaFirewall}"""; Flags: runhidden waituntilterminated; RunOnceId: "QuitarFirewall"

[Code]
var
  BaseNueva: Boolean;

function ServicioExiste: Boolean;
begin
  Result := RegKeyExists(HKLM, 'SYSTEM\CurrentControlSet\Services\{#Servicio}');
end;

function EsBaseNueva: Boolean;
begin
  Result := BaseNueva;
end;

function ArgumentosActivador(Param: String): String;
begin
  if WizardIsComponentSelected('bd') then
    Result := '--servidor --preparar-bd'
  else
    Result := '--extension';
end;

function RutaUnix(Ruta: String): String;
begin
  Result := Ruta;
  StringChangeEx(Result, '\', '/', True);
end;

procedure EscribirConfiguracionMariaDB;
var
  Contenido: String;
  Base: String;
begin
  Base := RutaUnix(ExpandConstant('{app}\mariadb'));
  Contenido :=
    '[mysqld]' + #13#10 +
    'basedir="' + Base + '"' + #13#10 +
    'datadir="' + Base + '/data"' + #13#10 +
    'port=3306' + #13#10 +
    'character-set-server=latin1' + #13#10 +
    'collation-server=latin1_swedish_ci' + #13#10 +
    'sql_mode=NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' + #13#10 +
    'lower_case_table_names=1' + #13#10 +
    'max_allowed_packet=16M' + #13#10 +
    'innodb_buffer_pool_size=128M' + #13#10 +
    'log-error="' + Base + '/data/hasnetdb.err"' + #13#10 + #13#10 +
    '[client]' + #13#10 +
    'port=3306' + #13#10;
  SaveStringToFile(ExpandConstant('{app}\mariadb\my.ini'), Contenido, False);
end;

function OtroServidorMySQL: String;
var
  Nombres: TArrayOfString;
  I: Integer;
begin
  Result := '';
  SetArrayLength(Nombres, 5);
  Nombres[0] := 'MySQL';
  Nombres[1] := 'MariaDB';
  Nombres[2] := 'MySQL80';
  Nombres[3] := 'MySQL57';
  Nombres[4] := 'MySQL56';
  for I := 0 to GetArrayLength(Nombres) - 1 do
    if RegKeyExists(HKLM, 'SYSTEM\CurrentControlSet\Services\' + Nombres[I]) then
    begin
      Result := Nombres[I];
      Exit;
    end;
end;

function NextButtonClick(CurPageID: Integer): Boolean;
var
  Otro: String;
begin
  Result := True;
  if (CurPageID = wpSelectComponents) and WizardIsComponentSelected('bd') and not ServicioExiste then
  begin
    Otro := OtroServidorMySQL;
    if Otro <> '' then
      Result := MsgBox('Este equipo ya tiene un servidor de base de datos instalado (servicio "' + Otro + '"). ' +
        'Si usa el puerto 3306, la base de datos de HasNet no podrá iniciar.' + #13#10#13#10 +
        'Detenga o desinstale ese servicio antes de continuar. ¿Desea continuar de todas formas?',
        mbConfirmation, MB_YESNO) = IDYES;
  end;
end;

function PrepareToInstall(var NeedsRestart: Boolean): String;
var
  Codigo: Integer;
begin
  Result := '';
  BaseNueva := not DirExists(ExpandConstant('{app}\mariadb\data\mysql'));
  if ServicioExiste then
    Exec(ExpandConstant('{sys}\net.exe'), 'stop {#Servicio}', '', SW_HIDE, ewWaitUntilTerminated, Codigo);
end;

procedure CurUninstallStepChanged(CurUninstallStep: TUninstallStep);
begin
  if CurUninstallStep = usPostUninstall then
    if DirExists(ExpandConstant('{app}\mariadb\data')) then
      MsgBox('La base de datos se conservó en ' + ExpandConstant('{app}\mariadb\data') +
        '. Bórrela manualmente solo si ya no necesita la información.', mbInformation, MB_OK);
end;
