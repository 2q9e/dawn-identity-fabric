package dev.codex.dawnidentity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public final class DawnIdentityClient implements ClientModInitializer {
    private static final int BUTTON_WIDTH = 112;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_MARGIN = 8;

    @Override
    public void onInitializeClient() {
        IdentityConfig.load();

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof JoinMultiplayerScreen)) {
                return;
            }

            int buttonWidth = Math.min(BUTTON_WIDTH, Math.max(1, scaledWidth - BUTTON_MARGIN * 2));
            int x = Math.max(BUTTON_MARGIN, scaledWidth - buttonWidth - BUTTON_MARGIN);

            Button toggle = Button.builder(buttonText(), button -> {
                IdentityConfig.toggle();
                button.setMessage(buttonText());
            }).bounds(x, 6, buttonWidth, BUTTON_HEIGHT).build();

            Screens.getWidgets(screen).add(toggle);
        });
    }

    private static Component buttonText() {
        return Component.literal("Dawn ID: " + (IdentityConfig.isEnabled() ? "ON" : "OFF"));
    }
}
