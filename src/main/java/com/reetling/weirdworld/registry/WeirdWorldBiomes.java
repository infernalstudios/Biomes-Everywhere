package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.WeirdWorld;
import com.reetling.weirdworld.biomes.AllBiomes;
import com.reetling.weirdworld.biomes.BiomeDefinitions;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WeirdWorldBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WeirdWorld.MODID);

    public static final RegistryObject<Biome> HONEYCOMB_FOREST = BIOMES.register("honeycomb_forest", BiomeDefinitions::honeycombForest);
}