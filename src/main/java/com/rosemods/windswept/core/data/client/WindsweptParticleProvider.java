package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.rosemods.windswept.core.registry.WindsweptParticleTypes.*;

public class WindsweptParticleProvider extends ParticleProvider {
    public WindsweptParticleProvider(PackOutput output, net.neoforged.neoforge.common.data.ExistingFileHelper helper) {
        super(output, Windswept.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(WILL_O_THE_WISP.get(), "will_o_the_wisp_0", "will_o_the_wisp_1", "will_o_the_wisp_2");
        this.add(FROST_LEAF.get(), "frost_leaf_0", "frost_leaf_1", "frost_leaf_2");
        this.add(FEATHER_CLOAK.get(), "feather_cloak_0", "feather_cloak_1", "feather_cloak_2");
        this.add(ACACIA_LEAVES.get(), "acacia_leaves_0", "acacia_leaves_1", "acacia_leaves_2");
    }
}