package hypixelcryreborn.hypixelcry.utils;

import net.minecraft.client.MinecraftClient;

public class Utils {
    public static boolean rendering3D = true;
    public static boolean canUpdate() {
        return MinecraftClient.getInstance() != null && MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null;
    }
}
