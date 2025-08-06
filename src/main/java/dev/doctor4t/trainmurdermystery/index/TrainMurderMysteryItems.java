package dev.doctor4t.trainmurdermystery.index;

import com.mojang.datafixers.util.Pair;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface TrainMurderMysteryItems {

    RegistryKey<ItemGroup> TrainMurderMystery_BUILDING_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("building"));
    RegistryKey<ItemGroup> TrainMurderMystery_DECORATION_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("decoration"));
    RegistryKey<ItemGroup> TrainMurderMystery_EQUIPMENT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("equipment"));

    List<Pair<Item, RegistryKey<ItemGroup>>> ITEMS = new ArrayList<>();

    static <T extends Item> T create(String name, T item, RegistryKey<ItemGroup> itemGroup) {
        ITEMS.add(new Pair<>(item, itemGroup));
        return Registry.register(Registries.ITEM, TrainMurderMystery.id(name), item);
    }

    static void initialize() {
        Registry.register(Registries.ITEM_GROUP, TrainMurderMystery_BUILDING_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.trainmurdermystery.building"))
                .icon(() -> new ItemStack(TrainMurderMysteryBlocks.TARNISHED_GOLD_PILLAR))
                .build());
        Registry.register(Registries.ITEM_GROUP, TrainMurderMystery_DECORATION_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.trainmurdermystery.decoration"))
                .icon(() -> new ItemStack(TrainMurderMysteryBlocks.TARNISHED_GOLD_VENT_SHAFT))
                .build());

        Map<Item, Consumer<FabricItemGroupEntries>> stackAppenders = new HashMap<>();
        ItemGroupEvents.modifyEntriesEvent(TrainMurderMystery_BUILDING_GROUP).register(entries -> addBuildingGroupEntries(entries, stackAppenders));
        ItemGroupEvents.modifyEntriesEvent(TrainMurderMystery_DECORATION_GROUP).register(entries -> addFunctionalGroupEntries(entries, stackAppenders));
        ItemGroupEvents.modifyEntriesEvent(TrainMurderMystery_EQUIPMENT_GROUP).register(entries -> addEquipmentGroupEntries(entries, stackAppenders));
    }

    private static void addBuildingGroupEntries(FabricItemGroupEntries entries, Map<Item, Consumer<FabricItemGroupEntries>> stackAppenders) {
        ITEMS.stream().filter(pair -> pair.getSecond() == TrainMurderMystery_BUILDING_GROUP).forEach(pair -> stackAppenders.getOrDefault(pair.getFirst(), itemGroupEntries -> itemGroupEntries.add(pair.getFirst())).accept(entries));
    }

    private static void addFunctionalGroupEntries(FabricItemGroupEntries entries, Map<Item, Consumer<FabricItemGroupEntries>> stackAppenders) {
        ITEMS.stream().filter(pair -> pair.getSecond() == TrainMurderMystery_DECORATION_GROUP).forEach(pair -> stackAppenders.getOrDefault(pair.getFirst(), itemGroupEntries -> itemGroupEntries.add(pair.getFirst())).accept(entries));
    }

    private static void addEquipmentGroupEntries(FabricItemGroupEntries entries, Map<Item, Consumer<FabricItemGroupEntries>> stackAppenders) {
        ITEMS.stream().filter(pair -> pair.getSecond() == TrainMurderMystery_EQUIPMENT_GROUP).forEach(pair -> stackAppenders.getOrDefault(pair.getFirst(), itemGroupEntries -> itemGroupEntries.add(pair.getFirst())).accept(entries));
    }
}
