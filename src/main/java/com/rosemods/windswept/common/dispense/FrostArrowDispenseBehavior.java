package com.rosemods.windswept.common.dispense;

import com.rosemods.windswept.common.entity.FrostArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class FrostArrowDispenseBehavior extends DefaultDispenseItemBehavior {

    @Override
    public ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.level();
        Position position = DispenserBlock.getDispensePosition(source);
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        FrostArrow frostArrow = new FrostArrow(level, position.x(), position.y(), position.z(), stack.copyWithCount(1), null);
        frostArrow.shoot(direction.getStepX(), (float)direction.getStepY() + 0.1F, direction.getStepZ(), 1.1F, 6.0F);
        frostArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        level.addFreshEntity(frostArrow);

        stack.shrink(1);
        return stack;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1002, source.pos(), 0);
    }

}