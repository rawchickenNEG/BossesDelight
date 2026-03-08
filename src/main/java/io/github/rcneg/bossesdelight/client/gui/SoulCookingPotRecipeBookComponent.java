package io.github.rcneg.bossesdelight.client.gui;

import io.github.rcneg.bossesdelight.recipes.SoulCookingPotRecipe;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class SoulCookingPotRecipeBookComponent extends RecipeBookComponent {
    protected static final ResourceLocation RECIPE_BOOK_BUTTONS = new ResourceLocation("farmersdelight", "textures/gui/recipe_book_buttons.png");

    public SoulCookingPotRecipeBookComponent() {
    }

    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(0, 0, 28, 18, RECIPE_BOOK_BUTTONS);
    }

    public void hide() {
        this.setVisible(false);
    }

    @Nonnull
    protected Component getRecipeFilterName() {
        return TextUtils.getTranslation("container.recipe_book.cookable", new Object[0]);
    }

    public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);
        if (((Slot)slots.get(6)).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{resultStack}), ((Slot)slots.get(6)).x, ((Slot)slots.get(6)).y);
        }

        if (recipe instanceof SoulCookingPotRecipe cookingRecipe) {
            ItemStack containerStack = cookingRecipe.getOutputContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{containerStack}), ((Slot)slots.get(7)).x, ((Slot)slots.get(7)).y);
            }
        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
    }
}
