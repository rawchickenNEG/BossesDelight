package io.github.rcneg.bossesdelight.blocks.entity;

import io.github.rcneg.bossesdelight.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface FrostableBlockEntity {
    default boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(ModTags.COLD_SOURCES);
    }
}