package hypixelcryreborn.hypixelcry.utils.render;

import hypixelcryreborn.hypixelcry.mixins.accessors.AccessorWorldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.util.math.Box;

public class FrustumUtils {

    public static Frustum getFrustum() {
        return ((AccessorWorldRenderer) MinecraftClient.getInstance().worldRenderer).getFrustum();
    }

    public static boolean isBoxVisible(Box box) {
        return getFrustum().isVisible(box);
    }
}