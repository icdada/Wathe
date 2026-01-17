package dev.doctor4t.wathe.mixin.client.restrictions;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.doctor4t.wathe.client.WatheClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {
    @Shadow
    public abstract boolean equals(KeyBinding other);

    @Unique
    private boolean shouldSuppressKey() {
        if (WatheClient.isPlayerAliveAndInSurvival()) {

            if (!WatheClient.gameComponent.isRunning()){
                GameOptions opt = MinecraftClient.getInstance().options;
                boolean suppress = this.equals(opt.chatKey)           // 聊天键 (T)
                        || this.equals(opt.jumpKey)           // 跳跃键 (空格)
                        || this.equals(opt.togglePerspectiveKey) // 视角切换键 (F5)
                        || this.equals(opt.dropKey)          // 丢弃物品键 (Q)
                        || this.equals(opt.commandKey);
                return !suppress;
            }
            return
                    this.equals(MinecraftClient.getInstance().options.chatKey) ||
                            this.equals(MinecraftClient.getInstance().options.commandKey) ||
                            this.equals(MinecraftClient.getInstance().options.jumpKey) ||
                            this.equals(MinecraftClient.getInstance().options.togglePerspectiveKey) ||
                            this.equals(MinecraftClient.getInstance().options.dropKey) ||
                            this.equals(MinecraftClient.getInstance().options.advancementsKey);
        }
        return false;
    }

    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
    private boolean wathe$restrainWasPressedKeys(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else return original;
    }

    @ModifyReturnValue(method = "isPressed", at = @At("RETURN"))
    private boolean wathe$restrainIsPressedKeys(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else return original;
    }

    @ModifyReturnValue(method = "matchesKey", at = @At("RETURN"))
    private boolean wathe$restrainMatchesKey(boolean original) {
        if (this.shouldSuppressKey()) return false;
        else return original;
    }
}
