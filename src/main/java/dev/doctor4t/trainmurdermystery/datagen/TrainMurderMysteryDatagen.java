package dev.doctor4t.trainmurdermystery.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.dimension.DimensionOptions;

import java.util.concurrent.CompletableFuture;

public class TrainMurderMysteryDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        // this is so that the dimension options can actually generate
        DynamicRegistries.register(RegistryKeys.DIMENSION, DimensionOptions.CODEC);

        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(TrainMurderMysteryModelGen::new);
        pack.addProvider(TrainMurderMysteryBlockTagGen::new);
        pack.addProvider(TrainMurderMysteryLangGen::new);
        pack.addProvider(TrainMurderMysteryBlockLootTableGen::new);
        pack.addProvider(DynamicRegistryGen::new);
    }

    public static class DynamicRegistryGen extends FabricDynamicRegistryProvider {


        public DynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        private static <T> void add(Entries entries, RegistryKey<Registry<T>> key) {
            entries.addAll((RegistryWrapper.Impl<T>) entries.getLookup(key));
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
            add(entries, RegistryKeys.NOISE_PARAMETERS);
            add(entries, RegistryKeys.BIOME);
            add(entries, RegistryKeys.CONFIGURED_FEATURE);
            add(entries, RegistryKeys.PLACED_FEATURE);
            add(entries, RegistryKeys.DENSITY_FUNCTION);
            add(entries, RegistryKeys.CHUNK_GENERATOR_SETTINGS);
            add(entries, RegistryKeys.DIMENSION);
            add(entries, RegistryKeys.DIMENSION_TYPE);
            add(entries, RegistryKeys.STRUCTURE);
            add(entries, RegistryKeys.STRUCTURE_SET);
            add(entries, RegistryKeys.PAINTING_VARIANT);
        }

        @Override
        public String getName() {
            return dev.doctor4t.trainmurdermystery.TrainMurderMystery.MOD_ID + "_dynamic_registry";
        }
    }
}
