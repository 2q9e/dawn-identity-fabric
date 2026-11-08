package dev.codex.dawnidentity.mixin;

import dev.codex.dawnidentity.DawnCookiePayload;
import dev.codex.dawnidentity.IdentityConfig;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.cookie.ClientboundCookieRequestPacket;
import net.minecraft.network.protocol.cookie.ServerboundCookieResponsePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonPacketListenerImpl.class)
public abstract class ClientCommonNetworkHandlerMixin {
    @Inject(method = "handleRequestCookie", at = @At("HEAD"), cancellable = true)
    private void dawnIdentity$handleCookieRequest(ClientboundCookieRequestPacket request, CallbackInfo callback) {
        if (!DawnCookiePayload.KEY.toString().equals(request.key().toString())) {
            return;
        }

        byte[] payload = IdentityConfig.isEnabled() ? DawnCookiePayload.encode() : null;
        ((ClientCommonPacketListenerImpl) (Object) this)
                .send(new ServerboundCookieResponsePacket(request.key(), payload));
        callback.cancel();
    }
}
