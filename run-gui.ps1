# Build (skip tests)
mvn -DskipTests=true package
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

# Try to run assembled jar if present
$jar = Get-ChildItem -Path target -Filter "*-jar-with-dependencies.jar" -File -ErrorAction SilentlyContinue | Select-Object -First 1
if ($jar) {
    java -jar $jar.FullName
} else {
    java -cp target\classes com.pico.ui.PicoPlacaGUI
}

Read-Host -Prompt "Press Enter to exit"
