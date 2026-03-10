package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.common.block.grower.ChestnutTreeGrower;
import com.rosemods.windswept.common.block.grower.FloweringAcaciaTreeGrower;
import com.rosemods.windswept.common.block.grower.HollyTreeGrower;
import com.rosemods.windswept.common.block.grower.PineTreeGrower;
import com.rosemods.windswept.common.item.WearableBlockItem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptSoundTypes;
import com.teamabnormals.blueprint.common.block.*;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WindsweptBlocks {
    public static final BlockSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getBlockSubHelper();

    // Holly //
    public static final DeferredBlock<Block> HOLLY_LOG = HELPER.createBlock("holly_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_HOLLY_LOG, Properties.HOLLY.log()));
    public static final DeferredBlock<Block> HOLLY_WOOD = HELPER.createBlock("holly_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_HOLLY_WOOD, Properties.HOLLY.log()));
    public static final DeferredBlock<Block> STRIPPED_HOLLY_LOG = HELPER.createBlock("stripped_holly_log", () -> new RotatedPillarBlock(Properties.HOLLY.log()));
    public static final DeferredBlock<Block> STRIPPED_HOLLY_WOOD = HELPER.createBlock("stripped_holly_wood", () -> new RotatedPillarBlock(Properties.HOLLY.log()));
    public static final DeferredBlock<Block> HOLLY_PLANKS = HELPER.createBlock("holly_planks", () -> new Block(Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_STAIRS = HELPER.createBlock("holly_stairs", () -> new StairBlock(HOLLY_PLANKS.get().defaultBlockState(), Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_SLAB = HELPER.createBlock("holly_slab", () -> new SlabBlock(Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_FENCE = HELPER.createFuelBlock("holly_fence", () -> new FenceBlock(Properties.HOLLY.planks()), 300);
    public static final DeferredBlock<Block> HOLLY_FENCE_GATE = HELPER.createFuelBlock("holly_fence_gate", () -> new FenceGateBlock(Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.planks()), 300);
    public static final DeferredBlock<Block> HOLLY_PRESSURE_PLATE = HELPER.createBlock("holly_pressure_plate", () -> new PressurePlateBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.pressurePlate().sound(SoundType.CHERRY_WOOD)));
    public static final DeferredBlock<Block> HOLLY_BUTTON = HELPER.createBlock("holly_button", () -> new ButtonBlock(Properties.HOLLY_BLOCK_SET, 30, Properties.HOLLY.button()));
    public static final DeferredBlock<Block> HOLLY_DOOR = HELPER.createBlock("holly_door", () -> new DoorBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.door()));
    public static final DeferredBlock<Block> HOLLY_TRAPDOOR = HELPER.createBlock("holly_trapdoor", () -> new TrapDoorBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> HOLLY_SIGNS = HELPER.createSignBlock("holly", Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.sign().sound(SoundType.CHERRY_WOOD));
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> HOLLY_HANGING_SIGNS = HELPER.createHangingSignBlock("holly", Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.hangingSign().sound(SoundType.CHERRY_WOOD_HANGING_SIGN));

    public static final DeferredBlock<Block> HOLLY_BEEHIVE = HELPER.createBlock("holly_beehive", () -> new BlueprintBeehiveBlock(Properties.HOLLY.beehive()));
    public static final DeferredBlock<Block> HOLLY_LADDER = HELPER.createFuelBlock("holly_ladder", () -> new LadderBlock(Properties.HOLLY.ladder()), 300);
    public static final DeferredBlock<Block> HOLLY_BOOKSHELF = HELPER.createFuelBlock("holly_bookshelf", () -> new Block(Properties.HOLLY.bookshelf()), 300);
    public static final DeferredBlock<Block> CHISELED_HOLLY_BOOKSHELF = HELPER.createFuelBlock("chiseled_holly_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.HOLLY.chiseledBookshelf()), 300);
    public static final DeferredBlock<Block> HOLLY_BOARDS = HELPER.createBlock("holly_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()));
    public static final DeferredBlock<BlueprintChestBlock> HOLLY_CHEST = HELPER.createChestBlock("holly", Properties.HOLLY.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_HOLLY_CHEST = HELPER.createTrappedChestBlock("holly", Properties.HOLLY.chest());

    public static final DeferredBlock<Block> HOLLY_SAPLING = HELPER.createBlock("holly_sapling", () -> new HollySaplingBlock(new HollyTreeGrower(), PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_HOLLY_SAPLING = HELPER.createBlockNoItem("potted_holly_sapling", () -> new FlowerPotBlock(HOLLY_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> HOLLY_LEAVES = HELPER.createBlock("holly_leaves", () -> new HollyLeavesBlock(Properties.HOLLY.leaves()));
    public static final DeferredBlock<Block> HOLLY_LEAF_PILE = HELPER.createBlock("holly_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()));

    // Chestnut //
    public static final DeferredBlock<Block> CHESTNUT_LOG = HELPER.createBlock("chestnut_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_CHESTNUT_LOG, Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> CHESTNUT_WOOD = HELPER.createBlock("chestnut_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_CHESTNUT_WOOD, Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_LOG = HELPER.createBlock("stripped_chestnut_log", () -> new RotatedPillarBlock(Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_WOOD = HELPER.createBlock("stripped_chestnut_wood", () -> new RotatedPillarBlock(Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> CHESTNUT_PLANKS = HELPER.createBlock("chestnut_planks", () -> new Block(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_STAIRS = HELPER.createBlock("chestnut_stairs", () -> new StairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_SLAB = HELPER.createBlock("chestnut_slab", () -> new SlabBlock(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_FENCE = HELPER.createFuelBlock("chestnut_fence", () -> new FenceBlock(Properties.CHESTNUT.planks()), 300);
    public static final DeferredBlock<Block> CHESTNUT_FENCE_GATE = HELPER.createFuelBlock("chestnut_fence_gate", () -> new FenceGateBlock(Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.planks()), 300);
    public static final DeferredBlock<Block> CHESTNUT_PRESSURE_PLATE = HELPER.createBlock("chestnut_pressure_plate", () -> new PressurePlateBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.pressurePlate()));
    public static final DeferredBlock<Block> CHESTNUT_BUTTON = HELPER.createBlock("chestnut_button", () -> new ButtonBlock(Properties.CHESTNUT_BLOCK_SET, 30, Properties.CHESTNUT.button()));
    public static final DeferredBlock<Block> CHESTNUT_DOOR = HELPER.createBlock("chestnut_door", () -> new DoorBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.door()));
    public static final DeferredBlock<Block> CHESTNUT_TRAPDOOR = HELPER.createBlock("chestnut_trapdoor", () -> new TrapDoorBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> CHESTNUT_SIGNS = HELPER.createSignBlock("chestnut", Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.sign());
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> CHESTNUT_HANGING_SIGNS = HELPER.createHangingSignBlock("chestnut", Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.hangingSign());

    public static final DeferredBlock<Block> CHESTNUT_BEEHIVE = HELPER.createBlock("chestnut_beehive", () -> new BlueprintBeehiveBlock(Properties.CHESTNUT.beehive()));
    public static final DeferredBlock<Block> CHESTNUT_LADDER = HELPER.createFuelBlock("chestnut_ladder", () -> new LadderBlock(Properties.CHESTNUT.ladder()), 300);
    public static final DeferredBlock<Block> CHESTNUT_BOOKSHELF = HELPER.createFuelBlock("chestnut_bookshelf", () -> new Block(Properties.CHESTNUT.bookshelf()), 300);
    public static final DeferredBlock<Block> CHISELED_CHESTNUT_BOOKSHELF = HELPER.createFuelBlock("chiseled_chestnut_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.CHESTNUT.chiseledBookshelf()), 300);
    public static final DeferredBlock<Block> CHESTNUT_BOARDS = HELPER.createBlock("chestnut_boards", () -> new RotatedPillarBlock(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<BlueprintChestBlock> CHESTNUT_CHEST = HELPER.createChestBlock("chestnut", Properties.CHESTNUT.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_CHESTNUT_CHEST = HELPER.createTrappedChestBlock("chestnut", Properties.CHESTNUT.chest());

    public static final DeferredBlock<Block> CHESTNUT_SAPLING = HELPER.createBlock("chestnut_sapling", () -> new SaplingBlock(new ChestnutTreeGrower(), PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_CHESTNUT_SAPLING = HELPER.createBlockNoItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> CHESTNUT_LEAVES = HELPER.createBlock("chestnut_leaves", () -> new LeavesBlock(Properties.CHESTNUT.leaves()));
    public static final DeferredBlock<Block> CHESTNUT_LEAF_PILE = HELPER.createBlock("chestnut_leaf_pile", () -> new LeafPileBlock(Properties.CHESTNUT.leafPile()));

    // Pine //
    public static final DeferredBlock<Block> PINE_LOG = HELPER.createBlock("pine_log", () -> new LogBlock(WindsweptBlocks.WEATHERED_PINE_LOG, Properties.PINE.log()));
    public static final DeferredBlock<Block> PINE_WOOD = HELPER.createBlock("pine_wood", () -> new WoodBlock(WindsweptBlocks.WEATHERED_PINE_WOOD, Properties.PINE.log()));
    public static final DeferredBlock<Block> WEATHERED_PINE_LOG = HELPER.createBlock("weathered_pine_log", () -> new LogBlock(WindsweptBlocks.STRIPPED_PINE_LOG, Properties.PINE.log()));
    public static final DeferredBlock<Block> WEATHERED_PINE_WOOD = HELPER.createBlock("weathered_pine_wood", () -> new WoodBlock(WindsweptBlocks.STRIPPED_PINE_WOOD, Properties.PINE.log()));
    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = HELPER.createBlock("stripped_pine_log", () -> new RotatedPillarBlock(Properties.PINE.log()));
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = HELPER.createBlock("stripped_pine_wood", () -> new RotatedPillarBlock(Properties.PINE.log()));
    public static final DeferredBlock<Block> PINE_PLANKS = HELPER.createBlock("pine_planks", () -> new Block(Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_STAIRS = HELPER.createBlock("pine_stairs", () -> new StairBlock(PINE_PLANKS.get().defaultBlockState(), Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_SLAB = HELPER.createBlock("pine_slab", () -> new SlabBlock(Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_FENCE = HELPER.createFuelBlock("pine_fence", () -> new FenceBlock(Properties.PINE.planks()), 300);
    public static final DeferredBlock<Block> PINE_FENCE_GATE = HELPER.createFuelBlock("pine_fence_gate", () -> new FenceGateBlock(Properties.PINE_WOOD_TYPE, Properties.PINE.planks()), 300);
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = HELPER.createBlock("pine_pressure_plate", () -> new PressurePlateBlock(Properties.PINE_BLOCK_SET, Properties.PINE.pressurePlate()));
    public static final DeferredBlock<Block> PINE_BUTTON = HELPER.createBlock("pine_button", () -> new ButtonBlock(Properties.PINE_BLOCK_SET, 30, Properties.PINE.button()));
    public static final DeferredBlock<Block> PINE_DOOR = HELPER.createBlock("pine_door", () -> new DoorBlock(Properties.PINE_BLOCK_SET, Properties.PINE.door()));
    public static final DeferredBlock<Block> PINE_TRAPDOOR = HELPER.createBlock("pine_trapdoor", () -> new TrapDoorBlock(Properties.PINE_BLOCK_SET, Properties.PINE.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> PINE_SIGNS = HELPER.createSignBlock("pine", Properties.PINE_WOOD_TYPE, Properties.PINE.sign());
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> PINE_HANGING_SIGNS = HELPER.createHangingSignBlock("pine", Properties.PINE_WOOD_TYPE, Properties.PINE.hangingSign());

    public static final DeferredBlock<Block> PINE_BEEHIVE = HELPER.createBlock("pine_beehive", () -> new BlueprintBeehiveBlock(Properties.PINE.beehive()));
    public static final DeferredBlock<Block> PINE_LADDER = HELPER.createFuelBlock("pine_ladder", () -> new LadderBlock(Properties.PINE.ladder()), 300);
    public static final DeferredBlock<Block> PINE_BOOKSHELF = HELPER.createFuelBlock("pine_bookshelf", () -> new Block(Properties.PINE.bookshelf()), 300);
    public static final DeferredBlock<Block> CHISELED_PINE_BOOKSHELF = HELPER.createFuelBlock("chiseled_pine_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.PINE.chiseledBookshelf()), 300);
    public static final DeferredBlock<Block> PINE_BOARDS = HELPER.createBlock("pine_boards", () -> new RotatedPillarBlock(Properties.PINE.planks()));
    public static final DeferredBlock<BlueprintChestBlock> PINE_CHEST = HELPER.createChestBlock("pine", Properties.PINE.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_PINE_CHEST = HELPER.createTrappedChestBlock("pine", Properties.PINE.chest());

    public static final DeferredBlock<Block> PINE_SAPLING = HELPER.createBlock("pine_sapling", () -> new SaplingBlock(new PineTreeGrower(), PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_PINE_SAPLING = HELPER.createBlockNoItem("potted_pine_sapling", () -> new FlowerPotBlock(PINE_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> PINE_LEAVES = HELPER.createBlock("pine_leaves", () -> new LeavesBlock(Properties.PINE.leaves()));
    public static final DeferredBlock<Block> PINE_LEAF_PILE = HELPER.createBlock("pine_leaf_pile", () -> new LeafPileBlock(Properties.PINE.leafPile()));

    // Moss //
    public static final DeferredBlock<Block> MOSSY_SPROUTS = HELPER.createBlock("mossy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> DRY_MOSSY_SPROUTS = HELPER.createBlock("dry_mossy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> DRY_MOSS_CARPET = HELPER.createBlock("dry_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)));
    public static final DeferredBlock<Block> DRY_MOSS_BLOCK = HELPER.createBlock("dry_moss_block", () -> new DryMossBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)));

    // Gelisol Blocks //
    public static final DeferredBlock<Block> GELISOL_GRASS = HELPER.createBlock("gelisol_grass", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> GELISOL = HELPER.createBlock("gelisol", () -> new GelisolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
    public static final DeferredBlock<Block> GELISOL_PATH = HELPER.createBlock("gelisol_path", () -> new DirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));

    // Plants //
    public static final DeferredBlock<Block> RED_ROSE = HELPER.createBlock("red_rose", () -> new RoseFlowerBlock(WindsweptBlocks.RED_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUE_ROSE = HELPER.createBlock("blue_rose", () -> new RoseFlowerBlock(WindsweptBlocks.BLUE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> WHITE_ROSE = HELPER.createBlock("white_rose", () -> new RoseFlowerBlock(WindsweptBlocks.WHITE_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> YELLOW_ROSE = HELPER.createBlock("yellow_rose", () -> new RoseFlowerBlock(WindsweptBlocks.YELLOW_ROSE_BUSH, () -> MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> FOXGLOVE = HELPER.createBlock("foxglove", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUEBELLS = HELPER.createBlock("bluebells", () -> new FlowerBlock(MobEffects.SLOW_FALLING, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> SNOWY_SPROUTS = HELPER.createBlock("snowy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> SNOWDROP = HELPER.createBlock("snowdrop", () -> new SnowdropBlock(WindsweptEffects.FROST_RESISTANCE, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)));
    public static final DeferredBlock<Block> MOSS_CAMPION = HELPER.createBlock("moss_campion", () -> new MossCampionBlock(WindsweptEffects.THORNS, 5, PropertyUtil.flower().sound(SoundType.AZALEA)));
    public static final DeferredBlock<Block> WILD_GINGER = HELPER.createBlock("wild_ginger", () -> new FlowerBlock(MobEffects.DAMAGE_BOOST, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> NIGHTSHADE = HELPER.createBlock("nightshade", () -> new NightshadeFlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).lightLevel(state -> 9)));
    public static final DeferredBlock<Block> MIMOSA = HELPER.createBlock("mimosa", () -> new MimosaBlock(MobEffects.FIRE_RESISTANCE, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)));

    // Tall Flowers //
    public static final DeferredBlock<Block> LUPINE = HELPER.createBlock("lupine", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> LIONS_TAIL = HELPER.createBlock("lions_tail", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> RED_ROSE_BUSH = HELPER.createBlock("red_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUE_ROSE_BUSH = HELPER.createBlock("blue_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> WHITE_ROSE_BUSH = HELPER.createBlock("white_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> YELLOW_ROSE_BUSH = HELPER.createBlock("yellow_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));

    // Lavender //
    public static final DeferredBlock<Block> LAVENDER = HELPER.createBlock("lavender", () -> new LavenderBlock(PropertyUtil.flower().sound(SoundType.AZALEA).randomTicks()));
    public static final DeferredBlock<Block> LAVENDER_BALE = HELPER.createBlock("lavender_bale", () -> new LavenderBaleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));
    public static final DeferredBlock<Block> LAVENDER_THATCH = HELPER.createBlock("lavender_thatch", () -> new ThatchBlock(Properties.LAVENDER_THATCH));
    public static final DeferredBlock<Block> LAVENDER_THATCH_STAIRS = HELPER.createBlock("lavender_thatch_stairs", () -> new ThatchStairBlock(LAVENDER_THATCH.get().defaultBlockState(), Properties.LAVENDER_THATCH));
    public static final DeferredBlock<Block> LAVENDER_THATCH_SLAB = HELPER.createBlock("lavender_thatch_slab", () -> new ThatchSlabBlock(Properties.LAVENDER_THATCH));

    // Acacia //
    public static final DeferredBlock<Block> FLOWERING_ACACIA_SAPLING = HELPER.createBlock("flowering_acacia_sapling", () -> new SaplingBlock(new FloweringAcaciaTreeGrower(), PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_FLOWERING_ACACIA_SAPLING = HELPER.createBlockNoItem("potted_flowering_acacia_sapling", () -> new FlowerPotBlock(FLOWERING_ACACIA_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> FLOWERING_ACACIA_LEAVES = HELPER.createBlock("flowering_acacia_leaves", () -> new FloweringAcaciaLeavesBlock(Properties.HOLLY.leaves()));
    public static final DeferredBlock<Block> FLOWERING_ACACIA_LEAF_PILE = HELPER.createBlock("flowering_acacia_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()));
    public static final DeferredBlock<Block> YELLOW_PETALS = HELPER.createBlock("yellow_petals", () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));

    // Decoration Blocks //
    public static final DeferredBlock<Block> HOLLY_WREATH = HELPER.createBlockWithItem("holly_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.HOLLY_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> PINECONE_WREATH = HELPER.createBlockWithItem("pinecone_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.PINECONE_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> VINE_WREATH = HELPER.createBlockWithItem("vine_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.VINE_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> CHERRY_WREATH = HELPER.createBlockWithItem("cherry_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.CHERRY_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> CHRISTMAS_PUDDING = HELPER.createBlock("christmas_pudding", () -> new ChristmasPuddingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion()), new Item.Properties().stacksTo(1));
    public static final DeferredBlock<Block> FROSTBITER_TROPHY = HELPER.createBlock("frostbiter_trophy", () -> new WallDecorationBlock(Properties.HOLLY.ladder().sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> ICE_SHEET = HELPER.createBlock("ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.of().strength(.3f).sound(SoundType.GLASS).noOcclusion().friction(.98f)));

    // Pinecone Blocks //
    public static final DeferredBlock<Block> PINECONE = HELPER.createBlock("pinecone", () -> new PineconeBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.25f).dynamicShape().noOcclusion().sound(WindsweptSoundTypes.PINECONE)));
    public static final DeferredBlock<Block> PINECONE_JAM_BLOCK = HELPER.createBlock("pinecone_jam_block", () -> new PineconeJamBlock(Block.Properties.of().mapColor(MapColor.COLOR_RED).noOcclusion().sound(SoundType.HONEY_BLOCK)));

    public static final DeferredBlock<Block> FAIRY_LIGHT = HELPER.createBlock("fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 14)));
    public static final DeferredBlock<Block> SOUL_FAIRY_LIGHT = HELPER.createBlock("soul_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> CUPRIC_FAIRY_LIGHT = HELPER.createBlock("cupric_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> ENDER_FAIRY_LIGHT = HELPER.createBlock("ender_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 14)));
    public static final DeferredBlock<Block> NIGHT_FAIRY_LIGHT = HELPER.createBlock("night_fairy_light", () -> new NightFairyLightBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> s.getValue(NightFairyLightBlock.LIT) ? 9 : 0)));
    public static final DeferredBlock<Block> REDSTONE_FAIRY_LIGHT = HELPER.createBlock("redstone_fairy_light", () -> new RedstoneFairyLightBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> s.getValue(RedstoneFairyLightBlock.LIT) ? 7 : 0)));

    public static final DeferredBlock<Block> ELDER_WING = HELPER.createBlock("elder_wing", () -> new FeatherWingBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(WindsweptSoundTypes.PINECONE)));
    public static final DeferredBlock<Block> ELDER_ORNAMENT = HELPER.createBlock("elder_ornament", () -> new FeatherOrnamentBlock(Properties.ELDER_ORNAMENT));
    public static final DeferredBlock<Block> DREAM_CATCHER = HELPER.createBlock("dream_catcher", () -> new DreamCatcherBlock(Properties.ELDER_ORNAMENT));

    public static final DeferredBlock<Block> PINECONE_BLOCK = HELPER.createBlock("pinecone_block", () -> new PineconeBlockBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CARVED_PINECONE_BLOCK = HELPER.createBlockWithItem("carved_pinecone_block", () -> new CarvedPineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE_BLOCK.get()).randomTicks()), () -> new WearableBlockItem(WindsweptBlocks.CARVED_PINECONE_BLOCK.get(), new Item.Properties()));
    public static final DeferredBlock<Block> WILL_O_THE_WISP = HELPER.createBlock("will_o_the_wisp", () -> new WillOTheWispBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE_BLOCK.get()).lightLevel(s -> 10)), new Item.Properties().rarity(Rarity.RARE));

    // Pinecone Shingles //
    public static final DeferredBlock<Block> PINECONE_SHINGLES = HELPER.createBlock("pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINECONE_SHINGLE_STAIRS = HELPER.createBlock("pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINECONE_SHINGLE_SLAB = HELPER.createBlock("pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLES = HELPER.createBlock("white_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("white_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLE_SLAB = HELPER.createBlock("white_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLES = HELPER.createBlock("light_gray_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("light_gray_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLE_SLAB = HELPER.createBlock("light_gray_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLES = HELPER.createBlock("gray_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("gray_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLE_SLAB = HELPER.createBlock("gray_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLES = HELPER.createBlock("black_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("black_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLE_SLAB = HELPER.createBlock("black_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLES = HELPER.createBlock("brown_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("brown_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLE_SLAB = HELPER.createBlock("brown_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLES = HELPER.createBlock("red_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("red_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLE_SLAB = HELPER.createBlock("red_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLES = HELPER.createBlock("orange_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("orange_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLE_SLAB = HELPER.createBlock("orange_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLES = HELPER.createBlock("yellow_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("yellow_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLE_SLAB = HELPER.createBlock("yellow_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLES = HELPER.createBlock("lime_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("lime_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLE_SLAB = HELPER.createBlock("lime_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLES = HELPER.createBlock("green_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("green_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLE_SLAB = HELPER.createBlock("green_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLES = HELPER.createBlock("cyan_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("cyan_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLE_SLAB = HELPER.createBlock("cyan_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLES = HELPER.createBlock("light_blue_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("light_blue_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLE_SLAB = HELPER.createBlock("light_blue_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLES = HELPER.createBlock("blue_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("blue_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLE_SLAB = HELPER.createBlock("blue_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLES = HELPER.createBlock("purple_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("purple_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLE_SLAB = HELPER.createBlock("purple_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLES = HELPER.createBlock("magenta_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("magenta_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLE_SLAB = HELPER.createBlock("magenta_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLES = HELPER.createBlock("pink_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLE_STAIRS = HELPER.createBlock("pink_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLE_SLAB = HELPER.createBlock("pink_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));

    // Icicle Blocks //
    public static final DeferredBlock<Block> ICICLES = HELPER.createBlock("icicles", () -> new IciclesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).noOcclusion()));
    public static final DeferredBlock<Block> ICICLE_BLOCK = HELPER.createBlock("icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> CHISELED_ICICLE_BLOCK = HELPER.createBlock("chiseled_icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> ICICLE_DOOR = HELPER.createBlock("icicle_door", () -> new DoorBlock(Properties.ICICLE_SET, Block.Properties.of().mapColor(MapColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion()));
    public static final DeferredBlock<Block> ICICLE_TRAPDOOR = HELPER.createBlock("icicle_trapdoor", () -> new TrapDoorBlock(Properties.ICICLE_SET, Block.Properties.of().mapColor(MapColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion().isValidSpawn(PropertyUtil::never)));
    public static final DeferredBlock<Block> ICICLE_BARS = HELPER.createBlock("icicle_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> ICE_LANTERN = HELPER.createBlock("ice_lantern", () -> new IceLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(s -> 13)));
    public static final DeferredBlock<Block> ICE_CHAIN = HELPER.createBlock("ice_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    // Shale //
    public static final DeferredBlock<Block> SHALE = HELPER.createBlock("shale", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_STAIRS = HELPER.createBlock("shale_stairs", () -> new StairBlock(SHALE.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_SLAB = HELPER.createBlock("shale_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_WALL = HELPER.createBlock("shale_wall", () -> new WallBlock(Properties.SHALE));

    public static final DeferredBlock<Block> POLISHED_SHALE = HELPER.createBlock("polished_shale", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_STAIRS = HELPER.createBlock("polished_shale_stairs", () -> new StairBlock(POLISHED_SHALE.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_SLAB = HELPER.createBlock("polished_shale_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_WALL = HELPER.createBlock("polished_shale_wall", () -> new WallBlock(Properties.SHALE));

    public static final DeferredBlock<Block> POLISHED_SHALE_BRICKS = HELPER.createBlock("polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> ICY_POLISHED_SHALE_BRICKS = HELPER.createBlock("icy_polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> CHISELED_POLISHED_SHALE_BRICKS = HELPER.createBlock("chiseled_polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_STAIRS = HELPER.createBlock("polished_shale_brick_stairs", () -> new StairBlock(POLISHED_SHALE_BRICKS.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_SLAB = HELPER.createBlock("polished_shale_brick_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_WALL = HELPER.createBlock("polished_shale_brick_wall", () -> new WallBlock(Properties.SHALE));

    // Packed Ice Blocks //
    public static final DeferredBlock<Block> PACKED_ICE_STAIRS = HELPER.createBlock("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));
    public static final DeferredBlock<Block> PACKED_ICE_SLAB = HELPER.createBlock("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredBlock<Block> PACKED_ICE_BRICKS = HELPER.createBlock("packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> CHISELED_PACKED_ICE_BRICKS = HELPER.createBlock("chiseled_packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_STAIRS = HELPER.createBlock("packed_ice_brick_stairs", () -> new StairBlock(PACKED_ICE_BRICKS.get().defaultBlockState(), Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_SLAB = HELPER.createBlock("packed_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_WALL = HELPER.createBlock("packed_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS));

    // Blue Ice Blocks //
    public static final DeferredBlock<Block> BLUE_ICE_STAIRS = HELPER.createBlock("blue_ice_stairs", () -> new StairBlock(Blocks.BLUE_ICE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));
    public static final DeferredBlock<Block> BLUE_ICE_SLAB = HELPER.createBlock("blue_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));

    public static final DeferredBlock<Block> BLUE_ICE_BRICKS = HELPER.createBlock("blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> CHISELED_BLUE_ICE_BRICKS = HELPER.createBlock("chiseled_blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_STAIRS = HELPER.createBlock("blue_ice_brick_stairs", () -> new StairBlock(BLUE_ICE_BRICKS.get().defaultBlockState(), Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_SLAB = HELPER.createBlock("blue_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_WALL = HELPER.createBlock("blue_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS));

    // Snow Blocks //
    public static final DeferredBlock<Block> SNOW_STAIRS = HELPER.createBlock("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredBlock<Block> SNOW_SLAB = HELPER.createBlock("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));

    public static final DeferredBlock<Block> SNOW_BRICKS = HELPER.createBlock("snow_bricks", () -> new Block(Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_STAIRS = HELPER.createBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get().defaultBlockState(), Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_SLAB = HELPER.createBlock("snow_brick_slab", () -> new SlabBlock(Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_WALL = HELPER.createBlock("snow_brick_wall", () -> new WallBlock(Properties.SNOW_BRICKS));

    public static final DeferredBlock<Block> SUSPICIOUS_SNOW = HELPER.createBlock("suspicious_snow", () -> new SuspiciousSnowBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(WindsweptSoundTypes.SUSPICIOUS_SNOW)));

    // Candy Cane Blocks //
    public static final DeferredBlock<Block> CANDY_CANE_BLOCK = HELPER.createBlock("candy_cane_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));

    // Ginger Blocks //
    public static final DeferredBlock<Block> GINGER_SOIL = HELPER.createBlock("ginger_soil", () -> new GingerSoilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(.5f).sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block> GINGER = HELPER.createBlockNoItem("ginger", () -> new GingerCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> GINGERBREAD_BLOCK = HELPER.createBlock("gingerbread_block", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BLOCK = HELPER.createBlock("glazed_gingerbread_block", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_COOKIE_BLOCK = HELPER.createBlock("gingerbread_cookie_block", () -> new Block(Properties.GINGERBREAD));

    public static final DeferredBlock<Block> GINGERBREAD_DOOR = HELPER.createBlock("gingerbread_door", () -> new DoorBlock(Properties.GINGERBREAD_SET, Properties.PINE.door()));
    public static final DeferredBlock<Block> GINGERBREAD_TRAPDOOR = HELPER.createBlock("gingerbread_trapdoor", () -> new TrapDoorBlock(Properties.GINGERBREAD_SET, Properties.PINE.trapdoor()));

    public static final DeferredBlock<Block> GINGERBREAD_BRICKS = HELPER.createBlock("gingerbread_bricks", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_STAIRS = HELPER.createBlock("gingerbread_brick_stairs", () -> new StairBlock(GINGERBREAD_BRICKS.get().defaultBlockState(), Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_SLAB = HELPER.createBlock("gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_WALL = HELPER.createBlock("gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD));

    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICKS = HELPER.createBlock("glazed_gingerbread_bricks", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_STAIRS = HELPER.createBlock("glazed_gingerbread_brick_stairs", () -> new StairBlock(GLAZED_GINGERBREAD_BRICKS.get().defaultBlockState(), Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_SLAB = HELPER.createBlock("glazed_gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_WALL = HELPER.createBlock("glazed_gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD));

    // Lunalite //
    public static final DeferredBlock<Block> LUNALITE = HELPER.createBlock("lunalite", () -> new LunaliteBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_STAIRS = HELPER.createBlock("lunalite_stairs", () -> new StairBlock(LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_SLAB = HELPER.createBlock("lunalite_slab", () -> new LunaliteSlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_WALL = HELPER.createBlock("lunalite_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> CUT_LUNALITE = HELPER.createBlock("cut_lunalite", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_STAIRS = HELPER.createBlock("cut_lunalite_stairs", () -> new StairBlock(CUT_LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_SLAB = HELPER.createBlock("cut_lunalite_slab", () -> new SlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_WALL = HELPER.createBlock("cut_lunalite_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> CUT_LUNALITE_BRICKS = HELPER.createBlock("cut_lunalite_bricks", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CHISELED_CUT_LUNALITE_BRICKS = HELPER.createBlock("chiseled_cut_lunalite_bricks", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_STAIRS = HELPER.createBlock("cut_lunalite_brick_stairs", () -> new StairBlock(CUT_LUNALITE_BRICKS.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_SLAB = HELPER.createBlock("cut_lunalite_brick_slab", () -> new SlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_WALL = HELPER.createBlock("cut_lunalite_brick_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> SMOOTH_LUNALITE = HELPER.createBlock("smooth_lunalite", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> SMOOTH_LUNALITE_STAIRS = HELPER.createBlock("smooth_lunalite_stairs", () -> new StairBlock(SMOOTH_LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> SMOOTH_LUNALITE_SLAB = HELPER.createBlock("smooth_lunalite_slab", () -> new SlabBlock(Properties.LUNALITE));

    // Dry Mossy Blocks //
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE = HELPER.createBlock("dry_mossy_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_STAIRS = HELPER.createBlock("dry_mossy_cobblestone_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_SLAB = HELPER.createBlock("dry_mossy_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_WALL = HELPER.createBlock("dry_mossy_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));

    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICKS = HELPER.createBlock("dry_mossy_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_STAIRS = HELPER.createBlock("dry_mossy_stone_brick_stairs", () -> new StairBlock(DRY_MOSSY_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_SLAB = HELPER.createBlock("dry_mossy_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_WALL = HELPER.createBlock("dry_mossy_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICKS = HELPER.createBlock("dry_mossy_cobblestone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_STAIRS = HELPER.createBlock("dry_mossy_cobblestone_brick_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_SLAB = HELPER.createBlock("dry_mossy_cobblestone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_WALL = HELPER.createBlock("dry_mossy_cobblestone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILES = HELPER.createBlock("dry_mossy_cobblestone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_STAIRS = HELPER.createBlock("dry_mossy_cobblestone_tile_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_SLAB = HELPER.createBlock("dry_mossy_cobblestone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_WALL = HELPER.createBlock("dry_mossy_cobblestone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    // Compressed Blocks //
    public static final DeferredBlock<Block> CHESTNUT_CRATE = HELPER.createFuelBlock("chestnut_crate", () -> new WindsweptCompressedBlock(Properties.CRATE), 300);
    public static final DeferredBlock<Block> ROASTED_CHESTNUT_CRATE = HELPER.createFuelBlock("roasted_chestnut_crate", () -> new WindsweptCompressedBlock(Properties.CRATE), 300);
    public static final DeferredBlock<Block> GINGER_ROOT_CRATE = HELPER.createFuelBlock("ginger_root_crate", () -> new WindsweptCompressedBlock(Properties.CRATE), 300);
    public static final DeferredBlock<Block> HOLLY_BERRY_BASKET = HELPER.createFuelBlock("holly_berry_basket", () -> new WindsweptCompressedBlock(Properties.HOLLY_BERRY_BASKET), 300);
    public static final DeferredBlock<Block> WILD_BERRY_BASKET = HELPER.createFuelBlock("wild_berry_basket", () -> new WindsweptCompressedBlock(Properties.CRATE), 300);
    public static final DeferredBlock<Block> FROZEN_FLESH_BLOCK = HELPER.createBlock("frozen_flesh_block", () -> new Block(BlockBehaviour.Properties.of().strength(.8f).sound(SoundType.CORAL_BLOCK)));

    // Pots //
    public static final DeferredBlock<Block> POTTED_RED_ROSE = HELPER.createBlockNoItem("potted_red_rose", () -> new FlowerPotBlock(RED_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = HELPER.createBlockNoItem("potted_blue_rose", () -> new FlowerPotBlock(BLUE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_WHITE_ROSE = HELPER.createBlockNoItem("potted_white_rose", () -> new FlowerPotBlock(WHITE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_YELLOW_ROSE = HELPER.createBlockNoItem("potted_yellow_rose", () -> new FlowerPotBlock(YELLOW_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_FOXGLOVE = HELPER.createBlockNoItem("potted_foxglove", () -> new FlowerPotBlock(FOXGLOVE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_BLUEBELLS = HELPER.createBlockNoItem("potted_bluebells", () -> new FlowerPotBlock(BLUEBELLS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_SNOWDROP = HELPER.createBlockNoItem("potted_snowdrop", () -> new FlowerPotBlock(SNOWDROP.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MOSS_CAMPION = HELPER.createBlockNoItem("potted_moss_campion", () -> new FlowerPotBlock(MOSS_CAMPION.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_WILD_GINGER = HELPER.createBlockNoItem("potted_wild_ginger", () -> new FlowerPotBlock(WILD_GINGER.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_NIGHTSHADE = HELPER.createBlockNoItem("potted_nightshade", () -> new NightShadeFlowerPotBlock(NIGHTSHADE.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().lightLevel(state -> 9)));
    public static final DeferredBlock<Block> POTTED_SNOWY_SPROUTS = HELPER.createBlockNoItem("potted_snowy_sprouts", () -> new FlowerPotBlock(SNOWY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_GELISOL_GRASS = HELPER.createBlockNoItem("potted_gelisol_grass", () -> new FlowerPotBlock(GELISOL_GRASS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_DRY_MOSSY_SPROUTS = HELPER.createBlockNoItem("potted_dry_mossy_sprouts", () -> new FlowerPotBlock(DRY_MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MOSSY_SPROUTS = HELPER.createBlockNoItem("potted_mossy_sprouts", () -> new FlowerPotBlock(MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_LAVENDER = HELPER.createBlockNoItem("potted_lavender", () -> new FlowerPotBlock(LAVENDER.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MIMOSA = HELPER.createBlockNoItem("potted_mimosa", () -> new FlowerPotBlock(MIMOSA.get(), PropertyUtil.flowerPot()));

    // Misc //
    public static final DeferredBlock<Block> WILD_BERRY_BUSH = HELPER.createBlockNoItem("wild_berry_bush", () -> new WildBerryBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));

    public static class Properties {
        // Block Sets //
        public static final BlockSetType HOLLY_BLOCK_SET = new BlockSetType(Windswept.MOD_ID + ":holly", true, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);
        public static final BlockSetType CHESTNUT_BLOCK_SET = new BlockSetType(Windswept.MOD_ID + ":chestnut");
        public static final BlockSetType PINE_BLOCK_SET = new BlockSetType(Windswept.MOD_ID + ":pine");
        public static final BlockSetType ICICLE_SET = new BlockSetType(Windswept.MOD_ID + ":icicles", true, SoundType.GLASS, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
        public static final BlockSetType GINGERBREAD_SET = new BlockSetType(Windswept.MOD_ID + ":gingerbread", true, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);

        public static final WoodType HOLLY_WOOD_TYPE = new WoodType(Windswept.MOD_ID + ":holly", HOLLY_BLOCK_SET, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN);
        public static final WoodType CHESTNUT_WOOD_TYPE = new WoodType(Windswept.MOD_ID + ":chestnut", CHESTNUT_BLOCK_SET);
        public static final WoodType PINE_WOOD_TYPE = new WoodType(Windswept.MOD_ID + ":pine", PINE_BLOCK_SET);
        public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MapColor.COLOR_PURPLE).sound(SoundType.CHERRY_WOOD).logSound(SoundType.CHERRY_WOOD).build();
        public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MapColor.COLOR_BROWN).build();
        public static final PropertyUtil.WoodSetProperties PINE = PropertyUtil.WoodSetProperties.builder(MapColor.TERRACOTTA_BROWN).build();

        // Blocks //
        public static final BlockBehaviour.Properties SNOW_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(.85f).sound(SoundType.SNOW);
        public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);
        public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.WOOD);
        public static final BlockBehaviour.Properties HOLLY_BERRY_BASKET = BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.CHERRY_WOOD);
        public static final BlockBehaviour.Properties SPROUTS = BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).replaceable();
        public static final BlockBehaviour.Properties PINECONE_BLOCK = Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2f, 3f).sound(WindsweptSoundTypes.PINECONE);
        public static final BlockBehaviour.Properties SHALE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.DRIPSTONE_BLOCK).requiresCorrectToolForDrops().strength(1.5f, 1f);
        public static final BlockBehaviour.Properties LUNALITE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(.8f);
        public static final BlockBehaviour.Properties GINGERBREAD = Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(2f, 3f).sound(SoundType.CHERRY_WOOD);
        public static final BlockBehaviour.Properties ELDER_ORNAMENT = Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(SoundType.AZALEA);
        public static final BlockBehaviour.Properties LAVENDER_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(.5f).sound(SoundType.NETHER_SPROUTS).noOcclusion();
    }
}