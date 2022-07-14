package com.reetling.biomeseverywhere.registry;

import com.reetling.biomeseverywhere.BiomesEverywhere;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class BEWPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BiomesEverywhere.MODID);

    public static final RegistryObject<PlacedFeature> PLACED_TREE_ASPEN = PLACED_FEATURES.register("placed_tree_aspen",
            () -> new PlacedFeature(BEWConfiguredFeatures.CONFIGURED_TREE_ASPEN.getHolder().get(),
                    List.of(CountOnEveryLayerPlacement.of(6), BiomeFilter.biome(), ConfiguredFilters.needsSoil())));

    public static final RegistryObject<PlacedFeature> PLACED_PATCH_DANDELION = PLACED_FEATURES.register("placed_patch_dandelion",
            () -> new PlacedFeature(BEWConfiguredFeatures.CONFIGURED_PATCH_DANDELION.getHolder().get(),
                    List.of(CountPlacement.of(96), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PLACED_PATCH_SUNFLOWER = PLACED_FEATURES.register("placed_patch_sunflower",
            () -> new PlacedFeature(BEWConfiguredFeatures.CONFIGURED_PATCH_SUNFLOWER.getHolder().get(),
                    List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    private static class ConfiguredFilters {
        private static BlockPredicateFilter needsSoil() {
            return BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO));
        }
    }
}
