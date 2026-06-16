package io.github.rcneg.bossesdelight.items;

import io.github.rcneg.bossesdelight.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlareStoutItem extends DrinkableItem {
    public GlareStoutItem(Properties properties) {
        super(properties);
    }

    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        List<MobEffectInstance> list = new ArrayList<>(consumer.getActiveEffects());
        List<MobEffectInstance> newEffects = new ArrayList<>();
        List<Integer> durationList = new ArrayList<>();
        List<Integer> ampifierList = new ArrayList<>();
        if(!list.isEmpty()){
            for(MobEffectInstance effectInstance : list){
                if(effectInstance.getAmplifier() < Config.GLARE_SODA_LEVEL.get()){
                    durationList.add(effectInstance.getDuration());
                    ampifierList.add(effectInstance.getAmplifier());
                }
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

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.bosses_delight.glare_stout", Config.GLARE_SODA_LEVEL.get()).withStyle(ChatFormatting.BLUE));
    }
}
