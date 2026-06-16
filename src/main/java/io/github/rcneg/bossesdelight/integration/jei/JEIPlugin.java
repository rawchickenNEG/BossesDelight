package io.github.rcneg.bossesdelight.integration.jei;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.init.ItemRegistry;
import io.github.rcneg.bossesdelight.init.MenuTypesRegistry;
import io.github.rcneg.bossesdelight.init.RecipeTypesRegistry;
import io.github.rcneg.bossesdelight.integration.jei.category.SoulCookingRecipeCategory;
import io.github.rcneg.bossesdelight.recipes.SoulCookingPotRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;
import vectorwing.farmersdelight.integration.jei.FDRecipes;
import vectorwing.farmersdelight.integration.jei.category.CookingRecipeCategory;
import vectorwing.farmersdelight.integration.jei.category.CuttingRecipeCategory;
import vectorwing.farmersdelight.integration.jei.category.DecompositionRecipeCategory;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(BossesDelight.MODID, "jei_plugin");

    public static final RecipeType<SoulCookingPotRecipe> SOUL_COOKING = RecipeType.create(BossesDelight.MODID, "soul_cooking", SoulCookingPotRecipe.class);

    public JEIPlugin() {
    }

    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new SoulCookingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
}

    public void registerRecipes(IRecipeRegistration registration) {
        BDRecipes modRecipes = new BDRecipes();
        registration.addRecipes(SOUL_COOKING, modRecipes.getSoulCookingPotRecipes());
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike)ItemRegistry.SOUL_COOKING_POT_ITEM.get()), SOUL_COOKING);
    }

    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CookingPotScreen.class, 89, 25, 24, 17, SOUL_COOKING);
    }

    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CookingPotMenu.class, (MenuType) MenuTypesRegistry.SOUL_COOKING_POT.get(), SOUL_COOKING, 0, 6, 9, 36);
    }

    public ResourceLocation getPluginUid() {
        return ID;
    }
}

