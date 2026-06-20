package io.github.rcneg.bossesdelight.items;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.init.CriticalTriggerRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import javax.annotation.Nullable;
import java.util.List;

public class NectarJelly extends Item {
    public NectarJelly(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResult useOn(UseOnContext p_40637_) {
        Level level = p_40637_.getLevel();
        ItemStack itemstack = p_40637_.getItemInHand();
        BlockPos blockpos = p_40637_.getClickedPos();
        BlockState state = level.getBlockState(blockpos);
        if (state.getBlock() instanceof CakeBlock || state.getBlock() instanceof FeastBlock || state.getBlock() instanceof PieBlock) {
            BlockState defaultState = state.getBlock().defaultBlockState();
            if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                    && defaultState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                defaultState = defaultState.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
            }
            if(!defaultState.equals(state)){
                if (!level.isClientSide() && p_40637_.getPlayer() instanceof ServerPlayer player) {
                    CriticalTriggerRegistry.JELLY_TRIGGER.trigger(player);
                }
                level.setBlock(blockpos, defaultState, 2);
                level.playSound((Player)null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SLIME_BLOCK_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!p_40637_.getPlayer().getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.CONSUME;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        String string = "tooltip."+ BossesDelight.MODID  +"." + stack.getItem();
        tooltip.add(Component.translatable(string).withStyle(ChatFormatting.YELLOW));
    }
}
