package hypixelcryreborn.hypixelcry.events.world;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;

public interface EntityRemoveEvent {
    Event<EntityRemoveEvent> EVENT = EventFactory.createArrayBacked(EntityRemoveEvent.class,
            (listeners) -> (entity) -> {
                for (EntityRemoveEvent listener : listeners) {
                    ActionResult result = listener.remove(entity);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult remove(Entity entity);
}
