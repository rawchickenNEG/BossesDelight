package io.github.rcneg.bossesdelight.items;

import io.github.rcneg.bossesdelight.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AnimaIceDrinkItem extends DrinkableItem {
    public AnimaIceDrinkItem(Item.Properties properties) {
        super(properties);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects().stream().filter(e -> e.getAmplifier() < Config.ANIMA_DRINK_LEVEL.get() - 1).toList());
        if(!list.isEmpty()){
            MobEffectInstance effectInstance = list.get(consumer.getRandom().nextInt(list.size()));
            consumer.addEffect(new MobEffectInstance(effectInstance.getEffect(), effectInstance.getDuration(), effectInstance.getAmplifier() + 1));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.bosses_delight.anima_ice_drink", Config.GLARE_SODA_LEVEL.get()).withStyle(ChatFormatting.BLUE));
    }
}
