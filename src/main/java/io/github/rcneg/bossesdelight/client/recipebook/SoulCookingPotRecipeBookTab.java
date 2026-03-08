package io.github.rcneg.bossesdelight.client.recipebook;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SoulCookingPotRecipeBookTab implements StringRepresentable
{
    FOODS("foods");

    public final String name;

    SoulCookingPotRecipeBookTab(String name) {
        this.name = name;
    }

    public static SoulCookingPotRecipeBookTab findByName(String name) {
        for (SoulCookingPotRecipeBookTab value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
