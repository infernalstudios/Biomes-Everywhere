package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.WeirdWorld;
import com.reetling.weirdworld.biomes.BiomeDefinitions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WeirdWorldBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WeirdWorld.MODID);

    public static final RegistryObject<Biome> HONEYCOMB_FOREST = BIOMES.register("honeycomb_forest", BiomeDefinitions::honeycombForest);
    public static final ResourceKey<Biome> HONEYCOMB_FOREST_KEY = register("honeycomb_forest");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(WeirdWorld.MODID, name));
    }
}