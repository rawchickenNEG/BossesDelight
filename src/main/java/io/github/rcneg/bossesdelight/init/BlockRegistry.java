package io.github.rcneg.bossesdelight.init;

import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.CookingPotBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BossesDelight.MODID);

    public static final RegistryObject<Block> OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK  = BLOCKS.register("obsidian_glazed_dragon_head_block", () -> {
        return new ObsidianGlazedDragonHeadBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD, true);
    });
    public static final RegistryObject<Block> CRYSTAL_FRUIT_CUBE_BLOCK  = BLOCKS.register("crystal_fruit_cube_block", () -> {
        return new CrystalFruitCubeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY), ItemRegistry.BOWL_OF_CRYSTAL_FRUIT_CUBE, false);
    });
    public static final RegistryObject<Block> LICH_SMOOTHIES_BLOCK  = BLOCKS.register("lich_smoothies_block", () -> {
        return new LichSmoothiesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.SOUL_SAND).pushReaction(PushReaction.DESTROY).lightLevel((state) -> 4), ItemRegistry.BOWL_OF_LICH_SMOOTHIES, true);
    });
    public static final RegistryObject<Block> HAM_ABOVE_PALM_BLOCK  = BLOCKS.register("ham_above_palm_block", () -> {
        return new LichSmoothiesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.NETHERITE_BLOCK).pushReaction(PushReaction.DESTROY), ItemRegistry.BOWL_OF_HAM_ABOVE_PALM, true);
    });
    public static final RegistryObject<Block> MAGIC_FROZEN_NOODLES_BLOCK  = BLOCKS.register("magic_frozen_noodles_block", () -> {
        return new MagicFrozenNoodlesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.3F).sound(SoundType.SOUL_SAND).pushReaction(PushReaction.DESTROY).lightLevel((state) -> 15), ItemRegistry.BOWL_OF_MAGIC_FROZEN_NOODLES, true);
    });
    public static final RegistryObject<Block> BLAZING_EYE_PIE_BLOCK  = BLOCKS.register("blazing_eye_pie_block", () -> {
        return new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).sound(SoundType.NETHERITE_BLOCK), ItemRegistry.BLAZING_EYE_PIE_SLICE);
    });
    public static final RegistryObject<Block> CRYSTAL_FRUIT_BLOCK = BLOCKS.register("crystal_fruit", () -> {
        return new CrystalFruitPlantBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH));
    });
    public static final RegistryObject<Block> OBSIDIAN_ONION_BLOCK = BLOCKS.register("obsidian_onions", () -> {
        return new ObsidianOnionsBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT));
    });
    public static final RegistryObject<Block> SOUL_COOKING_POT = BLOCKS.register("soul_cooking_pot", () -> {
        return new SoulCookingPotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN));
    });

}
