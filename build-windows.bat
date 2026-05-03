@echo off
echo ============================================
echo   Health Indicator Mod - Auto Build Script
echo ============================================
echo.

:: Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java 21 install nahi hai!
    echo Download karo: https://adoptium.net/
    pause
    exit /b 1
)
echo [OK] Java mila!

:: Download Gradle Wrapper
echo.
echo [1/3] Gradle wrapper download ho raha hai...
curl -L "https://services.gradle.org/distributions/gradle-8.8-bin.zip" -o gradle-bin.zip
if %errorlevel% neq 0 (
    echo [ERROR] Gradle download fail hua. Internet check karo!
    pause
    exit /b 1
)
echo [OK] Gradle download hua!

:: Extract Gradle
echo [2/3] Gradle extract ho raha hai...
powershell -Command "Expand-Archive -Path 'gradle-bin.zip' -DestinationPath '.' -Force"
del gradle-bin.zip
echo [OK] Gradle ready!

:: Build the mod
echo [3/3] Mod build ho raha hai (pehli baar thoda time lagega)...
echo.
gradle-8.8\bin\gradle.bat build --no-daemon
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Build fail hua! Error dekho upar.
    pause
    exit /b 1
)

:: Copy JAR
echo.
echo [DONE] Mod successfully build hua!
echo.
for %%f in (build\libs\health-indicator-*.jar) do (
    if not "%%~nxf"=="*-sources.jar" (
        echo JAR file: %%f
        copy "%%f" "health-indicator.jar" >nul
        echo Copied as: health-indicator.jar
    )
)
echo.
echo Ab "health-indicator.jar" apne .minecraft\mods\ folder mein rakho!
echo.
pause
