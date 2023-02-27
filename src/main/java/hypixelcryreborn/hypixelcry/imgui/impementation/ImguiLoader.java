package hypixelcryreborn.hypixelcry.imgui.impementation;

import hypixelcryreborn.hypixelcry.HypixelCry;
import hypixelcryreborn.hypixelcry.intefaces.CheatCategory;
import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.render.GL;
import hypixelcryreborn.hypixelcry.utils.render.imgui.ImGuiTexture;
import imgui.*;
import imgui.flag.*;
import imgui.gl3.*;
import imgui.glfw.*;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;

import static org.lwjgl.glfw.GLFW.*;

public class ImguiLoader {
    private static final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();

    private static final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    public static long windowHandle = -1;
    public static void onGlfwReturn(long handle) {
        //loadImage();
    }
    private static ImGuiTexture texture = new ImGuiTexture();
    public static void onGlfwInit(long handle)  {
        initializeImGui(handle);
        imGuiGlfw.init(handle, true);
        imGuiGl3.init();
        windowHandle = handle;
        texture.Async("https://sun9-18.userapi.com/impg/lmRBB_nEOLYg7mKlfPgSgLYb7pyR_Ph5cNJwqA/HYGoGXM69o0.jpg?size=1080x1080&quality=96&sign=44810ca92f7f964017d72b70d5b5df41&type=album");
        //HypixelCry.LOGGER.info("TextureID: " + HypixelCry.texture);
    }
    private static void ParseCategory(CheatCategory category) {
        if (category.IsAvalible()) {
            if (ImGui.treeNode(category.GetName())) {
                for (CheatCategory category1 : category.GetCategories()) {
                    ParseCategory(category1);
                }
                for (CheatModule module : category.modules) {
                    if (ImGui.treeNode(module.GetName())) {
                        module.RenderGuiSettings();
                        ImGui.treePop();
                        ImGui.spacing();
                    }
                }

                ImGui.treePop();
                ImGui.spacing();
            }
        }
    }

    public static void onFrameRender() {
        if (windowHandle != -1) {
            imGuiGlfw.newFrame();
            ImGui.newFrame();
            ImGui.begin("Minecraft Cry");

            for (CheatCategory category : HypixelCry.categories) {
                ParseCategory(category);
            }

            if (texture.GetTexture() != -2) {
                ImGui.image(texture.GetTexture(), 128, 128);
            }

            ImGui.end();
            ImGui.render();
            imGuiGl3.renderDrawData(ImGui.getDrawData());

            if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
                final long backupWindowPtr = glfwGetCurrentContext();
                ImGui.updatePlatformWindows();
                ImGui.renderPlatformWindowsDefault();
                glfwMakeContextCurrent(backupWindowPtr);
            }
        }
    }

    private static void initializeImGui(long glHandle) {
        ImGui.createContext();
        ImGui.styleColorsDark();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);
        io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
        io.setBackendFlags(ImGuiBackendFlags.HasMouseCursors);
        io.setBackendPlatformName("imgui_java_impl_glfw");
        //io.setKeyMap(ImGuiKey.Tab, GLFW.GLFW_KEY_TAB);
        //io.setKeyMap(ImGuiKey.LeftArrow, GLFW.GLFW_KEY_LEFT);
        //io.setKeyMap(ImGuiKey.RightArrow, GLFW.GLFW_KEY_RIGHT);
        //io.setKeyMap(ImGuiKey.UpArrow, GLFW.GLFW_KEY_UP);
        //io.setKeyMap(ImGuiKey.DownArrow, GLFW.GLFW_KEY_DOWN);
        //io.setKeyMap(ImGuiKey.PageUp, GLFW.GLFW_KEY_PAGE_UP);
        //io.setKeyMap(ImGuiKey.PageDown, GLFW.GLFW_KEY_PAGE_DOWN);
        //io.setKeyMap(ImGuiKey.Home, GLFW.GLFW_KEY_HOME);
        //io.setKeyMap(ImGuiKey.End, GLFW.GLFW_KEY_END);
        //io.setKeyMap(ImGuiKey.Insert, GLFW.GLFW_KEY_INSERT);
        //io.setKeyMap(ImGuiKey.Delete, GLFW.GLFW_KEY_DELETE);
    }
}
