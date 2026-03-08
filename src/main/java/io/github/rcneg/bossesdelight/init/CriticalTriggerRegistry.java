package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.criticaltriggers.JellyTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class CriticalTriggerRegistry {
        public static final JellyTrigger JELLY_TRIGGER = CriteriaTriggers.register(new JellyTrigger());

        public static void init() { }
}
