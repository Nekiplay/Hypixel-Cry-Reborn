package hypixelcryreborn.hypixelcry.gommehd;

import hypixelcryreborn.hypixelcry.intefaces.BindModule;
import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.render.RenderUtils;
import hypixelcryreborn.hypixelcry.utils.render.color.QuadColor;
import hypixelcryreborn.hypixelcry.utils.world.BlockGetterUtils;
import hypixelcryreborn.hypixelcry.utils.world.BlockUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public class OreNuker extends BlockGetterUtils implements CheatModule {
    public OreNuker() {
        SetDistance(4.5, 5);
    }
    public BindModule bindModule = new BindModule("Ore Nuker", "GommeHD", GLFW.GLFW_KEY_X);
    @Override
    public String GetName() {
        return "Ore Nuker";
    }

    private ArrayList<BlockPos> breaked = new ArrayList<>();
    private boolean work = false;
    BlockUtils blockUtils = new BlockUtils();
    BlockPos current = null;
    QuadColor outlineColorGreen = QuadColor.single(1.0F, 0.0F, 0.0F, 1f);
    public void RegisterTicksEvent() {
        WorldRenderEvents.END.register(wrc -> {
            if (work && current != null) {
                RenderUtils.drawBoxOutline(current, outlineColorGreen, 1);

            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (bindModule.binding.wasPressed()) {
                work = !work;
                if (work) {
                    breaked.clear();
                    current = null;
                }
                client.player.sendMessage(new LiteralText("Ore nuker: " + Boolean.toString(work).replaceAll("False", "Disabled").replaceAll("True", "Enabled")), false);
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (work) {
                BlockPos pos = getClosestBlock(getBlocks());
                if (pos != null && current == null) {
                    current = pos;
                    Runnable task = () -> {

                        blockUtils.BreakBlock(pos);
                        breaked.add(pos);
                        current = null;
                    };
                    new Thread(task).start();
                }
            }
        });
    }

    @Override
    public boolean isBlockToBreak(BlockState blockState, BlockPos pos) {
        if (!breaked.contains(pos)) {
            Block block = blockState.getBlock();
            if (block == Blocks.COAL_ORE || block == Blocks.GOLD_ORE || block == Blocks.IRON_ORE) {
                return true;
            }
        }
        return false;
    }
}
