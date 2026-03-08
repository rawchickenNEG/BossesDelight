package io.github.rcneg.bossesdelight.tier;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTier {
    public static final Tier VOID_THORN = new ForgeTier(2, 768, 12.0F, 2.0F, 15,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.DRAGON_BREATH));
    public static final Tier OBSIDIAN_HEART = new ForgeTier(3, 1024, 8.0F, 4.0F, 25,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.OBSIDIAN));
    public static final Tier GAUNTLET = new ForgeTier(3, 2048, 6.0F, 5.0F, 15,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.NETHERITE_INGOT));}