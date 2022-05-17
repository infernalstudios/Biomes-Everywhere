package com.reetling.weirdworld.world.placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.reetling.weirdworld.GenerationUtils;
import com.reetling.weirdworld.registry.WeirdWorldTreePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class BrushFoliagePlacer extends FoliagePlacer {

    public static final Codec<BrushFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return foliagePlacerParts(instance).and(IntProvider.codec(0, 16).fieldOf("trunk_height").forGetter((placer) -> {
            return placer.trunkHeight;
        })).apply(instance, BrushFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public BrushFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.trunkHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return WeirdWorldTreePlacers.BRUSH_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, TreeConfiguration config, int maxHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        int totalHeight = offset + foliageHeight;
        int i = 0;
        int j = (int) Math.floor((totalHeight - 2) / 2.0F);
        int k = (int) Math.ceil((totalHeight - 2) / 2.0F) + random.nextInt(2);

        for (int l = offset; l >= -foliageHeight - 3; --l) {
            if (i == 0) {
                this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), 0, l, attachment.doubleTrunk());
            } if (i == j + k + 1 || (i >= 1 && i <= j)) {
                this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), 1, l, attachment.doubleTrunk());
                if (i == j) {
                    for (Direction direction : GenerationUtils.Direction$Planar) {
                        if (random.nextInt(3) == 0) {
                            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos().relative(direction).relative(direction.getClockWise()), 0, l, attachment.doubleTrunk());
                        }
                    }
                }
            } if (i > j && i <= j + k) {

                for (Direction direction : GenerationUtils.Direction$Planar) { // randomly replace some outer blocks with air
                    this.placeLeavesRow(level, blockSetter, random, config, attachment.pos().relative(direction), 1, l, attachment.doubleTrunk());

                    this.placeLeavesRow(level, blockSetter, random, config, attachment.pos().relative(direction, 2).relative(random.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise()), 0, l, attachment.doubleTrunk());
                    if (random.nextInt(3) != 2) {
                        this.placeLeavesRow(level, blockSetter, random, config, attachment.pos().relative(direction, 2).relative(random.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise()), 0, l, attachment.doubleTrunk());
                    }
                }
            }
            i++;
        }
    }

    @Override
    public int foliageHeight(Random random, int height, TreeConfiguration config) {
        return Math.max(4, height - this.trunkHeight.sample(random));
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && range > 0;
    }
}
