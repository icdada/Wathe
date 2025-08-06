package dev.doctor4t.trainmurdermystery.index.sound;

import net.minecraft.sound.BlockSoundGroup;

public interface TrainMurderMysteryBlockSoundGroups {
    BlockSoundGroup VENT_SHAFT = new BlockSoundGroup(
            1.0F,
            1.0F,
            TrainMurderMysterySounds.BLOCK_VENT_SHAFT_BREAK,
            TrainMurderMysterySounds.BLOCK_VENT_SHAFT_STEP,
            TrainMurderMysterySounds.BLOCK_VENT_SHAFT_PLACE,
            TrainMurderMysterySounds.BLOCK_VENT_SHAFT_HIT,
            TrainMurderMysterySounds.BLOCK_VENT_SHAFT_FALL
    );
}
