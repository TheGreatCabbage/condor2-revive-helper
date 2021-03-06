; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Condor2 Revive Helper"
#define MyAppVersion "1.0.4"
#define MyAppPublisher "TheGreatCabbage"
#define MyAppExeName "Condor2Helper.exe"

#define root SourcePath

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{FA53D8C5-1AD0-443D-83AE-49EB53D1D9B3}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
LicenseFile=..\LICENSE
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "..\build\windows\Condor2Helper.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\build\windows\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

[Registry]
Root: HKCR; Subkey: "c2-revive"; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive"; ValueType: string; ValueName: ""; ValueData: "URL:Condor2Helper custom protocol handler"; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive"; ValueType: string; ValueName: "URL Protocol"; ValueData: ""; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive\DefaultIcon"; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive\DefaultIcon"; ValueType: string; ValueName: ""; ValueData: "{#MyAppExeName},1"; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive\Shell\open\command"; Flags: uninsdeletekey
Root: HKCR; Subkey: "c2-revive\Shell\open\command"; ValueType: string; ValueName: ""; ValueData: """{app}\{#MyAppExeName}"" ""%1"""; Flags: uninsdeletekey