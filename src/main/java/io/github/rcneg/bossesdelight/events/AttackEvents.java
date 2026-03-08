package io.github.rcneg.bossesdelight.events;

import io.github.rcneg.bossesdelight.init.EffectRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AttackEvents {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void OnEntityHurtEvent(LivingHurtEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            MobEffect effect = EffectRegistry.LAST_STAND.get();

            if(entity.hasEffect(effect)){
                if(event.getAmount() >= entity.getHealth() && entity.getHealth() > 1 && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                    event.setCanceled(true);
                    if(!entity.hasEffect(MobEffects.DAMAGE_RESISTANCE)){
                        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, (int) Math.pow(2, entity.getEffect(effect).getAmplifier() + 1) * 20, 4));
                    }
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ANVIL_HIT, SoundSource.NEUTRAL, 1.0F, 0.3F);
                    entity.setHealth(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnEntityHurtEvent2(LivingHurtEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            MobEffect effect = EffectRegistry.GAUNTLET_PROTECTION.get();
            if(entity.hasEffect(effect) && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                if(event.getSource().getEntity() == null) return;
                if(event.getSource().getEntity() instanceof LivingEntity living && living.hasEffect(EffectRegistry.BREAKDOWN.get())) return;
                float angle = Math.abs(event.getSource().getEntity().getYRot() - entity.getYRot()) % 360;
                if(angle < 90|| angle > 270){
                    event.setAmount((float) (event.getAmount() * Math.pow(0.5, entity.getEffect(effect).getAmplifier())));
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingAttackEvent(LivingAttackEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            MobEffect effect2 = EffectRegistry.INTANGIBLE.get();
            if(entity.hasEffect(effect2) && !event.getSource().is(DamageTypes.GENERIC_KILL)){
                if(entity.getRandom().nextInt(10) < 2 * entity.getEffect(effect2).getAmplifier() || event.getSource().getDirectEntity() instanceof Projectile){
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ALLAY_ITEM_TAKEN, SoundSource.NEUTRAL, 1.0F, 0.3F);
                    event.setCanceled(true);
                }
            }
        }
    }
}

