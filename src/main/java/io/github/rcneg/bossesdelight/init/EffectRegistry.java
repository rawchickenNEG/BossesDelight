package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.mobeffects.Breakdown;
import io.github.rcneg.bossesdelight.mobeffects.GauntletProtectionEffect;
import io.github.rcneg.bossesdelight.mobeffects.IntangibleEffect;
import io.github.rcneg.bossesdelight.mobeffects.LastStandEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BossesDelight.MODID);
    public static final RegistryObject<MobEffect> GAUNTLET_PROTECTION = MOB_EFFECTS.register("gauntlet_protection", () -> new GauntletProtectionEffect(MobEffectCategory.BENEFICIAL, -3355648));
    public static final RegistryObject<MobEffect> INTANGIBLE = MOB_EFFECTS.register("intangible", () -> new IntangibleEffect(MobEffectCategory.BENEFICIAL, -3355648));
    public static final RegistryObject<MobEffect> LAST_STAND = MOB_EFFECTS.register("last_stand", () -> new LastStandEffect(MobEffectCategory.BENEFICIAL, -3355648));
    public static final RegistryObject<MobEffect> BREAKDOWN = MOB_EFFECTS.register("breakdown", () -> new Breakdown(MobEffectCategory.BENEFICIAL, -3355648));


}
