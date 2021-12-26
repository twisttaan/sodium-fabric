// Originally from Logical Zoom by LogcialGeekBoy
// https://github.com/LogicalGeekBoy/logical_zoom

package me.jellysquid.mods.sodium.mixin.core;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.render.GameRenderer;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class SodiumREZoomMixin {

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if(SodiumClientMod.isZooming()) {
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov * SodiumClientMod.zoomLevel);
        }
        SodiumClientMod.manageSmoothCamera();
    }
}