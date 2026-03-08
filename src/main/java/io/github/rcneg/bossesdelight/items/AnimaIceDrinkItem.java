package io.github.rcneg.bossesdelight.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AnimaIceDrinkItem extends DrinkableItem {
    public AnimaIceDrinkItem(Item.Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
        if(!list.isEmpty()){
            MobEffectInstance effectInstance = list.get(consumer.getRandom().nextInt(list.size()));
            consumer.addEffect(new MobEffectInstance(effectInstance.getEffect(), effectInstance.getDuration(), effectInstance.getAmplifier() + 1));
        }
    }
}
