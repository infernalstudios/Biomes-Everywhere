package com.reetling.biomeseverywhere;

import com.mojang.logging.LogUtils;
import com.reetling.biomeseverywhere.generation.FeaturesToBiomes;
import com.reetling.biomeseverywhere.registry.BEWBiomes;
import com.reetling.biomeseverywhere.registry.BEWConfiguredFeatures;
import com.reetling.biomeseverywhere.registry.BEWPlacedFeatures;
import com.reetling.biomeseverywhere.registry.BEWTreePlacers;
import com.reetling.biomeseverywhere.world.BEWRegion;
import com.reetling.biomeseverywhere.world.BEWSurfaces;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(BiomesEverywhere.MODID)
public class BiomesEverywhere {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "biomeseverywhere";

    public BiomesEverywhere() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);

        forgeBus.register(new FeaturesToBiomes());

        BEWBiomes.BIOMES.register(bus);
        BEWConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        BEWTreePlacers.FOLIAGE_PLACERS.register(bus);
        BEWPlacedFeatures.PLACED_FEATURES.register(bus);
    }

    public void clientSetup(FMLClientSetupEvent event) {
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new BEWRegion(new ResourceLocation(MODID, "overworld"), 2));

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, BEWSurfaces.makeRules());
        });
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
        }
        if (event.includeServer()) {
        }
    }
}
