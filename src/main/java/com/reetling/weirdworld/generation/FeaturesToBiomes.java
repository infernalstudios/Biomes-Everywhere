package com.reetling.weirdworld.generation;

import com.reetling.weirdworld.WeirdWorld;
import com.reetling.weirdworld.registry.WeirdWorldPlacedFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WeirdWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FeaturesToBiomes {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void biomeLoad(BiomeLoadingEvent event) {
        if (event.getName() == null) {
            return;
        }

//        ResourceKey<Biome> biome = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
//        BiomeGenerationSettingsBuilder generation = event.getGeneration();
//
//        if (biome == Biomes.BIRCH_FOREST || biome == Biomes.OLD_GROWTH_BIRCH_FOREST) {
//            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WeirdWorldPlacedFeatures.PLACED_TREE_ASPEN);
//        }
    }
}
