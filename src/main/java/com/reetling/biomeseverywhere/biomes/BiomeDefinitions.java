package com.reetling.biomeseverywhere.biomes;

import com.reetling.biomeseverywhere.registry.BEWPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import javax.annotation.Nullable;

public class BiomeDefinitions {

    @Nullable
    private static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
    }

    public static Biome honeycombForest() {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(builder);

        BiomeDefaultFeatures.addDefaultGrass(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobs);
        BiomeDefaultFeatures.commonSpawns(mobs);
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BEE, 1, 1, 1));

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_TREE_ASPEN.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_SMALL_TREE_ASPEN.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_PATCH_DANDELION.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_PATCH_TALL_GRASS.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_PATCH_SUNFLOWER.getHolder().get());

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.6F, 0.6F, mobs, builder, NORMAL_MUSIC);
    }

    public static Biome ancientWoods() {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(builder);

        BiomeDefaultFeatures.addDefaultGrass(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobs);
        BiomeDefaultFeatures.commonSpawns(mobs);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BEWPlacedFeatures.PLACED_TREE_SEQUOIA.getHolder().get());

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.TAIGA, 0.3F, 0.8F, mobs, builder, NORMAL_MUSIC);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return biome(precipitation, category, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }
}
