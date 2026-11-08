package dev.codex.dawnidentity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentitySettingsStoreTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentitySettingsStoreTest.class);

    @TempDir
    Path temporaryDirectory;

    @Test
    void missingConfigDefaultsOff() {
        assertFalse(IdentitySettingsStore.load(temporaryDirectory.resolve("dawn-identity.properties"), LOGGER));
    }

    @Test
    void savesAndReloadsSelectedState() {
        Path configPath = temporaryDirectory.resolve("config").resolve("dawn-identity.properties");

        IdentitySettingsStore.save(configPath, true, LOGGER);
        assertTrue(IdentitySettingsStore.load(configPath, LOGGER));

        IdentitySettingsStore.save(configPath, false, LOGGER);
        assertFalse(IdentitySettingsStore.load(configPath, LOGGER));
    }
}

