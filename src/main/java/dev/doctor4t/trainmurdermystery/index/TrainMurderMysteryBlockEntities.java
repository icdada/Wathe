package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.block_entity.SmallDoorBlockEntity;
import dev.doctor4t.trainmurdermystery.block_entity.SprinklerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface TrainMurderMysteryBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType.Builder<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, TrainMurderMystery.id(name), builder.build());
    }    BlockEntityType<SprinklerBlockEntity> SPRINKLER = create("sprinkler", BlockEntityType.Builder
            .create(SprinklerBlockEntity::new, TrainMurderMysteryBlocks.GOLD_SPRINKLER, TrainMurderMysteryBlocks.STAINLESS_STEEL_SPRINKLER)
    );

    static void initialize() {
    }    BlockEntityType<SmallDoorBlockEntity> SMALL_GLASS_DOOR = create("small_glass_door", BlockEntityType.Builder
            .create(SmallDoorBlockEntity::createGlass, TrainMurderMysteryBlocks.SMALL_GLASS_DOOR)
    );
    BlockEntityType<SmallDoorBlockEntity> SMALL_WOOD_DOOR = create("small_wood_door", BlockEntityType.Builder
            .create(SmallDoorBlockEntity::createWood, TrainMurderMysteryBlocks.SMALL_WOOD_DOOR)
    );





}
