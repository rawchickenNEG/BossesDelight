package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BossesDelight.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(BossesDelight.MODID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.bosses_delight"))
            .icon(ItemRegistry.OBSIDIAN_RUNE_PUREE.get()::getDefaultInstance)
            .displayItems((parameters, output) -> {
                for(RegistryObject<Item> item: ItemRegistry.ITEMS.getEntries()){
                    output.accept(item.get());
                }
            }).build());
}
