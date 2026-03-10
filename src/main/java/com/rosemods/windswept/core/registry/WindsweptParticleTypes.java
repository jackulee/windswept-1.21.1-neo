package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.particle.AcaciaLeavesParticle;
import com.rosemods.windswept.client.particle.FrostLeafParticle;
import com.rosemods.windswept.client.particle.WillOTheWispParticle;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Windswept.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WindsweptParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Windswept.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WILL_O_THE_WISP = PARTICLE_TYPES.register("will_o_the_wisp", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FROST_LEAF = PARTICLE_TYPES.register("frost_leaf", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FEATHER_CLOAK = PARTICLE_TYPES.register("feather_cloak", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ACACIA_LEAVES = PARTICLE_TYPES.register("acacia_leaves", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WILL_O_THE_WISP.get(), WillOTheWispParticle.Provider::new);
        event.registerSpriteSet(FROST_LEAF.get(), FrostLeafParticle.Provider::new);
        event.registerSpriteSet(FEATHER_CLOAK.get(), EndRodParticle.Provider::new);
        event.registerSpriteSet(ACACIA_LEAVES.get(), AcaciaLeavesParticle.Provider::new);
    }
}