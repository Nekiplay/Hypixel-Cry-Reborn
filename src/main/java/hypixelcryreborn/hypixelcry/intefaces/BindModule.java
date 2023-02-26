package hypixelcryreborn.hypixelcry.intefaces;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class BindModule {
    public KeyBinding binding = null;
    private String Text = null;
    private String Category = null;
    private int Key = -1;

    public BindModule(String name, String category, int key) {
        this.Text = name;
        this.Category = category;
        this.Key = key;
        binding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                Text,
                Key,
                Category
        ));
    }
}
