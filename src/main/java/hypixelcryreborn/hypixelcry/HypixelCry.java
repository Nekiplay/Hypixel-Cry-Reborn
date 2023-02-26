package hypixelcryreborn.hypixelcry;

import hypixelcryreborn.hypixelcry.commands.ItemInfo;
import hypixelcryreborn.hypixelcry.gommehd.OreNuker;
import hypixelcryreborn.hypixelcry.imgui.impementation.ImguiLoader;
import hypixelcryreborn.hypixelcry.intefaces.CheatCategory;
import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.PreInit;
import hypixelcryreborn.hypixelcry.utils.ReflectInit;
import net.fabricmc.api.ModInitializer;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

public class HypixelCry implements ModInitializer {
    public static final String MOD_ID = "hypixel-cry";
    public static final Logger LOGGER = LoggerFactory.getLogger("Hypixel Cry");

    public static ArrayList<CheatCategory> categories = new ArrayList<>();

    @Override
    public void onInitialize() {

        ReflectInit.init(PreInit.class);

        OreNuker oreNuker = new OreNuker();
        CheatCategory combat = new CheatCategory("Combat", "");
        combat.modules.add(oreNuker);
        categories.add(combat);

        oreNuker.RegisterTicksEvent();
        //modules.get(0).RegisterTicksEvent();
    }
}
