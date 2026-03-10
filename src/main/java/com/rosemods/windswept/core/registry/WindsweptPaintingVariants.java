package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptPaintingVariants {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(Registries.PAINTING_VARIANT, Windswept.MOD_ID);

    public static final DeferredHolder<PaintingVariant, PaintingVariant> CLIFFSIDE = register("cliffside", 3, 2);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> DRESS_CODES = register("dress_codes", 2, 2);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> ECOTONAL_PAREIDOLIA = register("ecotonal_pareidolia", 4, 2);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> THE_FOILS = register("the_foils", 3, 4);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> AURORAE = register("aurorae", 2, 1);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> HEARTH_RUG = register("hearth_rug", 1, 4);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> ALLU_PINE = register("al-lu_pine", 2, 3);

    private static DeferredHolder<PaintingVariant, PaintingVariant> register(String name, int width, int height) {
        return PAINTING_VARIANTS.register(name, () -> new PaintingVariant(width * 16, height * 16));
    }
}