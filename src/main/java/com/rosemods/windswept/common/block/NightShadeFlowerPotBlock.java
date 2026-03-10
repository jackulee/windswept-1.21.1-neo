package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NightShadeFlowerPotBlock extends FlowerPotBlock {
    public NightShadeFlowerPotBlock(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        NightshadeFlowerBlock.particles(level, pos, rand);
    }
}