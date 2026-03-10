package com.rosemods.windswept.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptPaintingVariants;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptModelProvider extends BlueprintBlockStateProvider {
    public WindsweptModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Windswept.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        // item models
        this.generatedItem(HOLLY_BOAT.getFirst());
        this.generatedItem(HOLLY_BOAT.getSecond());
        this.generatedItem(CHESTNUT_BOAT.getFirst());
        this.generatedItem(CHESTNUT_BOAT.getSecond());
        this.generatedItem(PINE_BOAT.getFirst());
        this.generatedItem(PINE_BOAT.getSecond());
        this.generatedItem(HOLLY_BERRIES);
        this.generatedItem(WOODEN_BUCKET);
        this.generatedItem(WOODEN_MILK_BUCKET);
        this.generatedItem(WOODEN_POWDER_SNOW_BUCKET);
        this.generatedItem(WOODEN_WATER_BUCKET);
        this.generatedItem(WOODEN_HONEY_BUCKET);
        this.generatedItem(WOODEN_CHOCOLATE_BUCKET);
        this.generatedItem(ELDER_FEATHER);
        this.generatedItem(FEATHER_CLOAK);
        this.generatedItem(WILD_BERRIES);
        this.generatedItem(MUTTON_PIE);
        this.generatedItem(GOAT);
        this.generatedItem(COOKED_GOAT);
        this.generatedItem(GOAT_STEW);
        this.generatedItem(WILD_BERRY_PIPS);
        this.generatedItemWithOverlay(SNOW_BOOTS);
        this.generatedItem(FROST_ARROW);
        this.itemOnAStick(HOLLY_BERRIES_ON_A_STICK);
        this.generatedItem(FROZEN_FLESH);
        this.generatedItem(FROZEN_BRANCH);
        this.generatedItem(HOOT_POTTERY_SHERD);
        this.generatedItem(PLUMAGE_POTTERY_SHERD);
        this.generatedItem(OFFSHOOT_POTTERY_SHERD);
        this.generatedItem(FLAKE_POTTERY_SHERD);
        this.generatedItem(DRUPES_POTTERY_SHERD);
        this.generatedItem(SNOW_GOLEM_BANNER_PATTERN);
        this.generatedItem(SNOW_CHARGE_BANNER_PATTERN);
        this.generatedItem(ROSE_FLOWER_BANNER_PATTERN);
        this.generatedItem(MUSIC_DISC_RAIN);
        this.generatedItem(MUSIC_DISC_SNOW);
        this.generatedItem(MUSIC_DISC_BUMBLEBEE);
        this.spawnEggItem(CHILLED_SPAWN_EGG, FROSTBITER_SPAWN_EGG);
        this.generatedItem(HOLLY_FURNACE_BOAT);
        this.generatedItem(LARGE_HOLLY_BOAT);
        this.generatedItem(CHESTNUT_FURNACE_BOAT);
        this.generatedItem(LARGE_CHESTNUT_BOAT);
        this.generatedItem(PINE_FURNACE_BOAT);
        this.generatedItem(LARGE_PINE_BOAT);
        this.generatedItem(CHESTNUTS);
        this.generatedItem(ROASTED_CHESTNUTS);
        this.generatedItem(CHESTNUT_SOUP);
        this.generatedItem(GINGER_ROOT);
        this.generatedItem(GINGERBREAD_COOKIE);
        this.generatedItem(SPICY_SNOW_CONE);
        this.generatedItem(SWEET_SNOW_CONE);
        this.generatedItem(MINTY_SNOW_CONE);
        this.generatedItem(GINGER_TEA);
        this.handheldItem(CANDY_CANE);
        this.generatedItem(LAVENDER_TEA);
        this.generatedItem(LAVENDER_CROWN);
        this.generatedItem(ANTLER_HELMET);
        this.generatedItem(PINECONE_JAM_BOTTLE);

        this.painting(WindsweptPaintingVariants.CLIFFSIDE);
        this.painting(WindsweptPaintingVariants.DRESS_CODES);
        this.painting(WindsweptPaintingVariants.ECOTONAL_PAREIDOLIA);
        this.painting(WindsweptPaintingVariants.THE_FOILS);
        this.painting(WindsweptPaintingVariants.AURORAE);
        this.painting(WindsweptPaintingVariants.HEARTH_RUG);
        this.painting(WindsweptPaintingVariants.ALLU_PINE);

        // holly
        this.logBlocks(HOLLY_LOG, HOLLY_WOOD);
        this.logBlocks(STRIPPED_HOLLY_LOG, STRIPPED_HOLLY_WOOD);
        this.cubeAll(HOLLY_PLANKS);
        this.slabBlock(HOLLY_SLAB, HOLLY_PLANKS);
        this.stairsBlock(HOLLY_STAIRS, HOLLY_PLANKS);
        this.fenceBlock(HOLLY_FENCE, HOLLY_PLANKS);
        this.fenceGateBlock(HOLLY_FENCE_GATE, HOLLY_PLANKS);
        this.pressurePlateBlock(HOLLY_PRESSURE_PLATE, HOLLY_PLANKS);
        this.doorBlock(HOLLY_DOOR);
        this.trapdoorBlock(HOLLY_TRAPDOOR);
        this.buttonBlock(HOLLY_BUTTON, HOLLY_PLANKS);
        this.signBlocks(HOLLY_SIGNS, HOLLY_PLANKS);
        this.leavesBlocks(HOLLY_LEAVES, HOLLY_LEAF_PILE);
        this.crossBlockWithPot(HOLLY_SAPLING, POTTED_HOLLY_SAPLING);
        this.beehiveBlock(HOLLY_BEEHIVE);
        this.ladderBlock(HOLLY_LADDER);
        this.bookshelfBlock(HOLLY_BOOKSHELF, HOLLY_PLANKS);
        this.chiseledBookshelfBlock(CHISELED_HOLLY_BOOKSHELF);
        this.boardsBlock(HOLLY_BOARDS);
        this.chestBlocks(HOLLY_CHEST, TRAPPED_HOLLY_CHEST, HOLLY_PLANKS);
        this.directionalBlock(HOLLY_BERRY_BASKET);
        this.hangingSignBlocks(STRIPPED_HOLLY_LOG, HOLLY_HANGING_SIGNS);

        // chestnut
        this.logBlocks(CHESTNUT_LOG, CHESTNUT_WOOD);
        this.logBlocks(STRIPPED_CHESTNUT_LOG, STRIPPED_CHESTNUT_WOOD);
        this.cubeAll(CHESTNUT_PLANKS);
        this.slabBlock(CHESTNUT_SLAB, CHESTNUT_PLANKS);
        this.stairsBlock(CHESTNUT_STAIRS, CHESTNUT_PLANKS);
        this.fenceBlock(CHESTNUT_FENCE, CHESTNUT_PLANKS);
        this.fenceGateBlock(CHESTNUT_FENCE_GATE, CHESTNUT_PLANKS);
        this.pressurePlateBlock(CHESTNUT_PRESSURE_PLATE, CHESTNUT_PLANKS);
        this.doorBlock(CHESTNUT_DOOR);
        this.trapdoorBlock(CHESTNUT_TRAPDOOR);
        this.buttonBlock(CHESTNUT_BUTTON, CHESTNUT_PLANKS);
        this.signBlocks(CHESTNUT_SIGNS, CHESTNUT_PLANKS);
        this.leavesBlocks(CHESTNUT_LEAVES, CHESTNUT_LEAF_PILE);
        this.crossBlockWithPot(CHESTNUT_SAPLING, POTTED_CHESTNUT_SAPLING);
        this.beehiveBlock(CHESTNUT_BEEHIVE);
        this.ladderBlock(CHESTNUT_LADDER);
        this.bookshelfBlock(CHESTNUT_BOOKSHELF, CHESTNUT_PLANKS);
        this.chiseledBookshelfBlock(CHISELED_CHESTNUT_BOOKSHELF);
        this.boardsBlock(CHESTNUT_BOARDS);
        this.chestBlocks(CHESTNUT_CHEST, TRAPPED_CHESTNUT_CHEST, CHESTNUT_PLANKS);
        this.hangingSignBlocks(STRIPPED_CHESTNUT_LOG, CHESTNUT_HANGING_SIGNS);

        // pine
        this.logBlocks(PINE_LOG, PINE_WOOD);
        this.logBlocks(WEATHERED_PINE_LOG, WEATHERED_PINE_WOOD);
        this.logBlocks(STRIPPED_PINE_LOG, STRIPPED_PINE_WOOD);
        this.cubeAll(PINE_PLANKS);
        this.slabBlock(PINE_SLAB, PINE_PLANKS);
        this.stairsBlock(PINE_STAIRS, PINE_PLANKS);
        this.fenceBlock(PINE_FENCE, PINE_PLANKS);
        this.fenceGateBlock(PINE_FENCE_GATE, PINE_PLANKS);
        this.pressurePlateBlock(PINE_PRESSURE_PLATE, PINE_PLANKS);
        this.doorBlock(PINE_DOOR);
        this.trapdoorBlock(PINE_TRAPDOOR);
        this.buttonBlock(PINE_BUTTON, PINE_PLANKS);
        this.signBlocks(PINE_SIGNS, PINE_PLANKS);
        this.leavesBlocks(PINE_LEAVES, PINE_LEAF_PILE);
        this.crossBlockWithPot(PINE_SAPLING, POTTED_PINE_SAPLING);
        this.beehiveBlock(PINE_BEEHIVE);
        this.ladderBlock(PINE_LADDER);
        this.bookshelfBlock(PINE_BOOKSHELF, PINE_PLANKS);
        this.chiseledBookshelfBlock(CHISELED_PINE_BOOKSHELF);
        this.boardsBlock(PINE_BOARDS);
        this.chestBlocks(PINE_CHEST, TRAPPED_PINE_CHEST, PINE_PLANKS);
        this.hangingSignBlocks(STRIPPED_PINE_LOG, PINE_HANGING_SIGNS);

        // pinecone
        this.hangingPinecone(PINECONE);
        this.hangingPinecone(FAIRY_LIGHT);
        this.hangingPinecone(SOUL_FAIRY_LIGHT);
        this.hangingPinecone(CUPRIC_FAIRY_LIGHT);
        this.hangingPinecone(ENDER_FAIRY_LIGHT);
        this.offFairyLight(NIGHT_FAIRY_LIGHT);
        this.offFairyLight(REDSTONE_FAIRY_LIGHT);

        this.simpleBlock(PINECONE_JAM_BLOCK.get(), this.models().getExistingFile(this.modLoc("block/pinecone_jam_block")));
        this.blockItem(PINECONE_JAM_BLOCK);

        this.horizontalBlock(ELDER_WING.get(), this.models().getExistingFile(this.modLoc("block/elder_wing")));
        this.generatedItem(ELDER_WING, this.modLoc("block/elder_wing"));
        this.crossBlock(ELDER_ORNAMENT);
        this.generatedItem(ELDER_ORNAMENT, this.modLoc("block/elder_ornament"));
        this.dreamCatcher(DREAM_CATCHER);

        this.logBlocks(PINECONE_BLOCK);
        this.horizontalBlock(CARVED_PINECONE_BLOCK.get(), this.models().orientable("carved_pinecone_block", this.modLoc("block/pinecone_block"), this.modLoc("block/carved_pinecone_block"), this.modLoc("block/pinecone_block_top")));
        this.blockItem(CARVED_PINECONE_BLOCK);
        this.horizontalBlock(WILL_O_THE_WISP.get(), this.models().orientable("will_o_the_wisp", this.modLoc("block/pinecone_block"), this.modLoc("block/will_o_the_wisp"), this.modLoc("block/pinecone_block_top")));
        this.blockItem(WILL_O_THE_WISP);

        // pinecone shingles
        this.cubeAll(PINECONE_SHINGLES);
        this.stairsBlock(PINECONE_SHINGLE_STAIRS, PINECONE_SHINGLES);
        this.slabBlock(PINECONE_SHINGLE_SLAB, PINECONE_SHINGLES);

        this.cubeAll(WHITE_PINECONE_SHINGLES);
        this.stairsBlock(WHITE_PINECONE_SHINGLE_STAIRS, WHITE_PINECONE_SHINGLES);
        this.slabBlock(WHITE_PINECONE_SHINGLE_SLAB, WHITE_PINECONE_SHINGLES);
        this.cubeAll(LIGHT_GRAY_PINECONE_SHINGLES);
        this.stairsBlock(LIGHT_GRAY_PINECONE_SHINGLE_STAIRS, LIGHT_GRAY_PINECONE_SHINGLES);
        this.slabBlock(LIGHT_GRAY_PINECONE_SHINGLE_SLAB, LIGHT_GRAY_PINECONE_SHINGLES);
        this.cubeAll(GRAY_PINECONE_SHINGLES);
        this.stairsBlock(GRAY_PINECONE_SHINGLE_STAIRS, GRAY_PINECONE_SHINGLES);
        this.slabBlock(GRAY_PINECONE_SHINGLE_SLAB, GRAY_PINECONE_SHINGLES);
        this.cubeAll(BLACK_PINECONE_SHINGLES);
        this.stairsBlock(BLACK_PINECONE_SHINGLE_STAIRS, BLACK_PINECONE_SHINGLES);
        this.slabBlock(BLACK_PINECONE_SHINGLE_SLAB, BLACK_PINECONE_SHINGLES);
        this.cubeAll(BROWN_PINECONE_SHINGLES);
        this.stairsBlock(BROWN_PINECONE_SHINGLE_STAIRS, BROWN_PINECONE_SHINGLES);
        this.slabBlock(BROWN_PINECONE_SHINGLE_SLAB, BROWN_PINECONE_SHINGLES);
        this.cubeAll(RED_PINECONE_SHINGLES);
        this.stairsBlock(RED_PINECONE_SHINGLE_STAIRS, RED_PINECONE_SHINGLES);
        this.slabBlock(RED_PINECONE_SHINGLE_SLAB, RED_PINECONE_SHINGLES);
        this.cubeAll(ORANGE_PINECONE_SHINGLES);
        this.stairsBlock(ORANGE_PINECONE_SHINGLE_STAIRS, ORANGE_PINECONE_SHINGLES);
        this.slabBlock(ORANGE_PINECONE_SHINGLE_SLAB, ORANGE_PINECONE_SHINGLES);
        this.cubeAll(YELLOW_PINECONE_SHINGLES);
        this.stairsBlock(YELLOW_PINECONE_SHINGLE_STAIRS, YELLOW_PINECONE_SHINGLES);
        this.slabBlock(YELLOW_PINECONE_SHINGLE_SLAB, YELLOW_PINECONE_SHINGLES);
        this.cubeAll(LIME_PINECONE_SHINGLES);
        this.stairsBlock(LIME_PINECONE_SHINGLE_STAIRS, LIME_PINECONE_SHINGLES);
        this.slabBlock(LIME_PINECONE_SHINGLE_SLAB, LIME_PINECONE_SHINGLES);
        this.cubeAll(GREEN_PINECONE_SHINGLES);
        this.stairsBlock(GREEN_PINECONE_SHINGLE_STAIRS, GREEN_PINECONE_SHINGLES);
        this.slabBlock(GREEN_PINECONE_SHINGLE_SLAB, GREEN_PINECONE_SHINGLES);
        this.cubeAll(CYAN_PINECONE_SHINGLES);
        this.stairsBlock(CYAN_PINECONE_SHINGLE_STAIRS, CYAN_PINECONE_SHINGLES);
        this.slabBlock(CYAN_PINECONE_SHINGLE_SLAB, CYAN_PINECONE_SHINGLES);
        this.cubeAll(LIGHT_BLUE_PINECONE_SHINGLES);
        this.stairsBlock(LIGHT_BLUE_PINECONE_SHINGLE_STAIRS, LIGHT_BLUE_PINECONE_SHINGLES);
        this.slabBlock(LIGHT_BLUE_PINECONE_SHINGLE_SLAB, LIGHT_BLUE_PINECONE_SHINGLES);
        this.cubeAll(BLUE_PINECONE_SHINGLES);
        this.stairsBlock(BLUE_PINECONE_SHINGLE_STAIRS, BLUE_PINECONE_SHINGLES);
        this.slabBlock(BLUE_PINECONE_SHINGLE_SLAB, BLUE_PINECONE_SHINGLES);
        this.cubeAll(PURPLE_PINECONE_SHINGLES);
        this.stairsBlock(PURPLE_PINECONE_SHINGLE_STAIRS, PURPLE_PINECONE_SHINGLES);
        this.slabBlock(PURPLE_PINECONE_SHINGLE_SLAB, PURPLE_PINECONE_SHINGLES);
        this.cubeAll(MAGENTA_PINECONE_SHINGLES);
        this.stairsBlock(MAGENTA_PINECONE_SHINGLE_STAIRS, MAGENTA_PINECONE_SHINGLES);
        this.slabBlock(MAGENTA_PINECONE_SHINGLE_SLAB, MAGENTA_PINECONE_SHINGLES);
        this.cubeAll(PINK_PINECONE_SHINGLES);
        this.stairsBlock(PINK_PINECONE_SHINGLE_STAIRS, PINK_PINECONE_SHINGLES);
        this.slabBlock(PINK_PINECONE_SHINGLE_SLAB, PINK_PINECONE_SHINGLES);

        // snow
        this.stairsBlock(SNOW_STAIRS, this.mcLoc("block/snow"));
        this.slabBlock(SNOW_SLAB, this.mcLoc("block/snow"), this.mcLoc("block/snow"));
        this.cubeAll(SNOW_BRICKS);
        this.stairsBlock(SNOW_BRICK_STAIRS, SNOW_BRICKS);
        this.slabBlock(SNOW_BRICK_SLAB, SNOW_BRICKS);
        this.wallBlock(SNOW_BRICK_WALL, SNOW_BRICKS);
        this.brushableBlock(SUSPICIOUS_SNOW);

        // packed ice
        this.stairsBlock(PACKED_ICE_STAIRS, this.mcLoc("block/packed_ice"));
        this.slabBlock(PACKED_ICE_SLAB, this.mcLoc("block/packed_ice"));
        this.cubeAll(PACKED_ICE_BRICKS);
        this.cubeAll(CHISELED_PACKED_ICE_BRICKS);
        this.stairsBlock(PACKED_ICE_BRICK_STAIRS, PACKED_ICE_BRICKS);
        this.slabBlock(PACKED_ICE_BRICK_SLAB, PACKED_ICE_BRICKS);
        this.wallBlock(PACKED_ICE_BRICK_WALL, PACKED_ICE_BRICKS);

        // blue ice
        this.stairsBlock(BLUE_ICE_STAIRS, this.mcLoc("block/blue_ice"));
        this.slabBlock(BLUE_ICE_SLAB, this.mcLoc("block/blue_ice"));
        this.cubeAll(BLUE_ICE_BRICKS);
        this.cubeAll(CHISELED_BLUE_ICE_BRICKS);
        this.stairsBlock(BLUE_ICE_BRICK_STAIRS, BLUE_ICE_BRICKS);
        this.slabBlock(BLUE_ICE_BRICK_SLAB, BLUE_ICE_BRICKS);
        this.wallBlock(BLUE_ICE_BRICK_WALL, BLUE_ICE_BRICKS);

        // shale
        this.cubeAll(SHALE);
        this.stairsBlock(SHALE_STAIRS, SHALE);
        this.slabBlock(SHALE_SLAB, SHALE);
        this.wallBlock(SHALE_WALL, SHALE);
        this.cubeAll(POLISHED_SHALE);
        this.stairsBlock(POLISHED_SHALE_STAIRS, POLISHED_SHALE);
        this.slabBlock(POLISHED_SHALE_SLAB, POLISHED_SHALE);
        this.wallBlock(POLISHED_SHALE_WALL, POLISHED_SHALE);
        this.cubeAll(POLISHED_SHALE_BRICKS);
        this.cubeAll(ICY_POLISHED_SHALE_BRICKS);
        this.cubeAll(CHISELED_POLISHED_SHALE_BRICKS);
        this.stairsBlock(POLISHED_SHALE_BRICK_STAIRS, POLISHED_SHALE_BRICKS);
        this.slabBlock(POLISHED_SHALE_BRICK_SLAB, POLISHED_SHALE_BRICKS);
        this.wallBlock(POLISHED_SHALE_BRICK_WALL, POLISHED_SHALE_BRICKS);

        // decorations
        this.wreath(HOLLY_WREATH);
        this.wreath(PINECONE_WREATH);
        this.wreath(VINE_WREATH);
        this.wreath(CHERRY_WREATH);

        MultiPartBlockStateBuilder puddingBuilder = this.getMultipartBuilder(CHRISTMAS_PUDDING.get());
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_4"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FOUR).end();
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_3"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.THREE).end();
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_2"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.TWO).end();
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_1"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.ONE).end();
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_4"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FIRE).end();
        puddingBuilder.part().modelFile(this.models().getExistingFile(this.modLoc("block/christmas_pudding_fire"))).addModel().condition(ChristmasPuddingBlock.STATE, ChristmasPuddingBlock.PuddingStates.FIRE).end();

        this.generatedItem(CHRISTMAS_PUDDING);
        this.horizontalBlock(FROSTBITER_TROPHY.get(), this.models().getExistingFile(this.modLoc("block/frostbiter_trophy")));
        this.generatedItem(FROSTBITER_TROPHY, this.modLoc("block/frostbiter_trophy"));
        this.paneBlock((IronBarsBlock) ICE_SHEET.get(), this.mcLoc("block/ice"), this.mcLoc("block/ice"));

        // sprouts
        this.crossBlockWithPot(SNOWY_SPROUTS, POTTED_SNOWY_SPROUTS);
        this.crossBlockWithPot(GELISOL_GRASS, POTTED_GELISOL_GRASS);
        this.crossBlockWithPot(DRY_MOSSY_SPROUTS, POTTED_DRY_MOSSY_SPROUTS);
        this.crossBlockWithPot(MOSSY_SPROUTS, POTTED_MOSSY_SPROUTS);

        // flowers
        this.doublePlantBlock(RED_ROSE_BUSH);
        this.doublePlantBlock(BLUE_ROSE_BUSH);
        this.doublePlantBlock(WHITE_ROSE_BUSH);
        this.doublePlantBlock(YELLOW_ROSE_BUSH);
        this.doublePlantBlock(LUPINE);
        this.doublePlantBlock(LIONS_TAIL);

        this.crossBlockWithPot(RED_ROSE, POTTED_RED_ROSE);
        this.crossBlockWithPot(BLUE_ROSE, POTTED_BLUE_ROSE);
        this.crossBlockWithPot(WHITE_ROSE, POTTED_WHITE_ROSE);
        this.crossBlockWithPot(YELLOW_ROSE, POTTED_YELLOW_ROSE);
        this.crossBlockWithPot(FOXGLOVE, POTTED_FOXGLOVE);
        this.crossBlockWithPot(NIGHTSHADE, POTTED_NIGHTSHADE);
        this.crossBlockWithPot(SNOWDROP, POTTED_SNOWDROP);
        this.crossBlockWithPot(MOSS_CAMPION, POTTED_MOSS_CAMPION);
        this.crossBlockWithPot(WILD_GINGER, POTTED_WILD_GINGER);
        this.crossBlockWithPot(BLUEBELLS, POTTED_BLUEBELLS);
        this.crossBlockWithPot(MIMOSA, POTTED_MIMOSA);

        // lavender
        this.getVariantBuilder(LAVENDER.get())
                .partialState().with(LavenderBlock.AGE, 0).addModels(new ConfiguredModel(this.models().cross("lavender_stage0", this.modLoc("block/lavender_stage0")).renderType("cutout")))
                .partialState().with(LavenderBlock.AGE, 1).addModels(new ConfiguredModel(this.models().cross("lavender_stage1", this.modLoc("block/lavender_stage1")).renderType("cutout")))
                .partialState().with(LavenderBlock.AGE, 2).addModels(new ConfiguredModel(this.models().cross("lavender_stage2", this.modLoc("block/lavender_stage2")).renderType("cutout")));

        this.generatedItem(LAVENDER);
        this.potBlock(POTTED_LAVENDER, this.modLoc("block/potted_lavender"));

        this.compressedBlock(LAVENDER_BALE);
        this.thatchBlock(LAVENDER_THATCH, LAVENDER_THATCH_STAIRS, LAVENDER_THATCH_SLAB);

        // acacia
        this.crossBlockWithPot(FLOWERING_ACACIA_SAPLING, POTTED_FLOWERING_ACACIA_SAPLING);
        this.leavesBlocks(FLOWERING_ACACIA_LEAVES, FLOWERING_ACACIA_LEAF_PILE);
        this.petals(YELLOW_PETALS);

        // wild berry blocks
        this.wildBerryBush(WILD_BERRY_BUSH);
        this.compressedBlock(WILD_BERRY_BASKET);

        // candy cane
        this.logBlocks(CANDY_CANE_BLOCK);

        // icicles
        this.getVariantBuilder(ICICLES.get())
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.NORMAL).addModels(new ConfiguredModel(this.models().cross("icicles", this.blockTexture(ICICLES.get())).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.TOP).addModels(new ConfiguredModel(this.models().cross("icicles_top", this.modLoc("block/icicles_top")).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.BOTTOM).addModels(new ConfiguredModel(this.models().cross("icicles_bottom", this.modLoc("block/icicles_bottom")).renderType("cutout")))
                .partialState().with(IciclesBlock.STATE, IciclesBlock.IcicleStates.FLOOR).addModels(new ConfiguredModel(this.models().cross("icicles_floor", this.modLoc("block/icicles_floor")).renderType("cutout")));
        this.generatedItem(ICICLES);
        this.logBlocks(ICICLE_BLOCK);
        this.logBlocks(CHISELED_ICICLE_BLOCK);
        this.doorBlock(ICICLE_DOOR);
        this.trapdoorBlock(ICICLE_TRAPDOOR);
        this.paneBlock((IronBarsBlock) ICICLE_BARS.get(), this.mcLoc("block/packed_ice"), this.mcLoc("block/packed_ice"));
        this.getVariantBuilder(ICE_LANTERN.get())
                .partialState().with(IceLanternBlock.FACING, Direction.UP).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern"))))
                .partialState().with(IceLanternBlock.FACING, Direction.DOWN).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern_hanging"))))
                .partialState().with(IceLanternBlock.FACING, Direction.NORTH).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))))
                .partialState().with(IceLanternBlock.FACING, Direction.SOUTH).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(180).build())
                .partialState().with(IceLanternBlock.FACING, Direction.EAST).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(90).build())
                .partialState().with(IceLanternBlock.FACING, Direction.WEST).addModels(ConfiguredModel.builder().modelFile(this.models().getExistingFile(this.modLoc("block/ice_lantern_side"))).rotationY(270).build());
        this.generatedItem(ICE_LANTERN);
        this.simpleBlock(ICE_CHAIN.get(), this.models().getExistingFile(this.mcLoc("block/chain")));

        // lunalite
        this.getVariantBuilder(LUNALITE.get())
                .partialState().with(LunaliteBlock.TOP, true).addModels(new ConfiguredModel(this.models().cubeBottomTop("lunalite", this.modLoc("block/lunalite"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(LunaliteBlock.TOP, false).addModels(new ConfiguredModel(this.models().cubeAll("lunalite_bottom", this.modLoc("block/lunalite_bottom"))));
        this.simpleBlock(CUT_LUNALITE.get(), this.models().cubeBottomTop("cut_lunalite", this.modLoc("block/cut_lunalite"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.simpleBlock(CUT_LUNALITE_BRICKS.get(), this.models().cubeBottomTop("cut_lunalite_bricks", this.modLoc("block/cut_lunalite_bricks"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.simpleBlock(CHISELED_CUT_LUNALITE_BRICKS.get(), this.models().cubeBottomTop("chiseled_cut_lunalite_bricks", this.modLoc("block/chiseled_cut_lunalite_bricks"), this.modLoc("block/smooth_lunalite"), this.modLoc("block/lunalite_top")));
        this.blockItem(LUNALITE);
        this.blockItem(CUT_LUNALITE);
        this.blockItem(CUT_LUNALITE_BRICKS);
        this.blockItem(CHISELED_CUT_LUNALITE_BRICKS);

        this.getVariantBuilder(LUNALITE_SLAB.get())
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().slab("lunalite_slab", this.modLoc("block/lunalite_slab"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().slabTop("lunalite_slab_top", this.modLoc("block/lunalite_slab"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_top"))))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).with(LunaliteSlabBlock.TOP, true).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/lunalite"))))
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().slab("lunalite_slab_bottom", this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"))))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().slabTop("lunalite_slab_top_bottom", this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"), this.modLoc("block/lunalite_bottom"))))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).with(LunaliteSlabBlock.TOP, false).addModels(new ConfiguredModel(this.models().getExistingFile(this.modLoc("block/lunalite_bottom"))));

        this.slabBlock(CUT_LUNALITE_SLAB, CUT_LUNALITE);
        this.slabBlock(CUT_LUNALITE_BRICK_SLAB, CUT_LUNALITE_BRICKS);
        this.blockItem(LUNALITE_SLAB);

        this.stairsBlock(LUNALITE_STAIRS, LUNALITE);
        this.stairsBlock(CUT_LUNALITE_STAIRS, CUT_LUNALITE);
        this.stairsBlock(CUT_LUNALITE_BRICK_STAIRS, CUT_LUNALITE_BRICKS);

        this.wallBlock(LUNALITE_WALL, LUNALITE);
        this.wallBlock(CUT_LUNALITE_WALL, CUT_LUNALITE);
        this.wallBlock(CUT_LUNALITE_BRICK_WALL, CUT_LUNALITE_BRICKS);

        this.cubeAll(SMOOTH_LUNALITE);
        this.stairsBlock(SMOOTH_LUNALITE_STAIRS, SMOOTH_LUNALITE);
        this.slabBlock(SMOOTH_LUNALITE_SLAB, SMOOTH_LUNALITE);

        // ginger
        this.doorBlock(GINGERBREAD_DOOR);
        this.trapdoorBlock(GINGERBREAD_TRAPDOOR);
        this.getVariantBuilder(GINGER.get())
                .partialState().with(GingerCropBlock.AGE, 0).addModels(new ConfiguredModel(this.models().crop("ginger_stage0", this.modLoc("block/ginger_stage0")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 1).addModels(new ConfiguredModel(this.models().crop("ginger_stage1", this.modLoc("block/ginger_stage1")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 2).addModels(new ConfiguredModel(this.models().crop("ginger_stage2", this.modLoc("block/ginger_stage2")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 3).addModels(new ConfiguredModel(this.models().crop("ginger_stage3", this.modLoc("block/ginger_stage3")).renderType("cutout")))
                .partialState().with(GingerCropBlock.AGE, 4).addModels(new ConfiguredModel(this.models().crop("ginger_stage4", this.modLoc("block/ginger_stage4")).renderType("cutout")));
        this.cubeAll(GINGER_SOIL);
        this.cubeAll(GINGERBREAD_BLOCK);
        this.cubeAll(GINGERBREAD_BRICKS);
        this.stairsBlock(GINGERBREAD_BRICK_STAIRS, GINGERBREAD_BRICKS);
        this.slabBlock(GINGERBREAD_BRICK_SLAB, GINGERBREAD_BRICKS);
        this.wallBlock(GINGERBREAD_BRICK_WALL, GINGERBREAD_BRICKS);

        this.cubeTopBlock(GLAZED_GINGERBREAD_BLOCK, this.modLoc("block/glazed_gingerbread_top"));
        this.cubeAll(GINGERBREAD_COOKIE_BLOCK);
        this.cubeTopBlock(GLAZED_GINGERBREAD_BRICKS, this.modLoc("block/glazed_gingerbread_top"));
        this.stairsBlock(GLAZED_GINGERBREAD_BRICK_STAIRS, GLAZED_GINGERBREAD_BRICKS);
        this.slabBlock(GLAZED_GINGERBREAD_BRICK_SLAB, GLAZED_GINGERBREAD_BRICKS);
        this.wallBlock(GLAZED_GINGERBREAD_BRICK_WALL, GLAZED_GINGERBREAD_BRICKS);

        this.compressedBlock(GINGER_ROOT_CRATE);

        // dry moss
        this.carpetBlock(DRY_MOSS_CARPET, DRY_MOSS_BLOCK);
        this.cubeAll(DRY_MOSS_BLOCK);
        this.cubeAll(DRY_MOSSY_COBBLESTONE);
        this.stairsBlock(DRY_MOSSY_COBBLESTONE_STAIRS, DRY_MOSSY_COBBLESTONE);
        this.slabBlock(DRY_MOSSY_COBBLESTONE_SLAB, DRY_MOSSY_COBBLESTONE);
        this.wallBlock(DRY_MOSSY_COBBLESTONE_WALL, DRY_MOSSY_COBBLESTONE);
        this.cubeAll(DRY_MOSSY_STONE_BRICKS);
        this.stairsBlock(DRY_MOSSY_STONE_BRICK_STAIRS, DRY_MOSSY_STONE_BRICKS);
        this.slabBlock(DRY_MOSSY_STONE_BRICK_SLAB, DRY_MOSSY_STONE_BRICKS);
        this.wallBlock(DRY_MOSSY_STONE_BRICK_WALL, DRY_MOSSY_STONE_BRICKS);
        this.cubeAll(DRY_MOSSY_COBBLESTONE_BRICKS);
        this.stairsBlock(DRY_MOSSY_COBBLESTONE_BRICK_STAIRS, DRY_MOSSY_COBBLESTONE_BRICKS);
        this.slabBlock(DRY_MOSSY_COBBLESTONE_BRICK_SLAB, DRY_MOSSY_COBBLESTONE_BRICKS);
        this.wallBlock(DRY_MOSSY_COBBLESTONE_BRICK_WALL, DRY_MOSSY_COBBLESTONE_BRICKS);
        this.cubeAll(DRY_MOSSY_COBBLESTONE_TILES);
        this.stairsBlock(DRY_MOSSY_COBBLESTONE_TILE_STAIRS, DRY_MOSSY_COBBLESTONE_TILES);
        this.slabBlock(DRY_MOSSY_COBBLESTONE_TILE_SLAB, DRY_MOSSY_COBBLESTONE_TILES);
        this.wallBlock(DRY_MOSSY_COBBLESTONE_TILE_WALL, DRY_MOSSY_COBBLESTONE_TILES);

        // compressed blocks
        this.cubeAll(FROZEN_FLESH_BLOCK);
        this.compressedBlock(CHESTNUT_CRATE);
        this.compressedBlock(ROASTED_CHESTNUT_CRATE);

        // gelisol
        this.getVariantBuilder(GELISOL.get())
                .partialState().with(SnowyDirtBlock.SNOWY, false).addModels(new ConfiguredModel(this.models().cubeBottomTop("gelisol", this.modLoc("block/gelisol_side"), this.mcLoc("block/dirt"), this.modLoc("block/gelisol_top"))))
                .partialState().with(SnowyDirtBlock.SNOWY, true).addModels(new ConfiguredModel(this.models().cubeBottomTop("gelisol_snowy", this.modLoc("block/gelisol_side_snowy"), this.mcLoc("block/dirt"), this.mcLoc("block/snow"))));
        this.blockItem(GELISOL);
        this.simpleBlock(GELISOL_PATH.get(), this.models().withExistingParent("gelisol_path", this.mcLoc("dirt_path")).texture("top", this.modLoc("block/gelisol_path_top")).texture("side", this.modLoc("block/gelisol_path_side")));
        this.blockItem(GELISOL_PATH);
    }

    private void hangingPinecone(DeferredBlock<Block> pinecone) {
        String name = pinecone.getId().getPath();
        ResourceLocation texture = this.blockTexture(pinecone.get());
        ModelFile model1 = this.models().withExistingParent(name + "_1", this.modLoc("block/hanging_pinecone_template_1")).texture("texture", texture);
        ModelFile model2 = this.models().withExistingParent(name + "_2", this.modLoc("block/hanging_pinecone_template_2")).texture("texture", texture);
        ModelFile model3 = this.models().withExistingParent(name + "_3", this.modLoc("block/hanging_pinecone_template_3")).texture("texture", texture);
        ModelFile model4 = this.models().withExistingParent(name + "_4", this.modLoc("block/hanging_pinecone_template_4")).texture("texture", texture);

        this.generatedItem(pinecone, this.modLoc("item/" + name));
        this.getVariantBuilder(pinecone.get())
                .partialState().with(PineconeBlock.AMOUNT, 1).addModels(new ConfiguredModel(model1), new ConfiguredModel(model1, 0, 90, false), new ConfiguredModel(model1, 0, 180, false), new ConfiguredModel(model1, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 2).addModels(new ConfiguredModel(model2), new ConfiguredModel(model2, 0, 90, false), new ConfiguredModel(model2, 0, 180, false), new ConfiguredModel(model2, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 3).addModels(new ConfiguredModel(model3), new ConfiguredModel(model3, 0, 90, false), new ConfiguredModel(model3, 0, 180, false), new ConfiguredModel(model3, 0, 270, false))
                .partialState().with(PineconeBlock.AMOUNT, 4).addModels(new ConfiguredModel(model4), new ConfiguredModel(model4, 0, 90, false), new ConfiguredModel(model4, 0, 180, false), new ConfiguredModel(model4, 0, 270, false));
    }

    private void offFairyLight(DeferredBlock<Block> pinecone) {
        String name = pinecone.getId().getPath();
        ResourceLocation texture = this.blockTexture(pinecone.get());
        ResourceLocation textureOff = texture.withPath(s -> s + "_off");
        ModelFile model1 = this.models().withExistingParent(name + "_1", this.modLoc("block/hanging_pinecone_template_1")).texture("texture", texture);
        ModelFile modelOff1 = this.models().withExistingParent(name + "_off_1", this.modLoc("block/hanging_pinecone_template_1")).texture("texture", textureOff);

        this.generatedItem(pinecone, this.modLoc("item/" + name));
        this.getVariantBuilder(pinecone.get())
                .partialState().with(PineconeBlock.AMOUNT, 1).with(BlockStateProperties.LIT, true).addModels(new ConfiguredModel(model1))
                .partialState().with(PineconeBlock.AMOUNT, 1).with(BlockStateProperties.LIT, false).addModels(new ConfiguredModel(modelOff1));
    }

    private void wildBerryBush(DeferredBlock<Block> bush) {
        String name = bush.getId().getPath();
        this.getVariantBuilder(bush.get())
                .partialState().with(WildBerryBushBlock.AGE, 0).addModels(new ConfiguredModel(this.models().cross(name + "_stage0", this.modLoc("block/" + name + "_stage0")).renderType("cutout")))
                .partialState().with(WildBerryBushBlock.AGE, 1).addModels(new ConfiguredModel(this.models().cross(name + "_stage1", this.modLoc("block/" + name + "_stage1")).renderType("cutout")))
                .partialState().with(WildBerryBushBlock.AGE, 2).addModels(new ConfiguredModel(this.models().cross(name + "_stage2", this.modLoc("block/" + name + "_stage2")).renderType("cutout")))
                .partialState().with(WildBerryBushBlock.AGE, 3).addModels(new ConfiguredModel(this.models().cross(name + "_stage3", this.modLoc("block/" + name + "_stage3")).renderType("cutout")));
    }

    private void dreamCatcher(DeferredBlock<Block> block) {
        String name = block.getId().getPath();
        this.getVariantBuilder(block.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(this.models().cross(name + "_top", this.modLoc("block/" + name + "_top")).renderType("cutout")))
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(this.models().cross(name + "_bottom", this.modLoc("block/" + name + "_bottom")).renderType("cutout")));
    }

    private void wreath(DeferredBlock<Block> wreath) {
        ResourceLocation texture = this.blockTexture(wreath.get());
        this.horizontalBlock(wreath.get(), this.models().withExistingParent(wreath.getId().getPath(), "block/ladder").texture("texture", texture).renderType("cutout"));
        this.itemModels().withExistingParent(wreath.getId().getPath(), this.modLoc("item/wreath")).texture("layer0", texture);
    }

    private void petals(DeferredBlock<Block> petals) {
        this.generatedItem(petals);
    }

    private void painting(DeferredHolder<PaintingVariant, PaintingVariant> painting) {
        String name = painting.getId().getPath();
        this.itemModels().withExistingParent("item/painting/" + name, "item/generated").texture("layer0", this.modLoc("item/painting/" + name));
    }
}