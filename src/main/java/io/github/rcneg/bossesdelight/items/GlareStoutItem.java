package io.github.rcneg.bossesdelight.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlareStoutItem extends DrinkableItem {
    public GlareStoutItem(Properties properties) {
        super(properties, false, true);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
        List<MobEffectInstance> newEffects = new ArrayList<>();
        List<Integer> durationList = new ArrayList<>();
        List<Integer> ampifierList = new ArrayList<>();
        if(!list.isEmpty()){
            for(MobEffectInstance effectInstance : list){
                durationList.add(effectInstance.getDuration());
                ampifierList.add(effectInstance.getAmplifier());
            }
            Collections.shuffle(durationList);
            Collections.shuffle(ampifierList);
            for(int i = 0; i < list.size(); ++i){
                newEffects.add(new MobEffectInstance(list.get(i).getEffect(), durationList.get(i), ampifierList.get(i)));
            }
            consumer.getActiveEffectsMap().clear();
            for(MobEffectInstance newEffect : newEffects){
                consumer.addEffect(newEffect);
            }
        }
    }
}
