package dev.doctor4t.trainmurdermystery.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.doctor4t.trainmurdermystery.client.util.AlwaysVisibleFrustum;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "method_52816", at = @At(value = "RETURN"), cancellable = true)
    private static void method_52816(Frustum frustum, CallbackInfoReturnable<Frustum> cir) {
        cir.setReturnValue(new AlwaysVisibleFrustum(frustum));
    }

    @WrapOperation(method = "renderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/GlUniform;set(FFF)V", ordinal = 0))
    public void renderLayer(GlUniform instance, float v1, float v2, float v3, Operation<Void> original, @Local BlockPos blockPos, @Local(name = "x") double x, @Local(name = "y") double y, @Local(name = "z") double z) {
        boolean trainSection = ChunkSectionPos.getSectionCoord(blockPos.getY()) >= 16;
        float trainSpeed = 180; // in kmh
        original.call(
                instance,
                trainSection ? v1 : ((v1 + ((client.player.age + client.getRenderTickCounter().getTickDelta(true)) / 73.8f * trainSpeed)) % 513 - 256),
                trainSection ? v2 : (v2 + 45),
                v3
        );
    }
}