package com.reetling.biomeseverywhere.world;

import com.reetling.biomeseverywhere.registry.BEWBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class BEWSurfaces {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static final SurfaceRules.RuleSource MOSS_BLOCK = makeStateRule(Blocks.MOSS_BLOCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource biomeSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(is(BEWBiomes.ANCIENT_WOODS_KEY), MOSS_BLOCK),
                        SurfaceRules.ifTrue(is(BEWBiomes.HONEYCOMB_FOREST_KEY), GRASS_BLOCK))),
                DIRT);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, biomeSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.ConditionSource is(ResourceKey<Biome> biome) {
        return SurfaceRules.isBiome(biome);
    }
}
