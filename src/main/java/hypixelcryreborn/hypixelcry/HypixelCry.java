package hypixelcryreborn.hypixelcry;

import hypixelcryreborn.hypixelcry.commands.ItemInfo;
import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HypixelCry implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Hypixel Cry");
    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Call the onRender method to render the ImGui interface
            //ImguiLoader.onFrameRender();
        });
        new ItemInfo().Init();
    }
}
