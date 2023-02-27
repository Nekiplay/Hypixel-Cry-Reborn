package hypixelcryreborn.hypixelcry.intefaces;

import imgui.type.ImBoolean;
import org.lwjgl.glfw.GLFW;

public interface CheatModule {
    //region properties
    ImBoolean guiBoolean = new ImBoolean(false);
    BindModule bindModule = new BindModule("Ore Nuker", "GommeHD", GLFW.GLFW_KEY_X);
    //endregion
    //region methods
    public default String GetName() {
        return "";
    }
    public default ImBoolean GetGuiBoolean() {
        return guiBoolean;
    }
    public default boolean IsEnabled() {
        return false;
    }
    public default BindModule GetBind()  {
        return bindModule;
    }
    public void RegisterEvent();
    public void RenderGuiSettings();
    //endregion
}
