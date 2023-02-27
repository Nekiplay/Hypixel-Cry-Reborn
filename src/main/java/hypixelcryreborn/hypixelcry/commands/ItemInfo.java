package hypixelcryreborn.hypixelcry.commands;

import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import hypixelcryreborn.hypixelcry.imgui.menus.SettingsMenu;
import hypixelcryreborn.hypixelcry.utils.server.ServerUtils;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.LiteralText;
import org.w3c.dom.Text;

import java.awt.*;

public class ItemInfo  {
    public void Init() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("cry").executes(context -> {
                    context.getSource().sendFeedback(new LiteralText("Server IP: " + ServerUtils.GetServerIP()));
                    context.getSource().sendFeedback(new LiteralText("Window Handle: " + ImguiLoader.windowHandle));
                    return 0;
                })
        );
    }

}
