package com.rosemods.windswept.core.data.server;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.block.GingerCropBlock;
import com.rosemods.windswept.common.block.LavenderBlock;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptLootTableProvider extends LootTableProvider {

    public WindsweptLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Set.of(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(WindsweptBlockLoot::new, LootContextParamSets.BLOCK),
                new WindsweptEntityLoot(provider),
                new LootTableProvider.SubProviderEntry(WindsweptChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(WindsweptArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ), provider);
    }

    private static class WindsweptBlockLoot extends BlockLootSubProvider {
        protected WindsweptBlockLoot(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
            HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

            this.dropSelf(STRIPPED_HOLLY_LOG.get());
            this.dropSelf(STRIPPED_HOLLY_WOOD.get());
            this.dropSelf(HOLLY_LOG.get());
            this.dropSelf(HOLLY_WOOD.get());
            this.dropSelf(HOLLY_PLANKS.get());
            this.add(HOLLY_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(HOLLY_STAIRS.get());
            this.dropSelf(HOLLY_FENCE.get());
            this.dropSelf(HOLLY_FENCE_GATE.get());
            this.dropSelf(HOLLY_PRESSURE_PLATE.get());
            this.add(HOLLY_DOOR.get(), this::createDoorTable);
            this.dropSelf(HOLLY_TRAPDOOR.get());
            this.dropSelf(HOLLY_BUTTON.get());
            this.dropSelf(HOLLY_SIGNS.getFirst().get());
            this.dropSelf(HOLLY_HANGING_SIGNS.getFirst().get());
            this.dropSelf(HOLLY_SAPLING.get());
            this.dropPottedContents(POTTED_HOLLY_SAPLING.get());
            this.add(HOLLY_BEEHIVE.get(), b -> createBeeHiveDrop(b));
            this.dropSelf(HOLLY_LADDER.get());
            this.add(HOLLY_BOOKSHELF.get(), b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3.0F)));
            this.dropWhenSilkTouch(CHISELED_HOLLY_BOOKSHELF.get());
            this.dropSelf(HOLLY_BOARDS.get());
            this.dropSelf(HOLLY_CHEST.get());
            this.dropSelf(TRAPPED_HOLLY_CHEST.get());
            this.add(HOLLY_LEAVES.get(), b -> createLeavesDrops(b, HOLLY_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveShearsOrSilkTouch()).add(this.applyExplosionCondition(b, LootItem.lootTableItem(HOLLY_BERRIES.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 1.0F))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));
            this.createMultifaceBlockDrops(HOLLY_LEAF_PILE.get(), ANY_SHEARS);
            this.dropSelf(HOLLY_BERRY_BASKET.get());

            this.dropSelf(STRIPPED_CHESTNUT_LOG.get());
            this.dropSelf(STRIPPED_CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_LOG.get());
            this.dropSelf(CHESTNUT_WOOD.get());
            this.dropSelf(CHESTNUT_PLANKS.get());
            this.add(CHESTNUT_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CHESTNUT_STAIRS.get());
            this.dropSelf(CHESTNUT_FENCE.get());
            this.dropSelf(CHESTNUT_FENCE_GATE.get());
            this.dropSelf(CHESTNUT_PRESSURE_PLATE.get());
            this.add(CHESTNUT_DOOR.get(), this::createDoorTable);
            this.dropSelf(CHESTNUT_TRAPDOOR.get());
            this.dropSelf(CHESTNUT_BUTTON.get());
            this.dropSelf(CHESTNUT_SIGNS.getFirst().get());
            this.dropSelf(CHESTNUT_HANGING_SIGNS.getFirst().get());
            this.dropSelf(CHESTNUT_SAPLING.get());
            this.dropPottedContents(POTTED_CHESTNUT_SAPLING.get());
            this.add(CHESTNUT_BEEHIVE.get(), b -> createBeeHiveDrop(b));
            this.dropSelf(CHESTNUT_LADDER.get());
            this.add(CHESTNUT_BOOKSHELF.get(), b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3.0F)));
            this.dropWhenSilkTouch(CHISELED_CHESTNUT_BOOKSHELF.get());
            this.dropSelf(CHESTNUT_BOARDS.get());
            this.dropSelf(CHESTNUT_CHEST.get());
            this.dropSelf(TRAPPED_CHESTNUT_CHEST.get());
            this.add(CHESTNUT_LEAVES.get(), b -> createLeavesDrops(b, CHESTNUT_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveShearsOrSilkTouch()).add(this.applyExplosionCondition(b, LootItem.lootTableItem(CHESTNUTS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(-4.0F, 1.0F))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));
            this.createMultifaceBlockDrops(CHESTNUT_LEAF_PILE.get(), ANY_SHEARS);
            this.dropSelf(CHESTNUT_CRATE.get());
            this.dropSelf(ROASTED_CHESTNUT_CRATE.get());

            this.dropSelf(STRIPPED_PINE_LOG.get());
            this.dropSelf(STRIPPED_PINE_WOOD.get());
            this.dropSelf(WEATHERED_PINE_LOG.get());
            this.dropSelf(WEATHERED_PINE_WOOD.get());
            this.dropSelf(PINE_LOG.get());
            this.dropSelf(PINE_WOOD.get());
            this.dropSelf(PINE_PLANKS.get());
            this.add(PINE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PINE_STAIRS.get());
            this.dropSelf(PINE_FENCE.get());
            this.dropSelf(PINE_FENCE_GATE.get());
            this.dropSelf(PINE_PRESSURE_PLATE.get());
            this.add(PINE_DOOR.get(), this::createDoorTable);
            this.dropSelf(PINE_TRAPDOOR.get());
            this.dropSelf(PINE_BUTTON.get());
            this.dropSelf(PINE_SIGNS.getFirst().get());
            this.dropSelf(PINE_HANGING_SIGNS.getFirst().get());
            this.dropSelf(PINE_SAPLING.get());
            this.dropPottedContents(POTTED_PINE_SAPLING.get());
            this.add(PINE_BEEHIVE.get(), b -> createBeeHiveDrop(b));
            this.dropSelf(PINE_LADDER.get());
            this.add(PINE_BOOKSHELF.get(), b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3.0F)));
            this.dropWhenSilkTouch(CHISELED_PINE_BOOKSHELF.get());
            this.dropSelf(PINE_BOARDS.get());
            this.dropSelf(PINE_CHEST.get());
            this.dropSelf(TRAPPED_PINE_CHEST.get());
            this.add(PINE_LEAVES.get(), b -> createLeavesDrops(b, PINE_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F));
            this.createMultifaceBlockDrops(PINE_LEAF_PILE.get(), ANY_SHEARS);

            this.add(PINECONE.get(), b -> createPineconeTable(b));
            this.add(FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.add(SOUL_FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.add(CUPRIC_FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.add(ENDER_FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.add(NIGHT_FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.add(REDSTONE_FAIRY_LIGHT.get(), b -> createPineconeTable(b));
            this.dropSelf(PINECONE_JAM_BLOCK.get());
            this.dropSelf(PINECONE_BLOCK.get());
            this.dropSelf(CARVED_PINECONE_BLOCK.get());
            this.dropSelf(WILL_O_THE_WISP.get());
            this.dropSelf(ELDER_WING.get());
            this.dropSelf(ELDER_ORNAMENT.get());
            this.add(DREAM_CATCHER.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));

            this.dropSelf(PINECONE_SHINGLES.get());
            this.dropSelf(PINECONE_SHINGLE_STAIRS.get());
            this.add(PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(WHITE_PINECONE_SHINGLES.get());
            this.dropSelf(WHITE_PINECONE_SHINGLE_STAIRS.get());
            this.add(WHITE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIGHT_GRAY_PINECONE_SHINGLES.get());
            this.dropSelf(LIGHT_GRAY_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIGHT_GRAY_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GRAY_PINECONE_SHINGLES.get());
            this.dropSelf(GRAY_PINECONE_SHINGLE_STAIRS.get());
            this.add(GRAY_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLACK_PINECONE_SHINGLES.get());
            this.dropSelf(BLACK_PINECONE_SHINGLE_STAIRS.get());
            this.add(BLACK_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BROWN_PINECONE_SHINGLES.get());
            this.dropSelf(BROWN_PINECONE_SHINGLE_STAIRS.get());
            this.add(BROWN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(RED_PINECONE_SHINGLES.get());
            this.dropSelf(RED_PINECONE_SHINGLE_STAIRS.get());
            this.add(RED_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(ORANGE_PINECONE_SHINGLES.get());
            this.dropSelf(ORANGE_PINECONE_SHINGLE_STAIRS.get());
            this.add(ORANGE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(YELLOW_PINECONE_SHINGLES.get());
            this.dropSelf(YELLOW_PINECONE_SHINGLE_STAIRS.get());
            this.add(YELLOW_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIME_PINECONE_SHINGLES.get());
            this.dropSelf(LIME_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIME_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GREEN_PINECONE_SHINGLES.get());
            this.dropSelf(GREEN_PINECONE_SHINGLE_STAIRS.get());
            this.add(GREEN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CYAN_PINECONE_SHINGLES.get());
            this.dropSelf(CYAN_PINECONE_SHINGLE_STAIRS.get());
            this.add(CYAN_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LIGHT_BLUE_PINECONE_SHINGLES.get());
            this.dropSelf(LIGHT_BLUE_PINECONE_SHINGLE_STAIRS.get());
            this.add(LIGHT_BLUE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_PINECONE_SHINGLES.get());
            this.dropSelf(BLUE_PINECONE_SHINGLE_STAIRS.get());
            this.add(BLUE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PURPLE_PINECONE_SHINGLES.get());
            this.dropSelf(PURPLE_PINECONE_SHINGLE_STAIRS.get());
            this.add(PURPLE_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(MAGENTA_PINECONE_SHINGLES.get());
            this.dropSelf(MAGENTA_PINECONE_SHINGLE_STAIRS.get());
            this.add(MAGENTA_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PINK_PINECONE_SHINGLES.get());
            this.dropSelf(PINK_PINECONE_SHINGLE_STAIRS.get());
            this.add(PINK_PINECONE_SHINGLE_SLAB.get(), this::createSlabItemTable);

            this.dropSelf(SNOW_STAIRS.get());
            this.add(SNOW_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SNOW_BRICKS.get());
            this.dropSelf(SNOW_BRICK_STAIRS.get());
            this.add(SNOW_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SNOW_BRICK_WALL.get());
            this.add(SUSPICIOUS_SNOW.get(), noDrop());

            this.dropSelf(PACKED_ICE_STAIRS.get());
            this.add(PACKED_ICE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICKS.get());
            this.dropSelf(CHISELED_PACKED_ICE_BRICKS.get());
            this.dropSelf(PACKED_ICE_BRICK_STAIRS.get());
            this.add(PACKED_ICE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(PACKED_ICE_BRICK_WALL.get());

            this.dropSelf(BLUE_ICE_STAIRS.get());
            this.add(BLUE_ICE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICKS.get());
            this.dropSelf(CHISELED_BLUE_ICE_BRICKS.get());
            this.dropSelf(BLUE_ICE_BRICK_STAIRS.get());
            this.add(BLUE_ICE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(BLUE_ICE_BRICK_WALL.get());

            this.dropSelf(SHALE.get());
            this.dropSelf(SHALE_STAIRS.get());
            this.add(SHALE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SHALE_WALL.get());
            this.dropSelf(POLISHED_SHALE.get());
            this.dropSelf(POLISHED_SHALE_STAIRS.get());
            this.dropSelf(POLISHED_SHALE_WALL.get());
            this.add(POLISHED_SHALE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICKS.get());
            this.dropSelf(ICY_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(CHISELED_POLISHED_SHALE_BRICKS.get());
            this.dropSelf(POLISHED_SHALE_BRICK_STAIRS.get());
            this.add(POLISHED_SHALE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(POLISHED_SHALE_BRICK_WALL.get());

            this.dropSelf(CANDY_CANE_BLOCK.get());

            this.add(GINGER.get(), b -> this.applyExplosionDecay(b, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(GINGER_ROOT.get()))).withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 4))).add(LootItem.lootTableItem(GINGER_ROOT.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantments.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));
            this.add(GINGER_SOIL.get(), b -> dropTwoOthers(b, Items.DIRT, GINGER_ROOT.get()));
            this.dropSelf(GINGERBREAD_BLOCK.get());
            this.dropSelf(GLAZED_GINGERBREAD_BLOCK.get());
            this.dropSelf(GINGERBREAD_BRICKS.get());
            this.dropSelf(GINGERBREAD_BRICK_STAIRS.get());
            this.add(GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GINGERBREAD_BRICK_WALL.get());
            this.dropSelf(GLAZED_GINGERBREAD_BRICKS.get());
            this.dropSelf(GINGERBREAD_COOKIE_BLOCK.get());
            this.dropSelf(GLAZED_GINGERBREAD_BRICK_STAIRS.get());
            this.add(GLAZED_GINGERBREAD_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(GLAZED_GINGERBREAD_BRICK_WALL.get());
            this.dropSelf(GINGER_ROOT_CRATE.get());
            this.add(GINGERBREAD_DOOR.get(), this::createDoorTable);
            this.dropSelf(GINGERBREAD_TRAPDOOR.get());

            this.dropSelf(FROSTBITER_TROPHY.get());
            this.add(CHRISTMAS_PUDDING.get(), noDrop());
            this.dropSelf(HOLLY_WREATH.get());
            this.dropSelf(PINECONE_WREATH.get());
            this.dropSelf(VINE_WREATH.get());
            this.dropSelf(CHERRY_WREATH.get());
            this.dropSelf(ICE_SHEET.get());

            this.add(LUPINE.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(LIONS_TAIL.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(RED_ROSE_BUSH.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(BLUE_ROSE_BUSH.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(WHITE_ROSE_BUSH.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(YELLOW_ROSE_BUSH.get(), b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));

            this.add(SNOWY_SPROUTS.get(), BlockLootSubProvider::createShearsOnlyDrop);
            this.add(GELISOL_GRASS.get(), BlockLootSubProvider::createShearsOnlyDrop);
            this.add(DRY_MOSSY_SPROUTS.get(), BlockLootSubProvider::createShearsOnlyDrop);
            this.add(MOSSY_SPROUTS.get(), BlockLootSubProvider::createShearsOnlyDrop);

            this.dropSelf(RED_ROSE.get());
            this.dropSelf(BLUE_ROSE.get());
            this.dropSelf(WHITE_ROSE.get());
            this.dropSelf(YELLOW_ROSE.get());
            this.dropSelf(FOXGLOVE.get());
            this.dropSelf(BLUEBELLS.get());
            this.dropSelf(SNOWDROP.get());
            this.dropSelf(MOSS_CAMPION.get());
            this.add(WILD_GINGER.get(), b -> createShearsDispatchTable(b, this.applyExplosionDecay(b, LootItem.lootTableItem(GINGER_ROOT.get()))));
            this.dropSelf(NIGHTSHADE.get());
            this.add(LAVENDER.get(), b -> createLavenderTable(b));
            this.dropSelf(MIMOSA.get());
            this.dropSelf(FLOWERING_ACACIA_SAPLING.get());
            this.dropPottedContents(POTTED_FLOWERING_ACACIA_SAPLING.get());
            this.add(FLOWERING_ACACIA_LEAVES.get(), b -> createLeavesDrops(b, FLOWERING_ACACIA_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F));
            this.createMultifaceBlockDrops(FLOWERING_ACACIA_LEAF_PILE.get(), ANY_SHEARS);
            this.add(YELLOW_PETALS.get(), b -> createPetalsTable(b));

            this.dropPottedContents(POTTED_RED_ROSE.get());
            this.dropPottedContents(POTTED_BLUE_ROSE.get());
            this.dropPottedContents(POTTED_WHITE_ROSE.get());
            this.dropPottedContents(POTTED_YELLOW_ROSE.get());
            this.dropPottedContents(POTTED_FOXGLOVE.get());
            this.dropPottedContents(POTTED_BLUEBELLS.get());
            this.dropPottedContents(POTTED_SNOWDROP.get());
            this.dropPottedContents(POTTED_MOSS_CAMPION.get());
            this.dropPottedContents(POTTED_WILD_GINGER.get());
            this.dropPottedContents(POTTED_NIGHTSHADE.get());
            this.dropPottedContents(POTTED_SNOWY_SPROUTS.get());
            this.dropPottedContents(POTTED_GELISOL_GRASS.get());
            this.dropPottedContents(POTTED_DRY_MOSSY_SPROUTS.get());
            this.dropPottedContents(POTTED_MOSSY_SPROUTS.get());
            this.dropPottedContents(POTTED_LAVENDER.get());
            this.dropPottedContents(POTTED_MIMOSA.get());

            this.dropSelf(LAVENDER_BALE.get());
            this.dropSelf(LAVENDER_THATCH.get());
            this.dropSelf(LAVENDER_THATCH_STAIRS.get());
            this.add(LAVENDER_THATCH_SLAB.get(), this::createSlabItemTable);

            this.add(WILD_BERRY_BUSH.get(), b -> this.applyExplosionDecay(b, LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))).add(LootItem.lootTableItem(WILD_BERRIES.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))))));
            this.dropSelf(WILD_BERRY_BASKET.get());

            this.dropSelf(ICICLES.get());
            this.dropSelf(ICICLE_BLOCK.get());
            this.dropSelf(CHISELED_ICICLE_BLOCK.get());
            this.add(ICICLE_DOOR.get(), this::createDoorTable);
            this.dropSelf(ICICLE_TRAPDOOR.get());
            this.dropSelf(ICICLE_BARS.get());
            this.dropSelf(ICE_LANTERN.get());
            this.dropSelf(ICE_CHAIN.get());

            this.dropSelf(LUNALITE.get());
            this.dropSelf(LUNALITE_STAIRS.get());
            this.add(LUNALITE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(LUNALITE_WALL.get());
            this.dropSelf(CUT_LUNALITE.get());
            this.dropSelf(CUT_LUNALITE_STAIRS.get());
            this.add(CUT_LUNALITE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CUT_LUNALITE_WALL.get());
            this.dropSelf(CUT_LUNALITE_BRICKS.get());
            this.dropSelf(CHISELED_CUT_LUNALITE_BRICKS.get());
            this.dropSelf(CUT_LUNALITE_BRICK_STAIRS.get());
            this.add(CUT_LUNALITE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(CUT_LUNALITE_BRICK_WALL.get());
            this.dropSelf(SMOOTH_LUNALITE.get());
            this.dropSelf(SMOOTH_LUNALITE_STAIRS.get());
            this.add(SMOOTH_LUNALITE_SLAB.get(), this::createSlabItemTable);

            this.dropSelf(DRY_MOSS_CARPET.get());
            this.dropSelf(DRY_MOSS_BLOCK.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_WALL.get());
            this.dropSelf(DRY_MOSSY_STONE_BRICKS.get());
            this.dropSelf(DRY_MOSSY_STONE_BRICK_STAIRS.get());
            this.add(DRY_MOSSY_STONE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_STONE_BRICK_WALL.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICKS.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_BRICK_WALL.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILES.get());
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get());
            this.add(DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(DRY_MOSSY_COBBLESTONE_TILE_WALL.get());

            this.add(GELISOL.get(), b -> createSingleItemTableWithSilkTouch(b, Blocks.DIRT));
            this.add(GELISOL_PATH.get(), b -> createSingleItemTableWithSilkTouch(b, Blocks.DIRT));
            this.dropSelf(FROZEN_FLESH_BLOCK.get());
        }

        private LootTable.Builder createPineconeTable(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PineconeBlock.AMOUNT, i)))))));
        }

        private LootTable.Builder createPetalsTable(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))))));
        }

        private LootTable.Builder createLavenderTable(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(0, 1, 2), i -> SetItemCountFunction.setCount(ConstantValue.exactly(i + 1.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LavenderBlock.AGE, i)))))));
        }

        private LootTable.Builder dropTwoOthers(Block block, net.minecraft.world.level.ItemLike other, net.minecraft.world.level.ItemLike another) {
            return LootTable.lootTable().withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SILK_TOUCH).add(LootItem.lootTableItem(block)))).withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveSilkTouch()).add(LootItem.lootTableItem(other)))).withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveSilkTouch()).add(LootItem.lootTableItem(another))));
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Windswept.MOD_ID)).collect(Collectors.toSet());
        }
    }

    private static class WindsweptEntityLoot extends EntityLootSubProvider {
        protected WindsweptEntityLoot(CompletableFuture<HolderLookup.Provider> provider) {
            super(FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            this.add(WindsweptEntityTypes.CHILLED.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(FROZEN_FLESH.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(-2.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ICICLES.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(-3.0F, 1.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.GOLD_INGOT)).add(LootItem.lootTableItem(Items.BEETROOT)).add(LootItem.lootTableItem(Items.APPLE).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(MUSIC_DISC_SNOW.get())).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
            this.add(WindsweptEntityTypes.FROSTBITER.get(), LootTable.lootTable());
        }

        @Override
        public Stream<EntityType<?>> getKnownEntityTypes() {
            return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace().equals(Windswept.MOD_ID));
        }
    }

    private static record WindsweptChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Windswept.location("chests/grove_weathered_house")), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4.0F, 8.0F)).add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(SNOWY_SPROUTS.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))).add(LootItem.lootTableItem(SNOW_BOOTS.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20)))).add(LootItem.lootTableItem(Items.BOOK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(WOODEN_BUCKET.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20)))).add(LootItem.lootTableItem(SWEET_SNOW_CONE.get()).setWeight(1)).add(LootItem.lootTableItem(WILD_BERRIES.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))).add(LootItem.lootTableItem(ICICLES.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(Items.SNOWBALL).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(Items.COBWEB).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(HOLLY_SAPLING.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))).add(LootItem.lootTableItem(Items.COOKED_CHEVON).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));
        }
    }

    private static record WindsweptArchaeologyLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, Windswept.location("archaeology/pine_totem")), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(HOOT_POTTERY_SHERD.get()).setWeight(3)).add(LootItem.lootTableItem(PLUMAGE_POTTERY_SHERD.get()).setWeight(4)).add(LootItem.lootTableItem(ELDER_FEATHER.get()).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))).add(LootItem.lootTableItem(PINECONE.get()).setWeight(1)).add(LootItem.lootTableItem(Items.EMERALD).setWeight(1)).add(LootItem.lootTableItem(Items.STICK).setWeight(1))));
        }
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, net.minecraft.util.ProblemReporter.Collector collector) {
    }
}