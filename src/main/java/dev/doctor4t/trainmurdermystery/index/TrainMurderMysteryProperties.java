package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.trainmurdermystery.block.property.CouchArms;
import dev.doctor4t.trainmurdermystery.block.property.OrnamentShape;
import dev.doctor4t.trainmurdermystery.block.property.RailingShape;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public interface TrainMurderMysteryProperties {
    BooleanProperty ACTIVE = BooleanProperty.of("active"); // whether a block is receiving power from a breaker
    BooleanProperty FULL = BooleanProperty.of("full");
    BooleanProperty INTERACTION_COOLDOWN = BooleanProperty.of("interaction_cooldown");
    BooleanProperty LEFT = BooleanProperty.of("left");
    BooleanProperty OPAQUE = BooleanProperty.of("opaque");
    BooleanProperty RIGHT = BooleanProperty.of("right");
    BooleanProperty ROOT = BooleanProperty.of("root");
    BooleanProperty SUPPORT = BooleanProperty.of("support");
    BooleanProperty TOP = BooleanProperty.of("top");
    BooleanProperty UNBREAKABLE = BooleanProperty.of("unbreakable");

    IntProperty CANISTER_LEVEL = IntProperty.of("level", 0, 8);
    IntProperty MARSHMALLOW_LEVEL = IntProperty.of("level", 0, 8);
    IntProperty PART_12 = IntProperty.of("part", 0, 11);
    IntProperty PART_18 = IntProperty.of("part", 0, 17);
    IntProperty SPOOLS = IntProperty.of("spools", 1, 8);

    EnumProperty<CouchArms> COUCH_ARMS = EnumProperty.of("arms", CouchArms.class);
    EnumProperty<OrnamentShape> ORNAMENT_SHAPE = EnumProperty.of("shape", OrnamentShape.class);
    EnumProperty<RailingShape> RAILING_SHAPE = EnumProperty.of("shape", RailingShape.class);
}
