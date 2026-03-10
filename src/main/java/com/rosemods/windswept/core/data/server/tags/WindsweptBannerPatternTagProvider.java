package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.core.registry.WindsweptBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class WindsweptBannerPatternTagProvider extends BannerPatternTagsProvider {

    public WindsweptBannerPatternTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Windswept.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(WindsweptBannerPatternTags.SNOW_CHARGE).add(WindsweptBannerPatterns.SNOW_CHARGE.key());
        this.tag(WindsweptBannerPatternTags.SNOW_GOLEM).add(WindsweptBannerPatterns.SNOW_GOLEM.key());
        this.tag(WindsweptBannerPatternTags.ROSE_FLOWER).add(WindsweptBannerPatterns.ROSE_FLOWER.key());
    }

}