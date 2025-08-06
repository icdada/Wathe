package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.block.entity.SeatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface TrainMurderMysteryEntities {
    EntityType<SeatEntity> SEAT = register("seat", EntityType.Builder.create(SeatEntity::new, SpawnGroup.MISC)
            .dimensions(1f, 1f)
            .maxTrackingRange(128)
            .disableSummon()
    );

    static void initialize() {
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, TrainMurderMystery.id(name), builder.build());
    }
}
