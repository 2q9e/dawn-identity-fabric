# Testing record

Verification run on Windows 11 with Eclipse Temurin JDK 25.0.5 and Gradle 9.8.2.

## Automated checks

The following completed successfully after the version modules were added:

    .\gradlew.bat clean build --stacktrace

All six version modules compiled, packaged, and passed their test tasks: 1.21.4, 1.21.11, 26.1, 26.2, 26.3, and the experimental 26.4 Snapshot 7 module. The shared JUnit checks cover the cookie byte layout, the missing-config default, and config save/reload behavior. The tests run in each module's target toolchain.

The first run exposed that the committed Gradle wrapper JAR was not a valid JAR. It was replaced with the official Gradle 9.8.2 wrapper JAR before the successful build.

## Development client startup

`runClient` was launched for each of the six modules. Every client log:

- identifies the expected Minecraft and Fabric Loader versions;
- lists `dawn_identity` as loaded;
- reaches resource reload and sound engine initialization;
- contains no Fabric mixin application failure or fatal crash.

The 26.x development clients also print Windows Performance Library counter warnings from Minecraft's OSHI system profiler. The game logs continue through startup. Development sessions also report a Realms authorization warning because the development profile does not have a production Microsoft session; neither warning stopped the client.

## Not covered

These checks do not include clicking the toggle in each client, joining a server, or verifying the server-observed brand and cookie response. Those still need a manual multiplayer check. The 26.4 build targets Snapshot 7, whose runtime Minecraft ID is `26.4-alpha.7`; it is experimental and can break as the snapshot changes.
