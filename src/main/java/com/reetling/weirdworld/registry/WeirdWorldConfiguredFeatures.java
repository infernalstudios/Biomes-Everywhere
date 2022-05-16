package com.reetling.weirdworld.registry;

import com.reetling.weirdworld.WeirdWorld;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeirdWorldConfiguredFeatures {

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_ASPEN = configure("aspen_tree", Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.BIRCH_LOG),
                    new StraightTrunkPlacer(6, 2, 2),
                    BlockStateProvider.simple(Blocks.HONEYCOMB_BLOCK),
                    new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(5), 4),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> configure(String name, F feature, FC configuration) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(WeirdWorld.MODID, name).toString(), new ConfiguredFeature<>(feature, configuration));
    }
}
