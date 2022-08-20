package com.reetling.biomeseverywhere;

import net.minecraft.core.Direction;

import java.util.Random;

public record GenerationUtils() {
    public static final Direction[] Planar = {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};

    public static Direction planar(Random random) {
        return Planar[random.nextInt(Planar.length)];
    }
}
