package io.github.rcneg.bossesdelight.criticaltriggers;


import com.google.gson.JsonObject;
import io.github.rcneg.bossesdelight.BossesDelight;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class JellyTrigger extends SimpleCriterionTrigger<JellyTrigger.TriggerInstance> {

    public static final ResourceLocation ID = new ResourceLocation(BossesDelight.MODID, "jelly_trigger");

    public JellyTrigger() {
    }

    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, JellyTrigger.TriggerInstance::test);
    }

    protected JellyTrigger.TriggerInstance createInstance(JsonObject json, ContextAwarePredicate player, DeserializationContext conditionsParser) {
        return new JellyTrigger.TriggerInstance(player);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ContextAwarePredicate player) {
            super(JellyTrigger.ID, player);
        }

        public static JellyTrigger.TriggerInstance simple() {
            return new JellyTrigger.TriggerInstance(ContextAwarePredicate.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}