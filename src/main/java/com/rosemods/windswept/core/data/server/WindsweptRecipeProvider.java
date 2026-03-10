package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;

import java.util.concurrent.CompletableFuture;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptRecipeProvider extends BlueprintRecipeProvider {

    public WindsweptRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(Windswept.MOD_ID, output, provider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        // foods
        this.conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHESTNUT_SOUP.get()).requires(Items.BOWL).requires(ROASTED_CHESTNUTS.get(), 2).requires(Items.CARROT).requires(Items.POTATO).unlockedBy(getHasName(ROASTED_CHESTNUTS.get()), has(ROASTED_CHESTNUTS.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), consumer, getSaveLocation(CHESTNUT_SOUP.get()));
        foodCookingRecipes(consumer, CHESTNUTS.get(), ROASTED_CHESTNUTS.get());
        foodCookingRecipes(consumer, GOAT.get(), COOKED_GOAT.get());
        this.conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GOAT_STEW.get()).requires(Items.BOWL).requires(COOKED_GOAT.get()).requires(Items.BAKED_POTATO).requires(Items.CARROT).requires(Items.BROWN_MUSHROOM).unlockedBy(getHasName(COOKED_GOAT.get()), has(COOKED_GOAT.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), consumer, getSaveLocation(GOAT_STEW.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MUTTON_PIE.get()).requires(WindsweptItemTags.COOKED_MUTTON).requires(Items.WHEAT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy(getHasName(Items.COOKED_MUTTON), has(WindsweptItemTags.COOKED_MUTTON)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GINGERBREAD_COOKIE.get(), 8).requires(GINGER_ROOT.get()).requires(Items.WHEAT, 2).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(consumer);

        // other items
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, WOODEN_BUCKET.get()).define('#', ItemTags.LOGS).pattern("# #").pattern(" # ").unlockedBy("has_log", has(ItemTags.LOGS)).save(consumer);
        this.conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SNOW_BOOTS.get()).define('#', Items.IRON_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)), new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location()), consumer, getSaveLocation(SNOW_BOOTS.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FROST_ARROW.get(), 6).define('#', FROZEN_BRANCH.get()).define('I', ICICLES.get()).define('S', Items.STICK).pattern("#").pattern("S").pattern("I").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(consumer);

        // blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GINGER_SOIL.get()).requires(Items.DIRT).requires(GINGER_ROOT.get()).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LUNALITE.get(), 8).define('#', Items.CALCITE).define('L', Items.LAPIS_LAZULI).pattern("###").pattern("#L#").pattern("###").unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(consumer);

        // Dyes
        conversionRecipe(consumer, Items.RED_DYE, RED_ROSE.get(), "red_dye");
        conversionRecipe(consumer, Items.BLUE_DYE, BLUE_ROSE.get(), "blue_dye");
        conversionRecipe(consumer, Items.WHITE_DYE, WHITE_ROSE.get(), "white_dye");
        conversionRecipe(consumer, Items.YELLOW_DYE, YELLOW_ROSE.get(), "yellow_dye");

        // Wood Sets
        this.woodSet(WindsweptItemTags.HOLLY_LOGS, HOLLY_PLANKS, HOLLY_SLAB, HOLLY_STAIRS, HOLLY_LOG, HOLLY_WOOD, STRIPPED_HOLLY_LOG, STRIPPED_HOLLY_WOOD, HOLLY_BOAT, HOLLY_BUTTON, HOLLY_DOOR, HOLLY_TRAPDOOR, HOLLY_FENCE, HOLLY_FENCE_GATE, HOLLY_PRESSURE_PLATE, HOLLY_SIGNS, HOLLY_BOARDS, HOLLY_BEEHIVE, HOLLY_LADDER, HOLLY_BOOKSHELF, HOLLY_CHEST, TRAPPED_HOLLY_CHEST, HOLLY_HANGING_SIGNS, CHISELED_HOLLY_BOOKSHELF, consumer);
    }

    private void woodSet(net.minecraft.tags.TagKey<net.minecraft.world.item.Item> logs, net.neoforged.neoforge.registries.DeferredBlock<Block> planks, net.neoforged.neoforge.registries.DeferredBlock<Block> slab, net.neoforged.neoforge.registries.DeferredBlock<Block> stairs, net.neoforged.neoforge.registries.DeferredBlock<Block> log, net.neoforged.neoforge.registries.DeferredBlock<Block> wood, net.neoforged.neoforge.registries.DeferredBlock<Block> strippedLog, net.neoforged.neoforge.registries.DeferredBlock<Block> strippedWood, com.mojang.datafixers.util.Pair<net.neoforged.neoforge.registries.DeferredItem<com.teamabnormals.blueprint.common.item.BlueprintBoatItem>, net.neoforged.neoforge.registries.DeferredItem<com.teamabnormals.blueprint.common.item.BlueprintBoatItem>> boats, net.neoforged.neoforge.registries.DeferredBlock<Block> button, net.neoforged.neoforge.registries.DeferredBlock<Block> door, net.neoforged.neoforge.registries.DeferredBlock<Block> trapdoor, net.neoforged.neoforge.registries.DeferredBlock<Block> fence, net.neoforged.neoforge.registries.DeferredBlock<Block> fenceGate, net.neoforged.neoforge.registries.DeferredBlock<Block> pressurePlate, com.mojang.datafixers.util.Pair<net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock>, net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock>> sign, net.neoforged.neoforge.registries.DeferredBlock<Block> boards, net.neoforged.neoforge.registries.DeferredBlock<Block> beehive, net.neoforged.neoforge.registries.DeferredBlock<Block> ladder, net.neoforged.neoforge.registries.DeferredBlock<Block> bookshelf, net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock> chest, net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock> trappedChest, com.mojang.datafixers.util.Pair<net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock>, net.neoforged.neoforge.registries.DeferredBlock<com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock>> hangingSign, net.neoforged.neoforge.registries.DeferredBlock<Block> chiseledBookshelf, RecipeOutput consumer) {
        planksFromLogs(consumer, planks.get(), logs, 4);
        woodFromLogs(consumer, wood.get(), log.get());
        woodFromLogs(consumer, strippedWood.get(), strippedLog.get());
        hangingSign(consumer, hangingSign.getFirst().get(), strippedLog.get());
        this.woodenBoat(consumer, boats.getFirst().get(), planks.get());
    }

    private void conditionalRecipe(RecipeBuilder recipe, ICondition condition, RecipeOutput consumer, ResourceLocation id) {
        consumer.withConditions(condition).accept(id, recipe.build(), null);
    }

    private ResourceLocation getSaveLocation(net.minecraft.world.level.ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}