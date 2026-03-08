package io.github.rcneg.bossesdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class CrystalFruitCubeBlock extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 11, 13.0);

    public CrystalFruitCubeBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return PLATE_SHAPE;
    }
}