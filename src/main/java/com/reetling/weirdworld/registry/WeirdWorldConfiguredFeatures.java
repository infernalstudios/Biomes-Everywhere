package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.WeirdWorld;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WeirdWorldConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, WeirdWorld.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_TREE_ASPEN = CONFIGURED_FEATURES.register("tree_aspen",
            () -> new ConfiguredFeature<>(Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(Blocks.BIRCH_LOG),
                            new StraightTrunkPlacer(5, 2, 6),
                            BlockStateProvider.simple(Blocks.HONEYCOMB_BLOCK),
                            new BlobFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2), 6),
                            new TwoLayersFeatureSize(1, 0, 1))
                            .ignoreVines().build()));
}
