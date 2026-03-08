package io.github.rcneg.bossesdelight.blocks;

import io.github.rcneg.bossesdelight.config.Config;
import io.github.rcneg.bossesdelight.init.BlockRegistry;
import io.github.rcneg.bossesdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ObsidianOnionsBlock extends CropBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)};

    public ObsidianOnionsBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return (BlockRegistry.OBSIDIAN_ONION_BLOCK.get()).defaultBlockState();
    }

    protected ItemLike getBaseSeedId() {
        return ItemRegistry.OBSIDIAN_ONION.get();
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    @Override
    public void randomTick(BlockState p_221050_, ServerLevel p_221051_, BlockPos p_221052_, RandomSource p_221053_) {
        if (p_221051_.isAreaLoaded(p_221052_, 1)) {
            int i = this.getAge(p_221050_);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(this, p_221051_, p_221052_);
                if (ForgeHooks.onCropsGrowPre(p_221051_, p_221052_, p_221050_, p_221053_.nextInt((int)(25.0F / f) + 1) == 0)) {
                    p_221051_.setBlock(p_221052_, this.getStateForAge(i + 1), 2);
                    ForgeHooks.onCropsGrowPost(p_221051_, p_221052_, p_221050_);
                }
            }
            if(p_221053_.nextInt(60) == 0 && Config.OBSIDIAN_ONION_CONVERTING.get()){
                p_221051_.setBlock(p_221052_.below(), Blocks.CRYING_OBSIDIAN.defaultBlockState(), 2);
            }
        }
    }

    protected static float getGrowthSpeed(Block p_52273_, BlockGetter p_52274_, BlockPos p_52275_) {
        float f = 1.0F;
        BlockPos blockpos = p_52275_.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 1.0F;
                BlockState blockstate = p_52274_.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.is(Blocks.CRYING_OBSIDIAN)) {
                    f1 = 2.0F;
                }
                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = p_52275_.north();
        BlockPos blockpos2 = p_52275_.south();
        BlockPos blockpos3 = p_52275_.west();
        BlockPos blockpos4 = p_52275_.east();
        boolean flag = p_52274_.getBlockState(blockpos3).is(p_52273_) || p_52274_.getBlockState(blockpos4).is(p_52273_);
        boolean flag1 = p_52274_.getBlockState(blockpos1).is(p_52273_) || p_52274_.getBlockState(blockpos2).is(p_52273_);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = p_52274_.getBlockState(blockpos3.north()).is(p_52273_) || p_52274_.getBlockState(blockpos4.north()).is(p_52273_) || p_52274_.getBlockState(blockpos4.south()).is(p_52273_) || p_52274_.getBlockState(blockpos3.south()).is(p_52273_);
            if (flag2) {
                f /= 2.0F;
            }
        }
        return f;
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return Config.OBSIDIAN_ONION_PLANTABLE_BLOCKS.contains(p_51042_.getBlock());
    }

    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
        BlockState below = p_51029_.getBlockState(p_51030_.below());
        return this.mayPlaceOn(below, p_51029_, p_51030_);
    }
}