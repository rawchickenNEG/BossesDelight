package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.items.*;
import io.github.rcneg.bossesdelight.tier.ItemTier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.CookingPotItem;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BossesDelight.MODID);


    public static final RegistryObject<Item> OBSIDIAN_HEART_KNIFE = ITEMS.register("obsidian_heart_knife", () -> new ObsidianHeartKnifeItem(ItemTier.OBSIDIAN_HEART, 2.0F, -2.0F, defaultBuilder()));
    public static final RegistryObject<Item> VOID_THORN_KNIFE = ITEMS.register("void_thorn_knife", () -> new VoidThornKnifeItem(ItemTier.VOID_THORN, 2.0F, -2.0F, defaultBuilder()));
    public static final RegistryObject<Item> GAUNTLET = ITEMS.register("gauntlet", () -> new Gauntlet(ItemTier.GAUNTLET, 2.0F, -2.0F, defaultBuilder()));
    public static final RegistryObject<Item> SOUL_COOKING_POT_ITEM = ITEMS.register("soul_cooking_pot", () -> {
        return new SoulCookingPotItem(BlockRegistry.SOUL_COOKING_POT.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BROKEN_OBSIDIAN_HEART = ITEMS.register("broken_obsidian_heart", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(4).saturationMod(1.0f)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 0), 0.8F)
                    .build()), true));
    public static final RegistryObject<Item> OBSIDIAN_SAUCE = ITEMS.register("obsidian_sauce", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.8f).build())));
    public static final RegistryObject<Item> OBSIDIAN_ONION = ITEMS.register("obsidian_onion", () -> new TippedBlockItems(BlockRegistry.OBSIDIAN_ONION_BLOCK.get(),defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.8f)
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0), 0.3F)
                    .build())));
    public static final RegistryObject<Item> OBSIDIAN_RUNE_PUREE = ITEMS.register("obsidian_rune_puree", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(10).saturationMod(1.2f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.COMFORT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> OBSIDIAN_RUNE_COCKTAIL = ITEMS.register("obsidian_rune_cocktail", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(1.0f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.LAST_STAND.get(), 1200, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> OBSIDIAN_GLAZED_DRAGON_HEAD = ITEMS.register("obsidian_glazed_dragon_head", () -> {
        return new BlockItem(BlockRegistry.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> OBSIDIAN_GLAZED_DRAGON_HEAD_LEGACY = ITEMS.register("obsidian_glazed_dragon_head_block", () -> {
        return new BlockItem(BlockRegistry.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK_LEGACY.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD = ITEMS.register("bowl_of_obsidian_glazed_dragon_head", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(16).saturationMod(1.1f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.LAST_STAND.get(), 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 6000, 1), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> OBSIDIAN_GLAZED_DRAGON_TONGUE = ITEMS.register("obsidian_glazed_dragon_tongue", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(16).saturationMod(1.1f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.LAST_STAND.get(), 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 4), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> BOWL_OF_OBSIDIAN_GLAZED_DRAGON_BRAIN = ITEMS.register("bowl_of_obsidian_glazed_dragon_brain", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(16).saturationMod(1.1f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.LAST_STAND.get(), 3600, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 9600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 1), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> CRYSTAL_FRUIT_SLICE = ITEMS.register("crystal_fruit_slice", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(2).saturationMod(2.0f)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> CRYSTAL_FRUIT_SEEDS = ITEMS.register("crystal_fruit_seeds", () -> new TippedBlockItems(BlockRegistry.CRYSTAL_FRUIT_BLOCK.get(),defaultBuilder()));
    public static final RegistryObject<Item> CRYSTAL_CUP_CAKE = ITEMS.register("crystal_cup_cake", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(8).saturationMod(2.0f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 3), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> BLOSSOM_SALAD = ITEMS.register("blossom_salad", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(2.0f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> CRYSTAL_FRUIT_CUBE = ITEMS.register("crystal_fruit_cube_block", () -> {
        return new BlockItem(BlockRegistry.CRYSTAL_FRUIT_CUBE_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BOWL_OF_CRYSTAL_FRUIT_CUBE = ITEMS.register("bowl_of_crystal_fruit_cube", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(12).saturationMod(1.2f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 3), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> CRYSTAL_FRUIT_CIDER = ITEMS.register("crystal_fruit_cider", () -> new CrystalFruitCiderItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    public static final RegistryObject<Item> BLAZING_EYE_SHARDS = ITEMS.register("blazing_eye_shards", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(4).saturationMod(1.0f)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.GAUNTLET_PROTECTION.get(), 600, 0), 0.2F)
                    .build()), true));
    public static final RegistryObject<Item> ANCIENT_ROLL = ITEMS.register("ancient_roll", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).fast().nutrition(6).saturationMod(1.2f)
                    .effect(new MobEffectInstance(EffectRegistry.BREAKDOWN.get(), 3600, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> BLAZING_EYE_PIE = ITEMS.register("blazing_eye_pie_block", () -> {
        return new BlockItem(BlockRegistry.BLAZING_EYE_PIE_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BLAZING_EYE_PIE_SLICE = ITEMS.register("blazing_eye_pie_slice", () -> new ConsumableItem(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(1.0f)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.GAUNTLET_PROTECTION.get(), 600, 0), 0.6F)
                    .build()), true));
    public static final RegistryObject<Item> HAM_ABOVE_PALM = ITEMS.register("ham_above_palm_block", () -> {
        return new BlockItem(BlockRegistry.HAM_ABOVE_PALM_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BOWL_OF_HAM_ABOVE_PALM = ITEMS.register("bowl_of_ham_above_palm", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(14).saturationMod(1.2f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.GAUNTLET_PROTECTION.get(), 3600, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> GLARE_STOUT = ITEMS.register("glare_stout", () -> new GlareStoutItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    public static final RegistryObject<Item> ANIMA_ICE_DRINK = ITEMS.register("anima_ice_drink", () -> new AnimaIceDrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> ANIMA_POPSICLE = ITEMS.register("anima_popsicle", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(8).saturationMod(1.1f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 3600, 0), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.INTANGIBLE.get(), 1200, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> LICH_SMOOTHIES = ITEMS.register("lich_smoothies_block", () -> {
        return new BlockItem(BlockRegistry.LICH_SMOOTHIES_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BOWL_OF_LICH_SMOOTHIES = ITEMS.register("bowl_of_lich_smoothies", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(12).saturationMod(1.2f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.INTANGIBLE.get(), 1200, 1), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> MAGIC_FROZEN_NOODLES = ITEMS.register("magic_frozen_noodles_block", () -> {
        return new BlockItem(BlockRegistry.MAGIC_FROZEN_NOODLES_BLOCK.get(), defaultBuilder().stacksTo(1));
    });
    public static final RegistryObject<Item> BOWL_OF_MAGIC_FROZEN_NOODLES = ITEMS.register("bowl_of_magic_frozen_noodles", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(14).saturationMod(1.2f).alwaysEat()
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.JUMP, 6000, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 6000, 0), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.INTANGIBLE.get(), 3600, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> BOSSES_HODGEPODGE = ITEMS.register("bosses_hodgepodge", () -> new ConsumableItem(bowlFoodBuilder()
            .food((new FoodProperties.Builder()).nutrition(20).saturationMod(1.0f).alwaysEat()
                    .effect(new MobEffectInstance(EffectRegistry.INTANGIBLE.get(), 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.GAUNTLET_PROTECTION.get(), 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(EffectRegistry.LAST_STAND.get(), 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
                    .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F)
                    .build()), true));
    public static final RegistryObject<Item> BRIMSTONE_NECTAR_TEA = ITEMS.register("brimstone_nectar_tea", () -> new BrimstoneNectarTeaItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> NECTAR_JELLY = ITEMS.register("nectar_jelly", () -> new NectarJelly(defaultBuilder()
            .food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.8f).build())));
    //public static final RegistryObject<Item> BOSSES_LIQUOR = ITEMS.register("bosses_liquor", () -> new BrimstoneNectarTeaItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    private static Item.Properties defaultBuilder() {
        return new Item.Properties();
    }

    private static Item.Properties bowlFoodBuilder() {
        return new Item.Properties().craftRemainder(Items.BOWL).stacksTo(16);
    }
}
