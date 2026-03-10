package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.rosemods.windswept.core.other.WindsweptTiers;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

import java.util.List;

@EventBusSubscriber(modid = Windswept.MOD_ID, value = Dist.CLIENT)
public class FeatherCloakItem extends ArmorItem {
    public FeatherCloakItem(Properties properties) {
        super(WindsweptTiers.FEATHER_CLOAK, Type.CHESTPLATE, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

    public static void spawnFeatherCloakParticle(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel level)) return;
        for (int y = -9; y < 9; y++) {
            Vec3 angle = entity.calculateViewVector(0, y * 20f);
            for (int i = 0; i < 3; i++) {
                Vec3 vector = new Vec3(entity.getRandomX(.25f), entity.getRandomY(), entity.getRandomZ(.25f)).add(angle);
                level.sendParticles(WindsweptParticleTypes.FEATHER_CLOAK.get(), vector.x, vector.y, vector.z, 1, 0f, 0f, 0f, 0f);
            }
        }
    }

    @SubscribeEvent
    public static void livingRender(RenderLivingEvent.Pre<?, ?> event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof IDataManager data && data.getValue(WindsweptDataProcessors.CLOAKED))
            event.setCanceled(true);
    }
}