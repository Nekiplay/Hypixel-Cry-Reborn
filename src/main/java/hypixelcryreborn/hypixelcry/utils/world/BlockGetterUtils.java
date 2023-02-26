package hypixelcryreborn.hypixelcry.utils.world;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;

public class BlockGetterUtils {
    private double horizontal_distance = 5.4;
    private double vertical_distance = 7.5;

    public void SetDistance(double horizontal, double vertical) {
        horizontal_distance = horizontal;
        vertical_distance = vertical;
    }
    public boolean isBlockToBreak(BlockState blockState, BlockPos pos) {
        return false;
    }
    public ArrayList<BlockPos> getBlocks() {
        ArrayList<BlockPos> blockPositions = new ArrayList<>();
        MinecraftClient minecraft = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraft.player;
        if (player != null && player.world != null) {
            int r = (int) horizontal_distance + 1;
            int r_v = (int) vertical_distance;
            BlockPos playerPos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
            for (int x = -r; x <= r; x++) {
                for (int y = -r_v; y <= r_v; y++) {
                    for (int z = -r; z <= horizontal_distance; z++) {
                        BlockPos pos = new BlockPos(playerPos.getX() + x, playerPos.getY() + y, playerPos.getZ() + z);
                        BlockState state = player.world.getBlockState(pos);
                        if (isBlockToBreak(state, pos)) {
                            blockPositions.add(pos);
                        }
                    }
                }
            }
        }
        return blockPositions;
    }

    public BlockPos getClosestBlock(ArrayList<BlockPos> blocks) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraft.player;
        BlockPos closestBlock = null;
        if (player != null) {
            Vec3d playerPosition = player.getPos();
            double minDistance = Double.MAX_VALUE;

            for (BlockPos block : blocks) {
                double distance = block.getSquaredDistance(new Vec3i(playerPosition.x, playerPosition.y, playerPosition.z));

                if (distance < minDistance && distance < horizontal_distance * horizontal_distance) {
                    closestBlock = block;
                    minDistance = distance;
                }
            }

            if (closestBlock == null || closestBlock.getY() > playerPosition.y + vertical_distance || closestBlock.getY() < playerPosition.y - vertical_distance) {
                return null;
            }
        }
        return closestBlock;
    }
}
