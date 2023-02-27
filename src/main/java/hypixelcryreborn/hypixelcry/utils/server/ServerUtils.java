package hypixelcryreborn.hypixelcry.utils.server;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.realms.Request;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.math.BlockPos;

public class ServerUtils {
    public static String address = "";
    public static String GetServerIP() {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        if (minecraft.player != null) {
            ServerInfo serverData = minecraft.getCurrentServerEntry();
            if (serverData != null) {
                address = serverData.address.toLowerCase();
                return address;
            }
        }
        address = "";
        return "";
    }

    public static void InitUpdater() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            GetServerIP();
        });
    }
}
