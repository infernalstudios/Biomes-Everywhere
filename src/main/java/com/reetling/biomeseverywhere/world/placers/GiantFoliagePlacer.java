package com.reetling.biomeseverywhere.world.placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetling.biomeseverywhere.registry.BEWTreePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class GiantFoliagePlacer extends FoliagePlacer {

    public static final Codec<GiantFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("crown_height").forGetter((placer) -> {
            return placer.crownHeight;
        })).apply(instance, GiantFoliagePlacer::new);
    });
    private final IntProvider crownHeight;

    public GiantFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.crownHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return BEWTreePlacers.GIANT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, TreeConfiguration config, int maxHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();

        for(int j = blockpos.getY() - foliageHeight; j <= blockpos.getY() + offset; ++j) {
            int k = blockpos.getY() - j;
            int l = foliageRadius + attachment.radiusOffset() + Mth.floor((float)k / (float)foliageHeight * 3.5F);

            this.placeLeavesRow(level, blockSetter, random, config, new BlockPos(blockpos.getX() + random.nextInt(5) - 2, j, blockpos.getZ() + random.nextInt(5) - 2), l, 0, attachment.doubleTrunk());
            this.placeLeavesRow(level, blockSetter, random, config, new BlockPos(blockpos.getX() + random.nextInt(5) - 2, j, blockpos.getZ() + random.nextInt(5) - 2), l * 2, 0, attachment.doubleTrunk());
            this.placeLeavesRow(level, blockSetter, random, config, new BlockPos(blockpos.getX(), j, blockpos.getZ()), l * 2, 0, attachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random random, int height, TreeConfiguration config) {
        return this.crownHeight.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int localX, int localY, int localZ, int range, boolean large) {
        if (localX + localZ >= 7) {
            return true;
        } else {
            return localX * localX + localZ * localZ > range * range;
        }
    }
}
