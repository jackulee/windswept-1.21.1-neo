package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.rosemods.windswept.core.registry.WindsweptEntityTypes.*;

public class WindsweptEntityTagProvider extends EntityTypeTagsProvider {

    public WindsweptEntityTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Windswept.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(WindsweptEntityTypeTags.CONVERT_TO_CHILLED).add(EntityType.ZOMBIE, EntityType.HUSK, EntityType.DROWNED);
        this.tag(BlueprintEntityTypeTags.MILKABLE).add(FROSTBITER.get());
        this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(CHILLED.get(), FROSTBITER.get(), EntityType.GOAT);
        this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(CHILLED.get(), FROSTBITER.get());
        this.tag(EntityTypeTags.ARROWS).add(FROST_ARROW.get());
    }

}