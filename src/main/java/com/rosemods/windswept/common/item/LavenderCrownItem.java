package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class LavenderCrownItem extends ArmorItem {
    private static final ResourceLocation FRAGRANCE_ID = Windswept.location("fragrance_modifier");

    public LavenderCrownItem(Properties properties) {
        super(WindsweptTiers.LAVENDER_CROWN, Type.HELMET, properties);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack)
                .withModifierAdded(WindsweptAttributes.FRAGRANCE,
                        new AttributeModifier(FRAGRANCE_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD);
    }
}