package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.client.screen.splash.SplashProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WindsweptSplashProvider extends SplashProvider {
    public WindsweptSplashProvider(PackOutput output, ExistingFileHelper helper) {
        super(Windswept.MOD_ID, output);
    }

    @Override
    protected void registerSplashes() {
        this.add("#WINDSWEEP");
    }
}