package hypixelcryreborn.hypixelcry.mixins.world;

import hypixelcryreborn.hypixelcry.events.world.EntityAddedEvent;
import hypixelcryreborn.hypixelcry.events.world.EntityRemoveEvent;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Shadow
    @Nullable
    public Entity getEntityById(int id) {
        return null;
    }

    @Inject(method = "addEntityPrivate", at = @At("TAIL"))
    private void onAddEntityPrivate(int id, Entity entity, CallbackInfo info) {
        if (entity != null) {
            ActionResult result = EntityAddedEvent.EVENT.invoker().add(entity);
        }
    }

    @Inject(method = "removeEntity", at = @At("HEAD"))
    private void onRemoveEntity(int entityId, Entity.RemovalReason removalReason, CallbackInfo info) {
        if (getEntityById(entityId) != null)  {
            ActionResult result = EntityRemoveEvent.EVENT.invoker().remove(getEntityById(entityId));
        }
    }
}
