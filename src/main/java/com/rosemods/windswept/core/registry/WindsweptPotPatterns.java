package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptPotPatterns {
    public static final DeferredRegister<String> DECORATED_POT_PATTERNS = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, Windswept.MOD_ID);

    public static final DeferredHolder<String, String> HOOT = register("hoot");
    public static final DeferredHolder<String, String> PLUMAGE = register("plumage");
    public static final DeferredHolder<String, String> OFFSHOOT = register("offshoot");
    public static final DeferredHolder<String, String> FLAKE = register("flake");
    public static final DeferredHolder<String, String> DRUPES = register("drupes");

    private static DeferredHolder<String, String> register(String name) {
        String register = name + "_pottery_pattern";
        return DECORATED_POT_PATTERNS.register(register, () -> register);
    }

    public static void registerPatterns() {
        DataUtil.registerDecoratedPotPattern(
                Pair.of(WindsweptItems.HOOT_POTTERY_SHERD.get(), HOOT),
                Pair.of(WindsweptItems.PLUMAGE_POTTERY_SHERD.get(), PLUMAGE),
                Pair.of(WindsweptItems.OFFSHOOT_POTTERY_SHERD.get(), OFFSHOOT),
                Pair.of(WindsweptItems.FLAKE_POTTERY_SHERD.get(), FLAKE),
                Pair.of(WindsweptItems.DRUPES_POTTERY_SHERD.get(), DRUPES)
        );
    }
}