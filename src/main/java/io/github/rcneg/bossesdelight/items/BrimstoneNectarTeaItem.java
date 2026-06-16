package io.github.rcneg.bossesdelight.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
        if(!level.isClientSide()){
            consumer.setHealth(consumer.getMaxHealth());
            List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
            for (MobEffectInstance ins : list) {
                consumer.removeEffect(ins.getEffect());
                if (consumer.hasEffect(ins.getEffect())) {
                    consumer.getActiveEffectsMap().remove(ins.getEffect());
                }
            }
        }
    }
}
