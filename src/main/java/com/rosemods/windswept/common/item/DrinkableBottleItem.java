package com.rosemods.windswept.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class DrinkableBottleItem extends HoneyBottleItem {
    private final Supplier<SoundEvent> drinkSound;

    public DrinkableBottleItem(Supplier<SoundEvent> drinkSound, FoodProperties food) {
        super(new Properties().food(food).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
        this.drinkSound = drinkSound;
    }

    public DrinkableBottleItem(FoodProperties food) {
        this(() -> SoundEvents.GENERIC_DRINK, food);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        entity.eat(level, stack);
        if (entity instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return ItemUtils.createFilledResult(stack, (Player) entity, new ItemStack(Items.GLASS_BOTTLE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return this.drinkSound.get();
    }

    @Override
    public SoundEvent getEatingSound() {
        return this.drinkSound.get();
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }
}