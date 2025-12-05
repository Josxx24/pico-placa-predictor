@echo off
REM Build (skip tests for speed)
mvn -DskipTests=true package
if %ERRORLEVEL% NEQ 0 exit /b %ERRORLEVEL%









pause)    java -cp target\classes com.pico.ui.PicoPlacaGUI) else (    java -jar "%JAR%"    for %%f in (target\pico-placa-predictor-*-jar-with-dependencies.jar) do set JAR=%%fnREM Run the GUI from the assembled jar if present, otherwise run compiled classes
nif exist target\pico-placa-predictor-*-jar-with-dependencies.jar (