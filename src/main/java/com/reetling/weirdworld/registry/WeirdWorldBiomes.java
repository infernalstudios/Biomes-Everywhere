package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.biomes.AllBiomes;
import com.reetling.weirdworld.biomes.BiomeDefinitions;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeirdWorldBiomes {

    public static void registerBiomes (RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();
        registry.register(BiomeDefinitions.honeycombForest().setRegistryName(AllBiomes.HONEYCOMB_FOREST.getRegistryName()));
    }
}
