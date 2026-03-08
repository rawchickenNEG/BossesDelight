package io.github.rcneg.bossesdelight;

import com.mojang.logging.LogUtils;
import io.github.rcneg.bossesdelight.client.gui.SoulCookingPotScreen;
import io.github.rcneg.bossesdelight.client.recipebook.BDRecipeCategories;
import io.github.rcneg.bossesdelight.config.Config;
import io.github.rcneg.bossesdelight.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BossesDelight.MODID)
public class BossesDelight
{
    public static final String MODID = "bosses_delight";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final RecipeBookType RECIPE_TYPE_SOUL_COOKING = RecipeBookType.create("SOUL_COOKING");

    public BossesDelight()
    {
        CriticalTriggerRegistry.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        LootModifierRegistry.LOOT_MODIFIER.register(modEventBus);
        TabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        EffectRegistry.MOB_EFFECTS.register(modEventBus);
        MenuTypesRegistry.MENU_TYPES.register(modEventBus);
        RecipeSerializersRegistry.RECIPE_SERIALIZERS.register(modEventBus);
        RecipeTypesRegistry.RECIPE_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> MenuScreens.register(MenuTypesRegistry.SOUL_COOKING_POT.get(), SoulCookingPotScreen::new));
        }

        @SubscribeEvent
        public static void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
            BDRecipeCategories.init(event);
        }
    }
}
