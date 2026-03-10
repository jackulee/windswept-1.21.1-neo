package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.item.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.WindsweptFoods;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.integration.boatload.WindsweptBoatTypes;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

public class WindsweptItems {
    public static final ItemSubRegistryHelper HELPER = Windswept.REGISTRY_HELPER.getItemSubHelper();

    // Misc //
    public static final DeferredItem<Item> HOLLY_BERRIES = HELPER.createItem("holly_berries", () -> new Item(new Item.Properties().food(WindsweptFoods.HOLLY_BERRIES)));
    public static final DeferredItem<Item> HOLLY_BERRIES_ON_A_STICK = HELPER.createItem("holly_berries_on_a_stick", () -> new HollyBerriesOnAStickItem(new Item.Properties().durability(25)));
    public static final DeferredItem<Item> ELDER_FEATHER = HELPER.createItem("elder_feather", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FROST_ARROW = HELPER.createItem("frost_arrow", () -> new FrostArrowItem(new Item.Properties()));
    public static final DeferredItem<Item> FROZEN_BRANCH = HELPER.createItem("frozen_branch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FROZEN_FLESH = HELPER.createItem("frozen_flesh", () -> new Item(PropertyUtil.food(WindsweptFoods.FROZEN_FLESH)));

    // Armour //
    public static final DeferredItem<Item> LAVENDER_CROWN = HELPER.createItem("lavender_crown", () -> new LavenderCrownItem(new Item.Properties()));
    public static final DeferredItem<Item> ANTLER_HELMET = HELPER.createItem("antler_helmet", () -> new AntlerHelmetItem(new Item.Properties()));
    public static final DeferredItem<Item> FEATHER_CLOAK = HELPER.createItem("feather_cloak", () -> new FeatherCloakItem(new Item.Properties()));
    public static final DeferredItem<Item> SNOW_BOOTS = HELPER.createItem("snow_boots", () -> new SnowBootsItem(new Item.Properties()));

    // Wooden Buckets //
    public static final DeferredItem<Item> WOODEN_BUCKET = HELPER.createItem("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, new Item.Properties().durability(24)));
    public static final DeferredItem<Item> WOODEN_WATER_BUCKET = HELPER.createItem("wooden_water_bucket", () -> new WoodenBucketItem(() -> Fluids.WATER, new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_MILK_BUCKET = HELPER.createItem("wooden_milk_bucket", () -> new WoodenMilkBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_POWDER_SNOW_BUCKET = HELPER.createItem("wooden_powder_snow_bucket", () -> new WoodenPowderSnowBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_HONEY_BUCKET = HELPER.createItem("wooden_honey_bucket", () -> new WoodenBucketItem(() -> BuiltInRegistries.FLUID.get(WindsweptConstants.HONEY), new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_CHOCOLATE_BUCKET = HELPER.createItem("wooden_chocolate_bucket", () -> new WoodenBucketItem(() -> BuiltInRegistries.FLUID.get(WindsweptConstants.CHOCOLATE), new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));

    // Food //
    public static final DeferredItem<Item> WILD_BERRIES = HELPER.createItem("wild_berries", ModList.get().isLoaded("berry_good") ? () -> new Item(PropertyUtil.food(WindsweptFoods.WILD_BERRIES)) : () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), PropertyUtil.food(WindsweptFoods.WILD_BERRIES)));
    public static final DeferredItem<Item> WILD_BERRY_PIPS = HELPER.createItem("wild_berry_pips", ModList.get().isLoaded("berry_good") ? () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), new Item.Properties()) : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CANDY_CANE = HELPER.createItem("candy_cane", () -> new Item(PropertyUtil.food(WindsweptFoods.CANDY_CANE)));
    public static final DeferredItem<Item> CHESTNUTS = HELPER.createItem("chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.CHESTNUTS)));
    public static final DeferredItem<Item> ROASTED_CHESTNUTS = HELPER.createItem("roasted_chestnuts", () -> new Item(PropertyUtil.food(WindsweptFoods.ROASTED_CHESTNUTS)));
    public static final DeferredItem<Item> CHESTNUT_SOUP = HELPER.createItem("chestnut_soup", () -> new BowlFoodItem(PropertyUtil.food(WindsweptFoods.CHESTNUT_SOUP).usingConversions(Items.BOWL).stacksTo(1)));
    public static final DeferredItem<Item> GINGER_ROOT = HELPER.createItem("ginger_root", () -> new ItemNameBlockItem(WindsweptBlocks.GINGER.get(), PropertyUtil.food(WindsweptFoods.GINGER_ROOT)));
    public static final DeferredItem<Item> GINGERBREAD_COOKIE = HELPER.createItem("gingerbread_cookie", () -> new Item(PropertyUtil.food(WindsweptFoods.GINGERBREAD_COOKIE)));
    public static final DeferredItem<Item> GINGER_TEA = HELPER.createItem("ginger_tea", () -> new DrinkableBottleItem(WindsweptFoods.GINGER_TEA));
    public static final DeferredItem<Item> SPICY_SNOW_CONE = HELPER.createItem("spicy_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), PropertyUtil.food(WindsweptFoods.SPICY_SNOW_CONE)));
    public static final DeferredItem<Item> SWEET_SNOW_CONE = HELPER.createItem("sweet_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), PropertyUtil.food(WindsweptFoods.SWEET_SNOW_CONE)));
    public static final DeferredItem<Item> MINTY_SNOW_CONE = HELPER.createItem("minty_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), PropertyUtil.food(WindsweptFoods.MINY_SNOW_CONE)));
    public static final DeferredItem<Item> PINECONE_JAM_BOTTLE = HELPER.createItem("pinecone_jam_bottle", () -> new DrinkableBottleItem(() -> SoundEvents.HONEY_DRINK, WindsweptFoods.PINECONE_JAM));
    public static final DeferredItem<Item> LAVENDER_TEA = HELPER.createItem("lavender_tea", () -> new DrinkableBottleItem(WindsweptFoods.LAVENDER_TEA));
    public static final DeferredItem<Item> GOAT = HELPER.createItem("goat", () -> new Item(PropertyUtil.food(WindsweptFoods.GOAT)));
    public static final DeferredItem<Item> COOKED_GOAT = HELPER.createItem("cooked_goat", () -> new Item(PropertyUtil.food(WindsweptFoods.COOKED_GOAT)));
    public static final DeferredItem<Item> GOAT_STEW = HELPER.createItem("goat_stew", () -> new BowlFoodItem(PropertyUtil.food(WindsweptFoods.GOAT_STEW).usingConversions(Items.BOWL).stacksTo(1)));
    public static final DeferredItem<Item> MUTTON_PIE = HELPER.createItem("mutton_pie", () -> new Item(PropertyUtil.food(WindsweptFoods.MUTTON_PIE)));

    // Pottery Sherds //
    public static final DeferredItem<Item> HOOT_POTTERY_SHERD = HELPER.createItem("hoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLUMAGE_POTTERY_SHERD = HELPER.createItem("plumage_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OFFSHOOT_POTTERY_SHERD = HELPER.createItem("offshoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAKE_POTTERY_SHERD = HELPER.createItem("flake_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DRUPES_POTTERY_SHERD = HELPER.createItem("drupes_pottery_sherd", () -> new Item(new Item.Properties()));

    // Banner Patterns //
    public static final DeferredItem<Item> SNOW_GOLEM_BANNER_PATTERN = HELPER.createItem("snow_golem_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_GOLEM, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SNOW_CHARGE_BANNER_PATTERN = HELPER.createItem("snow_charge_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_CHARGE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ROSE_FLOWER_BANNER_PATTERN = HELPER.createItem("rose_flower_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.ROSE_FLOWER, new Item.Properties().stacksTo(1)));

    // Music Discs //
    public static final DeferredItem<Item> MUSIC_DISC_RAIN = HELPER.createItem("music_disc_rain", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_RAIN, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_SNOW = HELPER.createItem("music_disc_snow", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_SNOW, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MUSIC_DISC_BUMBLEBEE = HELPER.createItem("music_disc_bumblebee", () -> new BlueprintRecordItem(2, WindsweptSounds.MUSIC_DISC_BUMBLEBEE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    // Spawn Eggs //
    public static final DeferredItem<DeferredSpawnEggItem> CHILLED_SPAWN_EGG = HELPER.createSpawnEggItem("chilled", WindsweptEntityTypes.CHILLED::get, 0x9e9cbe, 0x98654a);
    public static final DeferredItem<DeferredSpawnEggItem> FROSTBITER_SPAWN_EGG = HELPER.createSpawnEggItem("frostbiter", WindsweptEntityTypes.FROSTBITER::get, 0xe2e2e2, 0x8fb5ff);

    // Boats //
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> HOLLY_BOAT = HELPER.createBoatAndChestBoatItem("holly", WindsweptBlocks.HOLLY_PLANKS);
    public static final DeferredItem<Item> HOLLY_FURNACE_BOAT = HELPER.createItem("holly_furnace_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.HOLLY_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_HOLLY_BOAT = HELPER.createItem("large_holly_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.LARGE_HOLLY_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> CHESTNUT_BOAT = HELPER.createBoatAndChestBoatItem("chestnut", WindsweptBlocks.CHESTNUT_PLANKS);
    public static final DeferredItem<Item> CHESTNUT_FURNACE_BOAT = HELPER.createItem("chestnut_furnace_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.CHESTNUT_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_CHESTNUT_BOAT = HELPER.createItem("large_chestnut_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.LARGE_CHESTNUT_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> PINE_BOAT = HELPER.createBoatAndChestBoatItem("pine", WindsweptBlocks.PINE_PLANKS);
    public static final DeferredItem<Item> PINE_FURNACE_BOAT = HELPER.createItem("pine_furnace_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.PINE_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_PINE_BOAT = HELPER.createItem("large_pine_boat", ModList.get().isLoaded("boatload") ? WindsweptBoatTypes.LARGE_PINE_BOAT : () -> new Item(new Item.Properties()));
}