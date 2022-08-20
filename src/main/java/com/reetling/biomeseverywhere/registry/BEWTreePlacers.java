package com.reetling.biomeseverywhere.registry;

import com.reetling.biomeseverywhere.BiomesEverywhere;
import com.reetling.biomeseverywhere.world.placers.BrushFoliagePlacer;
import com.reetling.biomeseverywhere.world.placers.ThickTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BEWTreePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BiomesEverywhere.MODID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, BiomesEverywhere.MODID);

    public static final RegistryObject<FoliagePlacerType<BrushFoliagePlacer>> BRUSH_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("brush_foliage_placer", () -> new FoliagePlacerType<>(BrushFoliagePlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<ThickTrunkPlacer>> THICK_TRUNK_PLACER = TRUNK_PLACERS.register("thick_trunk_placer", () -> new TrunkPlacerType<>(ThickTrunkPlacer.CODEC));
}
