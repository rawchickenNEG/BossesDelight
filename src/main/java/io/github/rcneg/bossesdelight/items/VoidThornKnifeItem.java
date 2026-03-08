package io.github.rcneg.bossesdelight.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class VoidThornKnifeItem extends TippedKnifeItems{
    public VoidThornKnifeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    public boolean hurtEnemy(ItemStack p_40994_, LivingEntity p_40995_, LivingEntity p_40996_) {
        p_40994_.hurtAndBreak(1, p_40996_, (p_41007_) -> {
            p_41007_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        p_40995_.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3000, 0));
        return true;
    }
}
