# Dawn Identity Toggle

A client-only Fabric mod for Minecraft 1.21.11. It adds one persistent ON/OFF toggle to the Multiplayer Server Selection screen.

When ON, the mod:
- reports the client brand as Dawn through Minecraft's ordinary brand channel;
- answers requests for the documented dawn:client_info cookie with a version byte and Java modified-UTF strings: brand Dawn and ref release.

When OFF, the normal client brand is used and a dawn:client_info request receives a null payload. The choice is persisted in config/dawn-identity.properties.

## Identity behavior and limits

This is an emulator, not the official Dawn Client. Dawn publishes the cookie key and byte layout, but does not publish the official ref value or a verification scheme. This mod sends version 1, brand Dawn, and ref release. Those are self-declared values; a server can inspect them and can choose to reject them or apply additional checks. This does not authenticate a Dawn account, create Dawn session telemetry, or qualify for Dawn rewards.

The normal brand and cookie response do not prove which launcher or mods are installed. The mod does not change authentication, credentials, session tokens, or the launcher's installation. It does not remove mod-list exchanges or other server-specific checks, and another mod may also alter these signals.

## Build

Requirements: Java 21 and an internet connection for Gradle to download Fabric and Minecraft build dependencies.

On Windows, run:

    .\gradlew.bat build

The mod JAR is written to build/libs/dawn-identity-emulator-1.0.0.jar.

To start a development client, run:

    .\gradlew.bat runClient

Install the built JAR and Fabric API in the mods folder of the Minecraft instance you want to use. The toggle appears on the Multiplayer Server Selection screen and persists across launches. A change applies to new server connections.

## Compatibility

- Minecraft Java Edition 1.21.11
- Fabric Loader 0.18.6 or newer
- Fabric API 0.141.6+1.21.11
- Java 21 or newer

## License

This project uses the custom **Dawn Identity Fabric Attribution License (DIF-1.0)** in [LICENSE](LICENSE). Reuse requires visible credit to 2q9e and a link to [this repository](https://github.com/2q9e/dawn-identity-fabric); published work that materially uses the project as a reference must cite it as well. This is not a standard MIT license or an OSI-approved license.

This project is independent and is not affiliated with or endorsed by Dawn, InPvP, Mojang Studios, or Microsoft.

