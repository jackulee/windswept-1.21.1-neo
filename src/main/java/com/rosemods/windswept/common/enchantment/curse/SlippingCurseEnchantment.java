package com.rosemods.windswept.common.enchantment.curse;

import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;

public class SlippingCurseEnchantment {

    public static float getFriction(Entity entity, float friction) {
        if (entity instanceof LivingEntity livingEntity && Blocks.ICE.getFriction() > friction && hasSlipping(livingEntity)) {
            return Blocks.ICE.getFriction();
        }
        return friction;
    }

    public static void attemptDamageBoots(LivingEntity entity) {
        if (hasSlipping(entity) && entity.level().getRandom().nextFloat() < .02f) {
            ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
            if (!boots.isEmpty()) {
                boots.hurtAndBreak(1, entity, EquipmentSlot.FEET);
            }
        }
    }

    public static boolean hasSlipping(LivingEntity entity) {
        return EnchantmentHelper.getEnchantmentLevel(WindsweptEnchantments.SLIPPING_CURSE, entity) > 0;
    }

}