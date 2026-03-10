package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

public class SnowBootsItem extends ArmorItem implements DyeableLeatherItem {
    private static final ResourceLocation SNOW_SPEED_ID = Windswept.location("snow_speed_modifier");
    private static final ResourceLocation SPEED_BOOST_ID = Windswept.location("snow_speed_boost");

    public SnowBootsItem(Properties properties) {
        super(WindsweptTiers.SNOW_BOOTS, Type.BOOTS, properties);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack)
                .withModifierAdded(WindsweptAttributes.SNOW_SPEED,
                        new AttributeModifier(SNOW_SPEED_ID, 0.2D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        EquipmentSlotGroup.FEET);
    }

    public static boolean canApplySnowSpeed(LivingEntity entity) {
        BlockPos below = BlockPos.containing(entity.getX(), entity.getY() - 0.500001D, entity.getZ());
        return isSnowingAt(entity) || ((entity.level().getBlockState(below).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)
                || entity.level().getBlockState(below.above()).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)) && !entity.level().getBlockState(below).isAir());
    }

    private static boolean isSnowingAt(LivingEntity entity) {
        BlockPos pos = entity.blockPosition();
        return entity.level().isRaining() && entity.level().getBiome(pos).value().getPrecipitationAt(pos) == Biome.Precipitation.SNOW;
    }

    public static boolean canSpawnSnowParticle(LivingEntity entity) {
        return entity.tickCount % 5 == 0 && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D
                && !entity.isSpectator() && canApplySnowSpeed(entity)
                && (entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get()) || entity instanceof Frostbiter);
    }

    public static void spawnSnowParticle(LivingEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.level().addParticle(ParticleTypes.SNOWFLAKE,
                entity.getX() + (entity.level().random.nextDouble() - .5d) * (double) entity.getBbWidth(),
                entity.getY() + .1d,
                entity.getZ() + (entity.level().random.nextDouble() - .5d) * (double) entity.getBbWidth(), vec3.x * -.2d,
                .1d, vec3.z * -.2d);
    }

    public static void removeSnowSpeed(LivingEntity entity) {
        AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speed != null) speed.removeModifier(SPEED_BOOST_ID);
    }

    public static void tryAddSnowSpeed(LivingEntity entity) {
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get())) {
            AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            if (speed != null) {
                if (speed.getModifier(SPEED_BOOST_ID) == null)
                    speed.addTransientModifier(new AttributeModifier(SPEED_BOOST_ID, 0.2D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                if (entity.level().random.nextFloat() < .02f)
                    entity.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, entity, EquipmentSlot.FEET);
            }
        }
    }
}