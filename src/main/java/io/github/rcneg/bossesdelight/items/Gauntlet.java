package io.github.rcneg.bossesdelight.items;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import javax.annotation.Nullable;
import java.util.List;

public class Gauntlet extends KnifeItem implements Equipable {

    public Gauntlet(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(itemStack);
    }

    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        int f = stack.getUseDuration() - remainingUseDuration;
        if (f < 10 || !(livingEntity instanceof Player player)) {
            return;
        }
        var hit = player.pick(player.getAttributeValue(ForgeMod.BLOCK_REACH.get()), 1, false);

        if (hit instanceof BlockHitResult blockHit) {
            BlockPos pos = blockHit.getBlockPos();
            Direction dir = blockHit.getDirection();
            BlockState state = level.getBlockState(pos);
            if(state.is(ModBlocks.CUTTING_BOARD.get()) || ((state.getBlock() instanceof FeastBlock || state.getBlock() instanceof CakeBlock || state.getBlock() instanceof PieBlock)&& !Config.COLLECT_FEAST.get())){
                return;
            }
            if(state.getDestroySpeed(level, BlockPos.containing(pos.getX(), pos.getY(), pos.getZ())) >= 0){
                if (!player.isCreative()){
                    stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(player.getUsedItemHand()));
                }
                level.destroyBlock(pos, false, player);
                Block.popResourceFromFace(level, pos, dir, state.getBlock().getCloneItemStack(level, pos, state));
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                livingEntity.releaseUsingItem();
            }
        }
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        String string = "tooltip."+ BossesDelight.MODID  +"." + stack.getItem();
        tooltip.add(Component.translatable(string).withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.translatable(string + "_1").withStyle(ChatFormatting.YELLOW));
        tooltip.add(Component.translatable(string + "_2").withStyle(ChatFormatting.YELLOW));
    }
}
