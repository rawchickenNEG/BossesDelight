package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.blocks.entity.container.SoulCookingPotMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES;
    public static final RegistryObject<MenuType<SoulCookingPotMenu>> SOUL_COOKING_POT;

    public MenuTypesRegistry() {
    }

    static {
        MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BossesDelight.MODID);
        SOUL_COOKING_POT = MENU_TYPES.register("soul_cooking_pot", () -> {
            return IForgeMenuType.create(SoulCookingPotMenu::new);
        });
    }
}