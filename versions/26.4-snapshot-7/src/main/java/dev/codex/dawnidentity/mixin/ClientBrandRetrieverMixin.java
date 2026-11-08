package dev.codex.dawnidentity.mixin;

import dev.codex.dawnidentity.DawnCookiePayload;
import dev.codex.dawnidentity.IdentityConfig;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBrandRetriever.class)
public abstract class ClientBrandRetrieverMixin {
    @Inject(method = "getClientModName", at = @At("HEAD"), cancellable = true)
    private static void dawnIdentity$overrideClientBrand(CallbackInfoReturnable<String> callback) {
        if (IdentityConfig.isEnabled()) {
            callback.setReturnValue(DawnCookiePayload.BRAND);
        }
    }
}
