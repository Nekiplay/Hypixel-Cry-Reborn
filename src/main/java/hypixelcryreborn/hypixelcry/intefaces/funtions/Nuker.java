package hypixelcryreborn.hypixelcry.intefaces.funtions;

import hypixelcryreborn.hypixelcry.intefaces.CheatModule;
import hypixelcryreborn.hypixelcry.utils.world.BlockGetterUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class Nuker extends BlockGetterUtils {
    private ArrayList<BlockPos> breaked = new ArrayList<>();
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
