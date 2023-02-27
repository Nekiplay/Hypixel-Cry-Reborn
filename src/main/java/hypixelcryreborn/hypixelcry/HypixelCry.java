package hypixelcryreborn.hypixelcry;

import hypixelcryreborn.hypixelcry.commands.ItemInfo;
import hypixelcryreborn.hypixelcry.gommehd.OreNuker;
import hypixelcryreborn.hypixelcry.intefaces.CheatCategory;
import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.network.Http;
import hypixelcryreborn.hypixelcry.utils.render.GL;
import hypixelcryreborn.hypixelcry.utils.server.ServerUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HypixelCry implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Hypixel Cry");

    public static ArrayList<CheatCategory> categories = new ArrayList<>();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    public void onInitialize() {
        ServerUtils.InitUpdater();

        OreNuker oreNuker = new OreNuker();
        CheatCategory gommehd = new CheatCategory("GommeHD", "gommehd.net", true);
        CheatCategory gommehd_moneymaker = new CheatCategory("Money Maker", "gommehd.net", true);
        gommehd.AddCategory(gommehd_moneymaker);
        gommehd_moneymaker.modules.add(oreNuker);

        categories.add(gommehd);

        for (CheatCategory category : categories) {
            RegisterEvents(category);
        }
    }

    private void RegisterEvents(CheatCategory category) {
        for (CheatModule module : category.modules) {
            module.RegisterEvent();
        }
        for (CheatCategory category1 : category.GetCategories()) {
            RegisterEvents(category1);
        }
    }
}
