package hypixelcryreborn.hypixelcry.imgui.menus;

import com.mojang.blaze3d.vertex.PoseStack;
import hypixelcryreborn.hypixelcry.HypixelCry;
import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import imgui.*;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class SettingsMenu extends Gui {
    public SettingsMenu() {
        super(Minecraft.getInstance());
    }

    @Override
    public void render(PoseStack poseStack, float f) {
        ImguiLoader.onFrameRender();
    }
}
