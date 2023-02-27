package hypixelcryreborn.hypixelcry.gommehd;

import hypixelcryreborn.hypixelcry.intefaces.BindModule;
import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.render.RenderUtils;
import hypixelcryreborn.hypixelcry.utils.render.color.QuadColor;
import hypixelcryreborn.hypixelcry.utils.world.BlockGetterUtils;
import hypixelcryreborn.hypixelcry.utils.world.BlockUtils;
import imgui.ImGui;
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
    @Override
    public String GetName() {
        return "Ore Nuker";
    }

    private ArrayList<BlockPos> breaked = new ArrayList<>();
    private boolean work = false;
    BlockUtils blockUtils = new BlockUtils();
    BlockPos current = null;
    QuadColor outlineColorGreen = QuadColor.single(1.0F, 1.0F, 1.0F, 1.0f);

    @Override
    public boolean IsEnabled() {
        return work;
    }

    @Override
    public void RegisterEvent() {
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((a, b) -> {
            if (work && current != null) {
                RenderUtils.drawBoxOutline(current, outlineColorGreen, 1);
            }
            return true;
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (GetBind().binding.wasPressed()) {
                work = !work;
                if (work) {
                    breaked.clear();
                    current = null;
                }
                client.player.sendMessage(new LiteralText("Ore nuker: " + Boolean.toString(work).replaceAll("False", "Disabled").replaceAll("True", "Enabled")), false);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (work && client.player != null && client.world != null) {
                SetDistance(horizontal_d[0], vertical_d[0]);
                BlockPos pos = getClosestBlock(getBlocks());
                if (pos != null && current == null) {
                    current = pos;
                    Runnable task = () -> {
                        float speedTemp = 1000 - (speed[0] * 1000);
                        if (speedTemp < 0)
                            speedTemp = 0;
                        blockUtils.BreakBlock(pos, speedTemp);
                        breaked.add(pos);
                        current = null;
                    };
                    new Thread(task).start();
                }
            }
        });
    }
    private float[] horizontal_d = new float[] {
      4.75f
    };
    private float[] vertical_d = new float[] {
        4.75f
    };
    private float[] speed = new float[] {
            0.325f
    };
    @Override
    public void RenderGuiSettings() {
        ImGui.setNextItemWidth(75);
        ImGui.sliderFloat("Speed", speed, 0, 1);
        ImGui.setNextItemWidth(75);
        ImGui.sliderFloat("X,Z distance", horizontal_d, 1, 7);
        ImGui.setNextItemWidth(75);
        ImGui.sliderFloat("Y distance", vertical_d, 1, 7);
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
