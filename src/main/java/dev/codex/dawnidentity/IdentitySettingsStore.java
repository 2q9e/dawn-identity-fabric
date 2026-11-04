package dev.codex.dawnidentity;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

final class IdentitySettingsStore {
    private IdentitySettingsStore() {
    }

    static boolean load(Path configPath, Logger logger) {
        if (!Files.exists(configPath)) {
            return false;
        }

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
            return Boolean.parseBoolean(properties.getProperty("enabled", "false"));
        } catch (IOException exception) {
            logger.error("Could not read Dawn Identity config; defaulting to OFF", exception);
            return false;
        }
    }

    static void save(Path configPath, boolean enabled, Logger logger) {
        Properties properties = new Properties();
        properties.setProperty("enabled", Boolean.toString(enabled));
        Path temporaryPath = configPath.resolveSibling(configPath.getFileName() + ".tmp");

        try {
            Files.createDirectories(configPath.getParent());
            try (OutputStream output = Files.newOutputStream(temporaryPath)) {
                properties.store(output, "Dawn Identity Toggle. Set enabled to true or false.");
            }

            try {
                Files.move(temporaryPath, configPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException exception) {
                Files.move(temporaryPath, configPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            logger.error("Could not save Dawn Identity config", exception);
        }
    }
}

