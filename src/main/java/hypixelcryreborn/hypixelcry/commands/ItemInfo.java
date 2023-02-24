package hypixelcryreborn.hypixelcry.commands;

import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import hypixelcryreborn.hypixelcry.imgui.menus.SettingsMenu;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import org.w3c.dom.Text;

public class ItemInfo  {
    public void Init() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("cry").executes(context -> {
                    context.getSource().sendFeedback(new TextComponent("Window Handle: " + ImguiLoader.windowHandle));
                    return 0;
                })
        );
    }

}
