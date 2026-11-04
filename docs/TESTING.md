# Testing record

## Automated checks

The following command completed successfully with Java 21:

    .\gradlew.bat clean test build

The build compiled and remapped the Fabric mod JAR for Minecraft 1.21.11. JUnit checks verify:
- the Dawn cookie encodes a version byte followed by the two modified-UTF strings and no trailing bytes;
- a missing config defaults to OFF;
- ON/OFF config values survive save/reload.

## Client startup

Gradle's runClient task started Minecraft 1.21.11 with Fabric Loader 0.18.6 and loaded dawn_identity 1.0.0 with Fabric API 0.141.6+1.21.11. The startup log showed the Mixin subsystem initialized and no mixin application error.

## UI check and remaining verification

The development client reached the Multiplayer Server Selection screen and displayed the Dawn ID toggle. It showed ON and the run config contained enabled=true. The computer-use click test was interrupted by Escape before I could toggle it, so the button action itself is not verified.

A Paper server or other live server was not used, so the exact server-observed brand/cookie response and multiplayer login with the emulation enabled remain unverified. Screen-resize behavior also remains unverified. The official Dawn ref value is not published in the public documentation; this build uses release as its self-declared ref and may not satisfy a server with stricter checks.

