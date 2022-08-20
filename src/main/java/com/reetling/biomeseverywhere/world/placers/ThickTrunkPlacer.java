package com.reetling.biomeseverywhere.world.placers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import static com.reetling.biomeseverywhere.GenerationUtils.Planar;
import static com.reetling.biomeseverywhere.GenerationUtils.planar;

import com.reetling.biomeseverywhere.registry.BEWTreePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class ThickTrunkPlacer extends TrunkPlacer {

    public static final Codec<ThickTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return trunkPlacerParts(instance).apply(instance, ThickTrunkPlacer::new);
    });

    public ThickTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return BEWTreePlacers.THICK_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {

        int r = random.nextInt(Planar.length);
        for (int i = -2; i < 3 + random.nextInt(4); i++) {
            placeWithRadius(level, blockSetter, random, pos.relative(Planar[r], 2).above(i).mutable(), config, 4);
            placeWithRadius(level, blockSetter, random, pos.relative(Planar[r]).above(i + 1).mutable(), config, 4);
        }

        for (int i = -2; i < 4; i++) {
            int r1 = random.nextInt(Planar.length);
            placeWithRadius(level, blockSetter, random, pos.relative(Planar[r1]).relative(Planar[r1].getClockWise()).above(i).mutable(), config, 4);
        }

        BlockPos pos1 = pos;
        boolean offset = false;
        int stalk = 0;
        for (int i = 0; i < freeTreeHeight; i++) {
            if (i < 0.4 * freeTreeHeight) {
                placeWithRadius(level, blockSetter, random, pos.above(i).mutable(), config, 4);
            } else if (i < 0.75 * freeTreeHeight) {
                if (!offset) {
                    int r2 = random.nextInt(Planar.length);
                    pos = pos.relative(Planar[r2]).relative(Planar[r2].getClockWise());
                    offset = true;
                }
                placeWithRadius(level, blockSetter, random, pos.above(i).mutable(), config, 3);
                if (stalk < 6) {
                    placeWithRadius(level, blockSetter, random, pos1.above(i).mutable(), config, 3);
                    stalk++;
                }
            } else {
                if (offset) {
                    int r3 = random.nextInt(Planar.length);
                    pos1 = pos;
                    pos = pos.relative(Planar[r3]).relative(Planar[r3].getClockWise());
                    offset = false;
                }
                placeWithRadius(level, blockSetter, random, pos.above(i).mutable(), config, 2);
                if (stalk < 11) {
                    placeWithRadius(level, blockSetter, random, pos1.above(i).mutable(), config, 2);
                    stalk++;
                } else if (i == freeTreeHeight - 1) {
                    int r4 = random.nextInt(Planar.length);
                    for (int j = 0; j < 6; j++) {
                        placeLogIfFree(level, blockSetter, random, pos.above(i + j).mutable(), config);
                        placeLogIfFree(level, blockSetter, random, pos.relative(Planar[r4].getClockWise()).above(i + j).mutable(), config);
                        placeLogIfFree(level, blockSetter, random, pos.relative(Planar[r4]).above(i + j).mutable(), config);
                        placeLogIfFree(level, blockSetter, random, pos.relative(Planar[r4]).relative(Planar[r4].getClockWise()).above(i + j).mutable(), config);
                    }
                }
            }
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(freeTreeHeight), 0, true));
    }

    private void placeWithRadius(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, BlockPos.MutableBlockPos pos, TreeConfiguration config, int radius) {
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                if (i*i + j*j < radius*radius*0.75) {
                    placeLogIfFree(level, blockSetter, random, pos.offset(i, 0, j).mutable(), config);
                }
            }
        }
    }
}
