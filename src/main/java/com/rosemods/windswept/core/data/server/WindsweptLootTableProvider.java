package com.rosemods.windswept.core.data.server;

import com.google.common.collect.ImmutableList;
import com.rosemods.windswept.common.block.GingerCropBlock;
import com.rosemods.windswept.common.block.LavenderBlock;
import com.rosemods.windswept.common.block.PineconeBlock;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptLootTableProvider extends LootTableProvider {

    public WindsweptLootTableProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(WindsweptBlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(WindsweptEntityLoot::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(WindsweptChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(WindsweptArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ), event.getLookupProvider());
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
    }

    private static class WindsweptBlockLoot extends BlockLootSubProvider {
        protected WindsweptBlockLoot(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
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
            this.add(HOLLY_BEEHIVE.get(), block -> createBeeHiveDrop(block));
            this.dropSelf(HOLLY_LADDER.get());
            this.bookshelf(HOLLY_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_HOLLY_BOOKSHELF.get());
            this.dropSelf(HOLLY_BOARDS.get());
            this.dropSelf(HOLLY_CHEST.get());
            this.dropSelf(TRAPPED_HOLLY_CHEST.get());

            HolderLookup.RegistryLookup<net.minecraft.world.item.enchantment.Enchantment> enchants = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

            this.add(HOLLY_LEAVES.get(), b -> createLeavesDrops(b, HOLLY_SAPLING.get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(this.doesNotHaveShearsOrSilkTouch())
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(HOLLY_BERRIES.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-6f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(enchants.getOrThrow(Enchantments.FORTUNE))))));

            this.leafPile(HOLLY_LEAF_PILE.get());
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
            this.add(CHESTNUT_BEEHIVE.get(), block -> createBeeHiveDrop(block));
            this.dropSelf(CHESTNUT_LADDER.get());
            this.bookshelf(CHESTNUT_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_CHESTNUT_BOOKSHELF.get());
            this.dropSelf(CHESTNUT_BOARDS.get());
            this.dropSelf(CHESTNUT_CHEST.get());
            this.dropSelf(TRAPPED_CHESTNUT_CHEST.get());

            this.add(CHESTNUT_LEAVES.get(), b -> createLeavesDrops(b, CHESTNUT_SAPLING.get(), .05f, .0625f, .083333336f, .1f).withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f)).when(this.doesNotHaveShearsOrSilkTouch())
                    .add(applyExplosionCondition(b, LootItem.lootTableItem(CHESTNUTS.get()))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(-4f, 1f)))
                            .apply(ApplyBonusCount.addUniformBonusCount(enchants.getOrThrow(Enchantments.FORTUNE))))));

            this.leafPile(CHESTNUT_LEAF_PILE.get());
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
            this.add(PINE_BEEHIVE.get(), block -> createBeeHiveDrop(block));
            this.dropSelf(PINE_LADDER.get());
            this.bookshelf(PINE_BOOKSHELF.get());
            this.dropWhenSilkTouch(CHISELED_PINE_BOOKSHELF.get());
            this.dropSelf(PINE_BOARDS.get());
            this.dropSelf(PINE_CHEST.get());
            this.dropSelf(TRAPPED_PINE_CHEST.get());
            this.add(PINE_LEAVES.get(), b -> createLeavesDrops(b, PINE_SAPLING.get(), .05f, .0625f, .083333336f, .1f));
            this.leafPile(PINE_LEAF_PILE.get());
            this.add(PINECONE.get(), this::createPineconeTable);
            this.add(FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(SOUL_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(CUPRIC_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(ENDER_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(NIGHT_FAIRY_LIGHT.get(), this::createPineconeTable);
            this.add(REDSTONE_FAIRY_LIGHT.get(), this::createPineconeTable);
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
            this.dropSelf(SNOW_STAIRS.get());
            this.add(SNOW_SLAB.get(), this::createSlabItemTable);
            this.dropSelf(SNOW_BRICKS.get());
            this.add(GINGER.get(), b -> createGingerDrops(b));
            this.dropSelf(GINGERBREAD_BLOCK.get());
            this.tallFlower(LUPINE.get());
            this.add(SNOWY_SPROUTS.get(), BlockLootSubProvider::createShearsOnlyDrop);
            this.dropSelf(RED_ROSE.get());
            this.add(WILD_BERRY_BUSH.get(), b -> applyExplosionDecay(b, LootTable.lootTable().withPool(LootPool.lootPool()
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)))
                    .add(LootItem.lootTableItem(WILD_BERRIES.get()))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
                    .apply(ApplyBonusCount.addUniformBonusCount(enchants.getOrThrow(Enchantments.FORTUNE))))));
        }

        private void bookshelf(Block block) {
            this.add(block, b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3f)));
        }

        private void tallFlower(Block block) {
            this.add(block, b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        }

        private void leafPile(Block block) {
            this.add(block, b -> createMultifaceBlockDrops(b, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR))));
        }

        private LootTable.Builder createGingerDrops(Block block) {
            return applyExplosionDecay(block, LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(GINGER_ROOT.get())))
                    .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 4)))
                            .add(LootItem.lootTableItem(GINGER_ROOT.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), .5714286f, 3)))));
        }

        private LootTable.Builder createPineconeTable(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                    .add(applyExplosionDecay(block, LootItem.lootTableItem(block)
                            .apply(List.of(2, 3, 4), i -> SetItemCountFunction.setCount(ConstantValue.exactly((float) i))
                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PineconeBlock.AMOUNT, i)))))));
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> Windswept.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toSet());
        }
    }

    private static class WindsweptEntityLoot extends EntityLootSubProvider {
        protected WindsweptEntityLoot(HolderLookup.Provider provider) {
            super(FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            this.add(WindsweptEntityTypes.CHILLED.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(FROZEN_FLESH.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(-2f, 2f)))
                                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0f, 1f)))))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                            .add(LootItem.lootTableItem(MUSIC_DISC_SNOW.get()))
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
            this.add(WindsweptEntityTypes.FROSTBITER.get(), LootTable.lootTable());
        }

        @Override
        public Stream<EntityType<?>> getKnownEntityTypes() {
            return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> Windswept.MOD_ID.equals(BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace()));
        }
    }

    private static class WindsweptChestLoot implements LootTableSubProvider {
        private final HolderLookup.Provider provider;
        protected WindsweptChestLoot(HolderLookup.Provider provider) { this.provider = provider; }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, Builder> builder) {
            builder.accept(ResourceKey.create(Registries.LOOT_TABLE, Windswept.location("chests/grove_weathered_house")), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(4f, 8f))
                    .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                    .add(LootItem.lootTableItem(WindsweptBlocks.SNOWY_SPROUTS.get()).setWeight(4))));
        }
    }

    private static class WindsweptArchaeologyLoot implements LootTableSubProvider {
        private final HolderLookup.Provider provider;
        protected WindsweptArchaeologyLoot(HolderLookup.Provider provider) { this.provider = provider; }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, Builder> builder) {
            builder.accept(ResourceKey.create(Registries.LOOT_TABLE, Windswept.location("archaeology/pine_totem")), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                    .add(LootItem.lootTableItem(WindsweptItems.HOOT_POTTERY_SHERD.get()).setWeight(3))));
        }
    }
}