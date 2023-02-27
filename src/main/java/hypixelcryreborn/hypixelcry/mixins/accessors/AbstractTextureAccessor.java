package hypixelcryreborn.hypixelcry.mixins.accessors;

import net.minecraft.client.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractTexture.class)
public class AbstractTextureAccessor {
    @Shadow
    public int glId;
}
