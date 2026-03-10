package com.rosemods.windswept.core.data.server.modifiers;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WindsweptAdvancementModifierProvider extends AdvancementModifierProvider {

    public WindsweptAdvancementModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(Windswept.MOD_ID, output, provider);
    }

    @Override
    protected void registerEntries(HolderLookup.Provider provider) {
        final List<Block> seedsBlocks = List.of(WindsweptBlocks.WILD_BERRY_BUSH.get());
        final List<EntityType<?>> killedMobs = List.of(WindsweptEntityTypes.CHILLED.get());

        this.entry("balanced_diet").selects("husbandry/balanced_diet").addModifier(this.food().requirements(Strategy.AND).build());
        this.entry("all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, this.effects()));
        this.entry("plant_seed").selects("husbandry/plant_seed").addModifier(this.seedsBlocks(seedsBlocks).addIndexedRequirements(0, false, this.getNames(seedsBlocks)).build());
        this.entry("kill_a_mob").selects("adventure/kill_a_mob").addModifier(this.killedMobs(killedMobs).addIndexedRequirements(0, false, this.getNamesEntity(killedMobs)).build());
        this.entry("kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(this.killedMobs(killedMobs).requirements(Strategy.AND).build());
        this.entry("walk_on_powder_snow_with_leather_boots").selects("adventure/walk_on_powder_snow_with_leather_boots").addModifier(this.snowBoots().addIndexedRequirements(0, false, "snow_boots").build());
    }

    private CriteriaModifier.Builder builder() {
        return CriteriaModifier.builder(this.modId);
    }

    private String[] getNames(List<Block> listIn) {
        return listIn.stream().map(b -> BuiltInRegistries.BLOCK.getKey(b).getPath()).toArray(String[]::new);
    }

    private String[] getNamesEntity(List<EntityType<?>> listIn) {
        return listIn.stream().map(e -> BuiltInRegistries.ENTITY_TYPE.getKey(e).getPath()).toArray(String[]::new);
    }

    private CriteriaModifier.Builder food() {
        CriteriaModifier.Builder food = this.builder();
        WindsweptItems.HELPER.getDeferredRegister().getEntries().forEach(i -> {
            if (i.get().getFoodProperties(null) != null)
                food.addCriterion(i.getId().getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(i.get()));
        });
        return food;
    }

    private MobEffectsPredicate effects() {
        MobEffectsPredicate.Builder effects = MobEffectsPredicate.Builder.effects();
        WindsweptEffects.HELPER.getDeferredRegister().getEntries().forEach(e -> effects.and(e.get()));
        return effects.build().get();
    }

    private CriteriaModifier.Builder seedsBlocks(List<Block> seedsBlocksIn) {
        CriteriaModifier.Builder seedsBlocks = this.builder();
        seedsBlocksIn.forEach(s -> seedsBlocks.addCriterion(BuiltInRegistries.BLOCK.getKey(s).getPath(), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(s)));
        return seedsBlocks;
    }

    private CriteriaModifier.Builder killedMobs(List<EntityType<?>> killedMobsIn) {
        CriteriaModifier.Builder killedMobs = this.builder();
        killedMobsIn.forEach(e -> killedMobs.addCriterion(BuiltInRegistries.ENTITY_TYPE.getKey(e).getPath(), KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(e))));
        return killedMobs;
    }

    private CriteriaModifier.Builder snowBoots() {
        return this.builder().addCriterion("snow_boots", PlayerTrigger.TriggerInstance.walkOnBlockWithEquipment(Blocks.POWDER_SNOW, WindsweptItems.SNOW_BOOTS.get()));
    }

}