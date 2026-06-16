package io.github.rcneg.bossesdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class ObsidianGlazedDragonHeadBlockLegacy extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE = Block.box(2.0, 0.0, 0.0, 14.0, 2.0, 16.0);
    protected static final VoxelShape PLATE_SHAPE_A = Block.box(0.0, 0.0, 2.0, 16.0, 2.0, 14.0);
    protected static final VoxelShape PIE_SHAPE;
    protected static final VoxelShape PIE_SHAPE_A;

    public ObsidianGlazedDragonHeadBlockLegacy(BlockBehaviour.Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape vs = state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        Direction d = state.getValue(FACING);
        if(d == Direction.WEST || d == Direction.EAST){
            return state.getValue(SERVINGS) == 0 ? PLATE_SHAPE_A : PIE_SHAPE_A;
        }
        return vs;
    }

    static {
        PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(4.0, 2.0, 1.0, 12.0, 10.0, 15.0), BooleanOp.OR);
        PIE_SHAPE_A = Shapes.joinUnoptimized(PLATE_SHAPE_A, Block.box(1.0, 2.0, 4.0, 15.0, 10.0, 12.0), BooleanOp.OR);
    }
}