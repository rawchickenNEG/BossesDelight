package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.recipes.SoulCookingPotRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypesRegistry {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;
    public static final RegistryObject<RecipeType<SoulCookingPotRecipe>> SOUL_COOKING;

    public RecipeTypesRegistry() {
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return BossesDelight.MODID + ":" + identifier;
            }
        };
    }

    static {
        RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, BossesDelight.MODID);
        SOUL_COOKING = RECIPE_TYPES.register("soul_cooking", () -> {
            return registerRecipeType("soul_cooking");
        });
    }
}
