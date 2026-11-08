package dev.codex.dawnidentity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class DawnIdentityClient implements ClientModInitializer {
    private static final int BUTTON_WIDTH = 112;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_MARGIN = 8;

    @Override
    public void onInitializeClient() {
        IdentityConfig.load();

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof MultiplayerScreen)) {
                return;
            }

            int buttonWidth = Math.min(BUTTON_WIDTH, Math.max(1, scaledWidth - BUTTON_MARGIN * 2));
            int x = Math.max(BUTTON_MARGIN, scaledWidth - buttonWidth - BUTTON_MARGIN);

            ButtonWidget toggle = ButtonWidget.builder(buttonText(), button -> {
                IdentityConfig.toggle();
                button.setMessage(buttonText());
            }).dimensions(x, 6, buttonWidth, BUTTON_HEIGHT).build();

            Screens.getButtons(screen).add(toggle);
        });
    }

    private static Text buttonText() {
        return Text.literal("Dawn ID: " + (IdentityConfig.isEnabled() ? "ON" : "OFF"));
    }
}

