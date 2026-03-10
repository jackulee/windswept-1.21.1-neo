package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.registry.BlueprintHolderSets;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.datapack.WindsweptStructures.*;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public final class WindsweptStructureRepaletters {

    public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        register(context, structures, Blocks.SNOW_BLOCK, SNOW_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.OAK_WALL_SIGN, HOLLY_SIGNS.getSecond().get(), IGLOO);
        register(context, structures, Blocks.POTTED_CACTUS, POTTED_WHITE_ROSE.get(), IGLOO);
        register(context, structures, Blocks.SPRUCE_SLAB, HOLLY_SLAB.get(), IGLOO);
        register(context, structures, Blocks.SPRUCE_STAIRS, HOLLY_STAIRS.get(), IGLOO);
        register(context, structures, Blocks.MOSSY_STONE_BRICKS, CHISELED_ICICLE_BLOCK.get(), IGLOO);
        register(context, structures, Blocks.IRON_BARS, ICICLE_BARS.get(), IGLOO);
        register(context, structures, Blocks.STONE_BRICKS, PACKED_ICE_BRICKS.get(), IGLOO);
        register(context, structures, Blocks.REDSTONE_TORCH, ICE_LANTERN.get(), IGLOO);

        register(context, structures, Blocks.POTTED_SPRUCE_SAPLING, POTTED_BLUEBELLS.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.POPPY, RED_ROSE.get(), VILLAGE_TAIGA);
        register(context, structures, Blocks.ACACIA_SAPLING, MIMOSA.get(), VILLAGE_SAVANNA);
        register(context, structures, Blocks.POPPY, YELLOW_PETALS.get(), VILLAGE_SAVANNA);

        register(context, structures, Blocks.SPRUCE_PLANKS, CHESTNUT_PLANKS.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_STAIRS, CHESTNUT_STAIRS.get(), VILLAGE_SNOWY);
        register(context, structures, Blocks.SPRUCE_DOOR, CHESTNUT_DOOR.get(), VILLAGE_SNOWY);

        register(context, structures, Blocks.JUNGLE_LOG, PINE_LOG.get(), SHIPWRECK, SHIPWRECK_BEACHED);
        register(context, structures, Blocks.JUNGLE_PLANKS, PINE_PLANKS.get(), SHIPWRECK, SHIPWRECK_BEACHED);

        ICondition woodworks = new ModLoadedCondition("woodworks");
        register(context, structures, Blocks.CHEST, HOLLY_CHEST.get(), woodworks, IGLOO);
        register(context, structures, Blocks.LADDER, HOLLY_LADDER.get(), woodworks, IGLOO);
        register(context, structures, Blocks.CHEST, CHESTNUT_CHEST.get(), woodworks, VILLAGE_SNOWY);
        register(context, structures, Blocks.BOOKSHELF, CHESTNUT_BOOKSHELF.get(), woodworks, VILLAGE_SNOWY);
    }

    @SafeVarargs
    private static void register(BootstrapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, Block replacesBlock, Block replacesWith, ICondition condition, ResourceKey<Structure>... selector) {
        register(context, getName(replacesBlock, replacesWith, selector), replacesBlock, replacesWith, BlueprintHolderSets.conditional(HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())), condition));
    }

    @SafeVarargs
    private static void register(BootstrapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
        register(context, getName(replacesBlock, replacesWith, selector), replacesBlock, replacesWith, HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())));
    }

    private static void register(BootstrapContext<StructureRepaletterEntry> context, String name, Block replacesBlock, Block replacesWith, HolderSet<Structure> structures) {
        context.register(createKey(name), new StructureRepaletterEntry(structures, Optional.empty(), false, new SimpleStructureRepaletter(replacesBlock, replacesWith)));
    }

    @SafeVarargs
    private static String getName(Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
        return selector[0].location().getPath() + "/" + BuiltInRegistries.BLOCK.getKey(replacesWith).getPath() + "_replaces_" + BuiltInRegistries.BLOCK.getKey(replacesBlock).getPath();
    }

    private static ResourceKey<StructureRepaletterEntry> createKey(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Windswept.location(name));
    }
}