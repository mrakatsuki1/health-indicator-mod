# Health Indicator Mod
**Minecraft Java Edition 1.21.1 | Fabric**

## Kya karta hai?
Jab aap enemy ko hit karte ho, toh uska nametag automatically color change karta hai:

| Health %      | Color  |
|---------------|--------|
| 75% – 100%    | 🟢 Green  |
| 50% –  74%    | 🟡 Yellow |
| 25% –  49%    | 🟠 Orange |
|  0% –  24%    | 🔴 Red    |

---

## Build Karne Ka Tarika

### Zaruriyat (Requirements)
- **Java 21** — [Download here](https://adoptium.net/)
- **Gradle 8** (ya Gradle Wrapper use karo)
- Internet connection (pehli baar Fabric Loom download hoga)

### Steps

1. **Is folder mein jaao:**
   ```
   cd health-indicator
   ```

2. **Build karo:**
   ```
   ./gradlew build
   ```
   Windows par:
   ```
   gradlew.bat build
   ```

3. **JAR file milegi:**
   ```
   build/libs/health-indicator-1.0.0.jar
   ```

4. **JAR file apne Minecraft `mods/` folder mein daalo.**

---

## Install Karne Ka Tarika

1. [Fabric Installer](https://fabricmc.net/use/installer/) se Fabric install karo (MC 1.21.1 ke liye)
2. [Fabric API](https://modrinth.com/mod/fabric-api) download karo (1.21.1 version)
3. `health-indicator-1.0.0.jar` aur Fabric API JAR dono `mods/` folder mein rakho
4. Minecraft launch karo — enjoy!

---

## Notes
- Yeh **client-side** mod hai — sirf aapke side par kaam karta hai
- Players ki health bhi color change hogi (agar unka nametag dikh raha ho)
- Multiplayer mein bhi kaam karta hai

## Support
Agar koi error aaye toh `.minecraft/logs/latest.log` check karo.
