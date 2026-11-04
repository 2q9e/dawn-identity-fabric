package dev.codex.dawnidentity;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public final class IdentityConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("DawnIdentity");
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("dawn-identity.properties");
    private static volatile boolean enabled;

    private IdentityConfig() {
    }

    public static void load() {
        enabled = IdentitySettingsStore.load(CONFIG_PATH, LOGGER);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static synchronized void toggle() {
        enabled = !enabled;
        save();
    }

    private static void save() {
        IdentitySettingsStore.save(CONFIG_PATH, enabled, LOGGER);
    }
}

