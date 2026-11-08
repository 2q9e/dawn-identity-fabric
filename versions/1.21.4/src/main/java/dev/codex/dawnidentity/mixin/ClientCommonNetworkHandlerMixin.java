package dev.codex.dawnidentity.mixin;

import dev.codex.dawnidentity.DawnCookiePayload;
import dev.codex.dawnidentity.IdentityConfig;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.c2s.common.CookieResponseC2SPacket;
import net.minecraft.network.packet.s2c.common.CookieRequestS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class ClientCommonNetworkHandlerMixin {
    @Inject(method = "onCookieRequest", at = @At("HEAD"), cancellable = true)
    private void dawnIdentity$handleCookieRequest(CookieRequestS2CPacket request, CallbackInfo callback) {
        if (!DawnCookiePayload.KEY.equals(request.key().toString())) {
            return;
        }

        byte[] payload = IdentityConfig.isEnabled() ? DawnCookiePayload.encode() : null;
        ((ClientCommonNetworkHandler) (Object) this)
                .sendPacket(new CookieResponseC2SPacket(request.key(), payload));
        callback.cancel();
    }
}
