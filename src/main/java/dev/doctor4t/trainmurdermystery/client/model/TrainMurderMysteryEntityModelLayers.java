package dev.doctor4t.trainmurdermystery.client.model;

import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.client.render.block_entity.SmallDoorBlockEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public interface TrainMurderMysteryEntityModelLayers {
    EntityModelLayer SMALL_DOOR = layer("small_door");

    static void initialize() {
        EntityModelLayerRegistry.registerModelLayer(SMALL_DOOR, SmallDoorBlockEntityRenderer::getTexturedModelData);
    }

    private static EntityModelLayer layer(String id, String name) {
        return new EntityModelLayer(TrainMurderMystery.id(id), name);
    }

    private static EntityModelLayer layer(String id) {
        return new EntityModelLayer(TrainMurderMystery.id(id), "main");
    }

}
