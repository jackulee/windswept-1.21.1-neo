package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class RedstoneFairyLightBlock extends PineconeBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public RedstoneFairyLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AMOUNT, 1).setValue(LIT, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        boolean lit = state.getValue(LIT);
        level.setBlock(pos, state.setValue(LIT, !lit), 3);
        level.playSound(player, pos, lit ? SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF : SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return direction == Direction.DOWN && state.getValue(LIT) ? Math.min(state.getValue(AMOUNT) * 4, 15) : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return state.getValue(LIT);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AMOUNT, LIT);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = (double)pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.8D;
            double d1 = (double)pos.getY() + 0.7D + (random.nextDouble() - 0.5D) * 0.3D;
            double d2 = (double)pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.8D;
            level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}