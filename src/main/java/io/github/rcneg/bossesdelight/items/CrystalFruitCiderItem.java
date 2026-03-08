package io.github.rcneg.bossesdelight.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.List;

public class CrystalFruitCiderItem extends DrinkableItem {
    public CrystalFruitCiderItem(Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
        if(!list.isEmpty()){
            MobEffectInstance effectInstance = list.get(consumer.getRandom().nextInt(list.size()));
            consumer.addEffect(new MobEffectInstance(effectInstance.getEffect(), effectInstance.getDuration() + 600, effectInstance.getAmplifier()));
        }
    }
}
