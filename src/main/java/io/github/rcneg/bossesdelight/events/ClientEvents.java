package io.github.rcneg.bossesdelight.events;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.init.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BossesDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK_LEGACY.get(), RenderType.cutout());
    }
}
