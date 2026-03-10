package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NightshadeFlowerBlock extends FlowerBlock {
    public NightshadeFlowerBlock(Holder<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        particles(level, pos, rand);
    }

    public static void particles(Level level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(8) == 0) {
            double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
            double d1 = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F);
            double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
            double d3 = (0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);

            level.addParticle(ParticleTypes.END_ROD, d0 + (double)Direction.UP.getStepX() * d3,
                    d1 + (double)Direction.UP.getStepY() * d3, d2 + (double)Direction.UP.getStepZ() * d3,
                    rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
        }
    }
}