#!/bin/bash
echo "============================================"
echo "  Health Indicator Mod - Auto Build Script"
echo "============================================"
echo ""

# Check Java
if ! java -version &>/dev/null; then
    echo "[ERROR] Java 21 install nahi hai!"
    echo "Download karo: https://adoptium.net/"
    exit 1
fi
echo "[OK] Java mila!"

# Download Gradle
echo ""
echo "[1/3] Gradle wrapper download ho raha hai..."
curl -L "https://services.gradle.org/distributions/gradle-8.8-bin.zip" -o gradle-bin.zip
if [ $? -ne 0 ]; then
    echo "[ERROR] Gradle download fail hua. Internet check karo!"
    exit 1
fi

# Extract Gradle
echo "[2/3] Gradle extract ho raha hai..."
unzip -q gradle-bin.zip
rm gradle-bin.zip
echo "[OK] Gradle ready!"

# Build
echo "[3/3] Mod build ho raha hai (pehli baar thoda time lagega)..."
echo ""
./gradle-8.8/bin/gradle build --no-daemon
if [ $? -ne 0 ]; then
    echo ""
    echo "[ERROR] Build fail hua!"
    exit 1
fi

# Find and copy JAR
echo ""
echo "[DONE] Mod build hua!"
for jar in build/libs/health-indicator-*.jar; do
    if [[ "$jar" != *"-sources.jar" ]]; then
        cp "$jar" "health-indicator.jar"
        echo "JAR tayyar: health-indicator.jar"
    fi
done

echo ""
echo "Ab 'health-indicator.jar' apne .minecraft/mods/ folder mein rakho!"
