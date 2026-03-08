package io.github.rcneg.bossesdelight.items;

import io.github.rcneg.bossesdelight.BossesDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.item.KnifeItem;

import javax.annotation.Nullable;
import java.util.List;

public class TippedKnifeItems extends KnifeItem {
    public TippedKnifeItems(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        String string = "tooltip."+ BossesDelight.MODID  +"." + stack.getItem();
        tooltip.add(Component.translatable(string).withStyle(ChatFormatting.YELLOW));
    }
}
