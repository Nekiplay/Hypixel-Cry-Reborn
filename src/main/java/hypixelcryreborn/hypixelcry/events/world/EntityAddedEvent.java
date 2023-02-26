package hypixelcryreborn.hypixelcry.events.world;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface  EntityAddedEvent {
    Event<EntityAddedEvent> EVENT = EventFactory.createArrayBacked(EntityAddedEvent.class,
            (listeners) -> (entity) -> {
                for (EntityAddedEvent listener : listeners) {
                    ActionResult result = listener.add(entity);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult add(Entity entity);
}
