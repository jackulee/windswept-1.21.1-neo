package com.rosemods.windswept.common.block;

import com.mojang.serialization.MapCodec;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class PineconeBlockBlock extends RotatedPillarBlock {
    public static final MapCodec<PineconeBlockBlock> CODEC = simpleCodec(PineconeBlockBlock::new);

    public PineconeBlockBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends RotatedPillarBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (stack.canPerformAction(ItemAbilities.SHEARS_CARVE)) {
            if (!level.isClientSide) {
                Direction direction = result.getDirection().getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : result.getDirection();

                level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, WindsweptBlocks.CARVED_PINECONE_BLOCK.get().defaultBlockState().setValue(CarvedPineconeBlock.FACING, direction), 11);

                if (player instanceof ServerPlayer serverPlayer) {
                    stack.hurtAndBreak(1, serverPlayer, player.getEquipmentSlotForItem(stack));
                }

                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}