package com.lumaa.mmf.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Locale;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void drawText(DrawContext context, List<String> text, boolean left);

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    public void render(DrawContext context, CallbackInfo ci) {
        this.client.getProfiler().push("debug");
        BlockPos blockPos = this.client.getCameraEntity().getBlockPos();
        String blockPosString = String.format(Locale.ROOT, "Position: %d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ());
        List<String> list = Lists.newArrayList(blockPosString);
        list.add(String.format(Locale.ROOT, "Yaw: %.1f", MathHelper.wrapDegrees(this.client.getCameraEntity().getYaw())));
        list.add(String.format(Locale.ROOT, "Pitch: %.1f", MathHelper.wrapDegrees(this.client.getCameraEntity().getPitch())));

        context.draw(() -> drawText(context, list, true));
        ci.cancel();
    }
}
