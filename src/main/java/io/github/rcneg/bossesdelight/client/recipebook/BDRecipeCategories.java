package io.github.rcneg.bossesdelight.client.recipebook;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.init.ItemRegistry;
import io.github.rcneg.bossesdelight.init.RecipeTypesRegistry;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;

import java.util.function.Supplier;

public class BDRecipeCategories
{
    public static final Supplier<RecipeBookCategories> COOKING_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("bosses_delight:soul_cooking_search", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> COOKING_FOODS = Suppliers.memoize(() -> RecipeBookCategories.create("bosses_delight:soul_cooking_foods", new ItemStack(ItemRegistry.NECTAR_JELLY.get()), new ItemStack(ItemRegistry.CRYSTAL_CUP_CAKE.get())));

    public static void init(RegisterRecipeBookCategoriesEvent event) {
        event.registerBookCategories(BossesDelight.RECIPE_TYPE_SOUL_COOKING, ImmutableList.of(COOKING_SEARCH.get(), COOKING_FOODS.get()));
        event.registerAggregateCategory(COOKING_SEARCH.get(), ImmutableList.of(COOKING_FOODS.get()));
        event.registerRecipeCategoryFinder(RecipeTypesRegistry.SOUL_COOKING.get(), recipe -> COOKING_FOODS.get());
    }
}
