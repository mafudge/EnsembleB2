if $CmdLine[0] < 2 then
	msgbox(0,"Useage:","AutoIt3.exe " & @ScriptName & " Project WarPath" )
	Exit
Else
	$Project =  $CmdLine[1]
	$WarFile =  $CmdLine[2]
EndIf
$url = "http://localhost/webapps/bb-starting-block-bb_bb60/execute/install?fileName=" & $Warfile & "&clean=true&available=true"
; build here
WinActivate("Java EE")
Send("!fo")
Send("war{enter}")
Send("!n")
Send("p")
Send($Project,1)
Send("{tab}")
Send($WarFile,1)
Send("!f")
Sleep(1500)
; Deploy Here
ShellExecute($url,"",@ScriptDir,"open")