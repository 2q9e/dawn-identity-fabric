# Research summary

Research was carried out by five specialist agents and reviewed by the lead architect.

## Dawn identification

Dawn's official server documentation says that, on Minecraft 1.20.5 and newer, a server may request the dawn:client_info cookie. It describes the cookie as a version byte followed by two Java modified-UTF strings named brand and ref, and says that only Dawn Client answers it.

The public documentation does not state the official byte value, official brand/ref values, or a cryptographic validation scheme. The mod therefore uses version 1, brand Dawn, and ref release as explicit emulation values. Server integrations may inspect those values, require different values, ignore the response, or apply their own account/presence checks. The public format alone does not authenticate an official Dawn session.

Source: https://dawn.gg/servers/docs/rewards-api

## Minecraft brand and server detection

Minecraft has a separate ordinary client-brand payload. The 1.21.11 Yarn API documents BrandCustomPayload. Paper exposes the received brand as Player#getClientBrandName(), and its documentation describes it as nullable and client-supplied. It is a label, not proof of the launcher or installed mods.

Sources:
- https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/network/packet/BrandCustomPayload.html
- https://jd.papermc.io/paper/1.21.11/org/bukkit/entity/Player.html

The Dawn cookie and vanilla brand are independent signals. This mod changes both while enabled.

## Fabric UI and networking

The UI specialist found that Fabric's ScreenEvents.AFTER_INIT event can add a widget to the MultiplayerScreen through Screens.getButtons. This allows a single toggle without another screen or a layout-field mixin.

The networking specialist confirmed Minecraft 1.21.11 has a client cookie-request handler and a CookieResponseC2SPacket. Fabric API does not expose a dedicated client cookie-response event, so the mod uses one narrow client mixin to handle the documented Dawn cookie key. A second narrow mixin changes the ordinary brand retriever only while the toggle is on.

Sources:
- https://maven.fabricmc.net/docs/fabric-api-0.141.6%2B1.21.11/net/fabricmc/fabric/api/client/screen/v1/ScreenEvents.html
- https://maven.fabricmc.net/docs/fabric-api-0.141.6%2B1.21.11/net/fabricmc/fabric/api/client/screen/v1/Screens.html
- https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/network/packet/s2c/common/CookieRequestS2CPacket.html
- https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/network/packet/c2s/common/CookieResponseC2SPacket.html
- https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/client/ClientBrandRetriever.html
- https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/client/network/ClientCommonNetworkHandler.html

## Version and compatibility

The build targets Minecraft 1.21.11, Java 21, Fabric Loader 0.18.6, Fabric API 0.141.6+1.21.11, and Loom 1.14.10. These are versioned components from Fabric's Maven/metadata repositories. A client-only Fabric mod works with the launcher's selected Fabric instance; it does not modify the launcher installation or authentication.

Source: https://docs.fabricmc.net/develop/getting-started/setting-up-a-development-environment

## Public test-server lead

MCPVP is the best public lead found: Dawn identifies MCPVP as its official launcher partner, and MCPVP describes client/server features and verified-client access. However, the public pages do not confirm that MCPVP requests the dawn:client_info cookie or accepts this emulator's self-declared values. Treat MCPVP as a candidate only, not a verified test server. No connection was made to it.

Sources:
- https://dawn.gg/
- https://dawn.gg/servers
- https://www.mcpvp.com/client

## Specialist summaries

- Dawn research: identified the dedicated cookie mechanism and confirmed that a brand string cannot substitute for the cookie.
- Server detection research: confirmed generic brand detection is available to Paper plugins but is self-declared; cookie-based detection is distinct.
- Fabric development research: confirmed the 1.21.11 cookie packet classes and client-brand retriever; no documented Fabric event covers this cookie request.
- UI research: recommended ScreenEvents.AFTER_INIT plus Screens.getButtons, a single persisted toggle, and no layout mixin.
- Quality assurance research: proposed byte-layout/config tests plus game/server checks; it emphasized that actual UI, connectivity, and server-observed values need in-game verification.

