package io.github.rcneg.bossesdelight.mobeffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class Breakdown extends MobEffect {
    public Breakdown(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
