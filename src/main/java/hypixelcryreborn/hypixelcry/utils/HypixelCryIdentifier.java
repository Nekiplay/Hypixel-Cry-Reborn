package hypixelcryreborn.hypixelcry.utils;

import hypixelcryreborn.hypixelcry.HypixelCry;
import net.minecraft.util.Identifier;

public class HypixelCryIdentifier extends Identifier {
    public HypixelCryIdentifier(String path) {
        super(HypixelCry.MOD_ID, path);
    }
}