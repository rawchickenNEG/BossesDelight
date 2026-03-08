package io.github.rcneg.bossesdelight.init;

import com.mojang.datafixers.types.Type;
import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.blocks.entity.SoulCookingPotBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;
    public static final RegistryObject<BlockEntityType<SoulCookingPotBlockEntity>>SOUL_COOKING_POT;
    public BlockEntityRegistry() {
    }
    static {
        BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BossesDelight.MODID);
        SOUL_COOKING_POT = BLOCK_ENTITIES.register("soul_cooking_pot", () -> {
            return BlockEntityType.Builder.of(SoulCookingPotBlockEntity::new, new Block[]{(Block)BlockRegistry.SOUL_COOKING_POT.get()}).build((Type)null);
        });
    }
}
