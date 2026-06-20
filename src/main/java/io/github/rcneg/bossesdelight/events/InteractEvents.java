package io.github.rcneg.bossesdelight.events;

import io.github.rcneg.bossesdelight.init.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

@Mod.EventBusSubscriber
public class InteractEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (state.getBlock() instanceof CakeBlock || state.getBlock() instanceof FeastBlock || state.getBlock() instanceof PieBlock) {
            if (stack.is(ItemRegistry.NECTAR_JELLY.get())) {
                event.setUseBlock(Event.Result.DENY);
            }
        }
    }
}
