package com.rosemods.windswept.common.item;

import com.rosemods.windswept.client.model.AntlerHelmetModel;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AntlerHelmetItem extends ArmorItem {
    private static final ResourceLocation SPRINT_DAMAGE_ID = Windswept.location("sprint_damage_modifier");
    private static final ResourceLocation ATTACK_DAMAGE_ID = Windswept.location("sprint_damage_boost");

    public AntlerHelmetItem(Properties properties) {
        super(WindsweptTiers.ANTLER_HELMET, Type.HELMET, properties);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack)
                .withModifierAdded(WindsweptAttributes.SPRINT_DAMAGE,
                        new AttributeModifier(SPRINT_DAMAGE_ID, 4.0D, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                return AntlerHelmetModel.INSTANCE;
            }
        });
    }

    public static void removeSprintDamage(LivingEntity entity) {
        AttributeInstance damage = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (damage != null) damage.removeModifier(ATTACK_DAMAGE_ID);
    }

    public static void tryAddSprintDamage(LivingEntity entity) {
        if (entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptItems.ANTLER_HELMET.get()) && entity.isSprinting()) {
            AttributeInstance damage = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (damage != null && damage.getModifier(ATTACK_DAMAGE_ID) == null)
                damage.addTransientModifier(new AttributeModifier(ATTACK_DAMAGE_ID, 4.0D, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}