package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.WeirdWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class WeirdWorldPlacedFeatures {
    private static BlockPredicateFilter needsSoil() { return BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)); }

    public static final Holder<PlacedFeature> PLACED_TREE_ASPEN = place("placed_aspen_tree", WeirdWorldConfiguredFeatures.TREE_ASPEN,
            CountOnEveryLayerPlacement.of(2), BiomeFilter.biome(), needsSoil());
    public static Holder<PlacedFeature> place(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(new ResourceLocation(WeirdWorld.MODID, name).toString(), configuredFeature, List.of(placementModifiers));
    }

    public static void registerPlacedFeatures() {
        register("aspen_tree", PLACED_TREE_ASPEN);
    }

    private static PlacedFeature register(String name, Holder<PlacedFeature> feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(WeirdWorld.MODID, name), feature.value());
    }
}
