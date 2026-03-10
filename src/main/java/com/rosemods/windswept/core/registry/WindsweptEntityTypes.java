package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.entity.Chilled;
import com.rosemods.windswept.common.entity.FrostArrow;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = Windswept.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WindsweptEntityTypes {
    public static final EntitySubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<Chilled>> CHILLED = HELPER.createLivingEntity("chilled", Chilled::new, MobCategory.MONSTER, .6f, 2f);
    public static final DeferredHolder<EntityType<?>, EntityType<Frostbiter>> FROSTBITER = HELPER.createLivingEntity("frostbiter", Frostbiter::new, MobCategory.CREATURE, 1.05f, 1.7f);
    public static final DeferredHolder<EntityType<?>, EntityType<FrostArrow>> FROST_ARROW = HELPER.createEntity("frost_arrow", FrostArrow::new, MobCategory.MISC, builder -> builder.sized(.5f, .5f));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CHILLED.get(), Chilled.createChilledAttributes().build());
        event.put(FROSTBITER.get(), Frostbiter.createFrostbiterAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawns(RegisterSpawnPlacementsEvent event) {
        event.register(CHILLED.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(FROSTBITER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Frostbiter::checkFrostbiterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }
}