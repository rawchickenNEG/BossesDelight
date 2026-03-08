package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.recipes.SoulCookingPotRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializersRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
    public static final RegistryObject<RecipeSerializer<?>> COOKING;
    public RecipeSerializersRegistry() {
    }

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BossesDelight.MODID);
        COOKING = RECIPE_SERIALIZERS.register("soul_cooking", SoulCookingPotRecipe.Serializer::new);
    }
}
