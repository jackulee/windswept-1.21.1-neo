package com.rosemods.windswept.core;

import com.rosemods.windswept.core.data.client.*;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.data.server.WindsweptLootTableProvider;
import com.rosemods.windswept.core.data.server.WindsweptRecipeProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptAdvancementModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptChunkGeneratorModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptLootModifierProvider;
import com.rosemods.windswept.core.data.server.tags.*;
import com.rosemods.windswept.core.other.*;
import com.rosemods.windswept.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.gallery.core.data.client.GalleryAssetsRemolderProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Windswept.MOD_ID)
public class Windswept {
    public static final String MOD_ID = "windswept";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public Windswept(IEventBus bus, ModContainer container) {
        WindsweptDataProcessors.registerTrackedData();
        NeoForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(bus);
        WindsweptTreeDecorators.DECORATORS.register(bus);
        WindsweptFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        WindsweptFeatures.FEATURES.register(bus);
        WindsweptEnchantments.ENCHANTMENTS.register(bus);
        WindsweptAttributes.ATTRIBUTES.register(bus);
        WindsweptBannerPatterns.BANNER_PATTERNS.register(bus);
        WindsweptTrunkPlacers.TRUNK_PLACERS.register(bus);
        WindsweptPaintingVariants.PAINTING_VARIANTS.register(bus);
        WindsweptParticleTypes.PARTICLE_TYPES.register(bus);
        WindsweptPotPatterns.DECORATED_POT_PATTERNS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::dataSetup);

        container.registerConfig(ModConfig.Type.COMMON, WindsweptConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, WindsweptConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WindsweptVillagerTypes.registerVillagerTypes();
            WindsweptBlockInfo.changeLocalisation();
            WindsweptBlockInfo.registerCompostables();
            WindsweptBlockInfo.registerFlammables();
            WindsweptEffects.registerPotionRecipes();
            WindsweptDispenseBehaviors.registerDispenseBehaviors();
            WindsweptCauldronInteractions.registerCauldronInteractions();
            WindsweptCreativeTabs.setupTabEditors();
            WindsweptPotPatterns.registerPatterns();
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean client = event.includeClient();
        boolean server = event.includeServer();

        gen.addProvider(client, new WindsweptSoundProvider(output, helper));
        gen.addProvider(client, new WindsweptLangProvider(output));
        gen.addProvider(client, new WindsweptModelProvider(output, helper));
        gen.addProvider(client, new WindsweptSplashProvider(output, helper));
        gen.addProvider(client, new WindsweptParticleProvider(output, helper));
        gen.addProvider(client, new WindsweptSpriteSourceProvider(output, provider, helper));
        gen.addProvider(client, new GalleryAssetsRemolderProvider(MOD_ID, output, provider));

        WindsweptDatapackProvider dataPack = new WindsweptDatapackProvider(output, provider);
        gen.addProvider(server, dataPack);
        provider = dataPack.getRegistryProvider();

        WindsweptBlockTagProvider blockTags = new WindsweptBlockTagProvider(output, provider, helper);
        gen.addProvider(server, blockTags);
        gen.addProvider(server, new WindsweptItemTagProvider(output, provider, blockTags.contentsGetter(), helper));
        gen.addProvider(server, new WindsweptEntityTagProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptBiomeTagProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptStructureTagsProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptTrimMaterialTagsProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptBannerPatternTagProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptLootTableProvider(output, provider));
        gen.addProvider(server, new WindsweptRecipeProvider(output, provider));
        gen.addProvider(server, new WindsweptAdvancementModifierProvider(output, provider));
        gen.addProvider(server, new WindsweptLootModifierProvider(output, provider));
        gen.addProvider(server, new WindsweptPaintingVariantTagsProvider(output, provider, helper));
        gen.addProvider(server, new WindsweptChunkGeneratorModifierProvider(output, provider));
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}