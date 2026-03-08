package io.github.rcneg.bossesdelight.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BrimstoneNectarTeaItem extends DrinkableItem {
    public BrimstoneNectarTeaItem(Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        Random random = new Random();
        List<MobEffect> effects = BuiltInRegistries.MOB_EFFECT.stream().filter(effect ->
                !effect.isInstantenous() && !effect.equals(MobEffects.SATURATION) && effect.isBeneficial()
        ).toList();
        MobEffect randomEffect = effects.get(random.nextInt(effects.size()));
        if(!level.isClientSide()){
            int duration = 2000;
            consumer.addEffect(new MobEffectInstance(randomEffect, duration, 1));
        }
    }
}
