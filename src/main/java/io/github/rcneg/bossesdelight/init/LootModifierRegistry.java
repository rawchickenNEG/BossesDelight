package io.github.rcneg.bossesdelight.init;

import com.mojang.serialization.Codec;
import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.loot.ModAddLootModifier;
import io.github.rcneg.bossesdelight.loot.ModLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootModifierRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS , BossesDelight.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM = LOOT_MODIFIER.register("add_item", ModLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE = LOOT_MODIFIER.register("add_loot_table", ModAddLootModifier.CODEC);
}
