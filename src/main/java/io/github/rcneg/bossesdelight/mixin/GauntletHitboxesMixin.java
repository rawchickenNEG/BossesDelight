package io.github.rcneg.bossesdelight.mixin;

import com.cerbon.bosses_of_mass_destruction.entity.custom.gauntlet.GauntletHitboxes;
import io.github.rcneg.bossesdelight.init.EffectRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GauntletHitboxes.class)
public class GauntletHitboxesMixin {

    @Inject(
            method = "shouldDamage(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void bd$breakdownEffect(LivingEntity actor, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource.getEntity() instanceof LivingEntity living && living.hasEffect(EffectRegistry.BREAKDOWN.get())) {
            actor.playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR);
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
