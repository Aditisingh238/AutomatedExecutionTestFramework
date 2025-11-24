# install-maven.ps1 (Admin)
$ErrorActionPreference = "Stop"
$version = "3.9.9"
$zipName = "apache-maven-$version-bin.zip"
$downloadUrl = "https://downloads.apache.org/maven/maven-3/$version/binaries/$zipName"
$destDir = "C:\Program Files\Apache"

New-Item -Path $destDir -ItemType Directory -Force | Out-Null
$zipPath = Join-Path $destDir $zipName
Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath
Expand-Archive -Path $zipPath -DestinationPath $destDir -Force
$extracted = Join-Path $destDir "apache-maven-$version"
$final = Join-Path $destDir "Maven"
if (Test-Path $final) { Remove-Item -Recurse -Force $final }
Rename-Item -Path $extracted -NewName "Maven"
$binPath = Join-Path $final "bin"
setx /M PATH "$($env:PATH);$binPath" | Out-Null
Remove-Item $zipPath
Write-Host "Maven installed. Restart PowerShell and run 'mvn -v'."
