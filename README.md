# Dawn Client Spoofer

An unofficial, client-side Fabric mod with a persistent ON/OFF toggle on the Multiplayer Server Selection screen. When enabled, it sends the self-declared client brand `Dawn` and responds to the documented `dawn:client_info` cookie request with version `1`, brand `Dawn`, and ref `release`. When disabled, it uses the normal client brand and returns a null response to that cookie request.

The toggle is saved in `config/dawn-identity.properties`. A change applies to new server connections.

## Identity behavior and limits

This is an emulator, not the official Dawn Client. The values are self-declared and do not prove that Dawn Client or its launcher is installed. This mod does not authenticate a Dawn account, change credentials or session tokens, create official Dawn session telemetry, or provide Dawn features or rewards. Servers can inspect or reject the values and can run additional checks.

The mod does not remove mod-list exchanges or other server-specific checks. Use it only where the server allows this behavior. This independent project is not affiliated with or endorsed by Dawn, InPvP, Mojang Studios, or Microsoft.

## Minecraft versions

Each Minecraft target is an independent Gradle subproject under `versions/`, with its own dependencies, metadata, and version-specific Minecraft hooks. The config store and cookie encoder are shared from `common/`.

| Minecraft target | Status | Fabric API | Java bytecode |
| --- | --- | --- | --- |
| 1.21.4 | Implemented | 0.119.4+1.21.4 | 21 |
| 1.21.11 | Implemented | 0.141.6+1.21.11 | 21 |
| 26.1 | Implemented | 0.145.1+26.1 | 25 |
| 26.2 | Implemented | 0.161.0+26.2 | 25 |
| 26.3 | Implemented | 0.165.2+26.3 | 25 |
| 26.4 Snapshot 7 (`26.4-alpha.7`) | Experimental snapshot build | 0.165.4+26.4 | 25 |

26.4 is still in snapshot development, so its build is experimental and may need updating as snapshots change. Other versions have not been ported yet.

## Requirements and build

Use JDK 25 to run the root Gradle build. Loom's shared 1.19 toolchain requires the Gradle JVM to be Java 25 or newer. The 1.21.x modules compile with Java 21 toolchains; 26.x modules compile with Java 25.

On Windows, run all module tests and builds with:

    .\gradlew.bat clean build

Run tests only with:

    .\gradlew.bat test

Build one target with a subproject task, for example:

    .\gradlew.bat :mc1_21_4:build
    .\gradlew.bat :mc26_3:build

Start a development client with:

    .\gradlew.bat :mc1_21_4:runClient

Gradle writes each JAR to that version project's `build/libs/` directory. Install the selected JAR and the matching Fabric API in the `mods` folder of that Minecraft instance.

## License

This project uses the custom **Dawn Identity Fabric Attribution License (DIF-1.0)** in [LICENSE](LICENSE). Reuse requires credit to 2q9e and a link to [this repository](https://github.com/2q9e/dawn-identity-fabric). Published work that materially uses the project as a reference must cite it as well. This is not a standard MIT license or an OSI-approved license.
