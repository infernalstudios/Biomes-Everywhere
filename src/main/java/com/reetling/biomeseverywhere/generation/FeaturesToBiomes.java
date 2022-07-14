package com.reetling.biomeseverywhere.generation;

import com.reetling.biomeseverywhere.BiomesEverywhere;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesEverywhere.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
//            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_TREE_ASPEN);
//        }
    }
}
