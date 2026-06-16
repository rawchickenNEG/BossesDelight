package io.github.rcneg.bossesdelight.integration.jei;

import io.github.rcneg.bossesdelight.init.RecipeTypesRegistry;
import io.github.rcneg.bossesdelight.recipes.SoulCookingPotRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.List;

public class BDRecipes {
    private final RecipeManager recipeManager;

    public BDRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level != null) {
            this.recipeManager = level.getRecipeManager();
        } else {
            throw new NullPointerException("minecraft world must not be null.");
        }
    }

    public List<SoulCookingPotRecipe> getSoulCookingPotRecipes() {
        return this.recipeManager.getAllRecipesFor((RecipeType) RecipeTypesRegistry.SOUL_COOKING.get()).stream().toList();
    }
}