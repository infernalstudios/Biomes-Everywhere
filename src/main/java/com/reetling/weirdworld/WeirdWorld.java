package com.reetling.weirdworld;

import com.mojang.logging.LogUtils;
import com.reetling.weirdworld.generation.FeaturesToBiomes;
import com.reetling.weirdworld.registry.WeirdWorldPlacedFeatures;
import com.reetling.weirdworld.world.WeirdWorldRegion;
import com.reetling.weirdworld.world.WeirdWorldSurfaces;
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

@Mod(WeirdWorld.MODID)
public class WeirdWorld {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weirdworld";

    public WeirdWorld() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);

        forgeBus.register(new FeaturesToBiomes());
    }

    public void clientSetup(FMLClientSetupEvent event) {
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        WeirdWorldPlacedFeatures.registerPlacedFeatures();
        event.enqueueWork(() -> {
            Regions.register(new WeirdWorldRegion(new ResourceLocation(MODID, "overworld"), 2));

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, WeirdWorldSurfaces.makeRules());
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
