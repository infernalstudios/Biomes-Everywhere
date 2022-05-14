package com.reetling.weirdworld.biomes;

import com.reetling.weirdworld.WeirdWorld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class AllBiomes {

    public static final ResourceKey<Biome> HONEYCOMB_FOREST = register("honeycomb_forest");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(WeirdWorld.MODID, name));
    }
}
