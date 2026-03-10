package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.rosemods.windswept.core.registry.WindsweptPaintingVariants.*;

public class WindsweptPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public WindsweptPaintingVariantTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Windswept.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(CLIFFSIDE.key(), DRESS_CODES.key(),
                ECOTONAL_PAREIDOLIA.key(), THE_FOILS.key(), AURORAE.key(), HEARTH_RUG.key(), ALLU_PINE.key());
    }

}