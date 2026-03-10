package com.rosemods.windswept.core.data.server.modifiers;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.WindsweptBiomes;
import com.teamabnormals.blueprint.common.world.modification.chunk.ChunkGeneratorModifierProvider;
import com.teamabnormals.blueprint.common.world.modification.chunk.modifiers.SurfaceRuleModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class WindsweptChunkGeneratorModifierProvider extends ChunkGeneratorModifierProvider {
    public WindsweptChunkGeneratorModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(Windswept.MOD_ID, output, provider);
    }

    @Override
    protected void registerEntries(HolderLookup.Provider provider) {
        SurfaceRules.ConditionSource inTundra = isBiome(WindsweptBiomes.TUNDRA);
        SurfaceRules.ConditionSource inFloweringSavanna = isBiome(WindsweptBiomes.FLOWERING_SAVANNA);
        RuleSource grassRule = sequence(ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), state(Blocks.GRASS_BLOCK.defaultBlockState()))));
        RuleSource snowRule = sequence(ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), state(Blocks.SNOW_BLOCK.defaultBlockState()))));
        RuleSource coarseDirtRule = sequence(ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), state(Blocks.COARSE_DIRT.defaultBlockState()))));

        this.entry("tundra_surface_rule").selects("minecraft:overworld").addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(inTundra, sequence(ifTrue(noiseRange(-2f, .5f), snowRule), sequence(ifTrue(noiseRange(1f, 1.5f), snowRule), grassRule)))), false));
        this.entry("flowering_savanna_surface_rule").selects("minecraft:overworld").addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(inFloweringSavanna, sequence(ifTrue(noiseRange(-2f, .5f), coarseDirtRule), sequence(ifTrue(noiseRange(1f, 1.5f), coarseDirtRule), grassRule)))), false));
    }

    private static ConditionSource noiseRange(float low, float high) {
        return noiseCondition(Noises.SURFACE, low / 8.25f, high / 8.25f);
    }

}