package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WindsweptBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, Windswept.MOD_ID);

    public static final DeferredHolder<BannerPattern, BannerPattern> SNOW_CHARGE = BANNER_PATTERNS.register("snow_charge", () -> new BannerPattern("wsc"));
    public static final DeferredHolder<BannerPattern, BannerPattern> SNOW_GOLEM = BANNER_PATTERNS.register("snow_golem", () -> new BannerPattern("wsg"));
    public static final DeferredHolder<BannerPattern, BannerPattern> ROSE_FLOWER = BANNER_PATTERNS.register("rose_flower", () -> new BannerPattern("wrf"));
}