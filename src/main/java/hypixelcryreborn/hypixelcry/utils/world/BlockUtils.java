package hypixelcryreborn.hypixelcry.utils.world;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockUtils {
    public void BreakBlock(BlockPos pos) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraft.player;
        World world = minecraft.world;
        if (world != null && player != null) {

            // Send packet to start breaking block
            player.swingHand(Hand.MAIN_HAND);
            player.networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, Direction.DOWN));

            // Get block hardness and tool speed
            BlockState state = world.getBlockState(pos);
            int toolSpeed = EnchantmentHelper.getEfficiency(player);
            float hardness = state.getHardness(world, pos) * (1.0F / toolSpeed);

            ItemStack tool = player.getMainHandStack();
            float speed = 0;
            if (tool != ItemStack.EMPTY) {
                int efficiencyLevel = EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, tool);
                // Apply efficiency bonus
                speed = hardness * (1.0F / efficiencyLevel * 0.3F);
            }

            // Wait for block to break
            try {
                Thread.sleep((long) (speed * 750.0F) + 50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (player != null) {
                player.networkHandler.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, Direction.DOWN));
            }
        }
    }
    
}
