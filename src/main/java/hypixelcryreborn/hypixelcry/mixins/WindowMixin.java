package hypixelcryreborn.hypixelcry.mixins;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
    @Final
    private long window;

    @Inject(at = @At("HEAD"),method = "setIcon")
    private void setIcon(InputStream inputStream, InputStream inputStream2, CallbackInfo ci){
        ImguiLoader.onGlfwInit(window);
    }
}
