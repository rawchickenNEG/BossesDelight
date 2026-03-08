package io.github.rcneg.bossesdelight.blocks;

import io.github.rcneg.bossesdelight.config.Config;
import io.github.rcneg.bossesdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;


public class CrystalFruitPlantBlock extends SweetBerryBushBlock {
    private static final VoxelShape SHAPE_1;
    private static final VoxelShape SHAPE_2;
    private static final VoxelShape SHAPE_3;
    private static final VoxelShape SHAPE_4;
    public static final IntegerProperty AGE;

    public CrystalFruitPlantBlock(Properties p_57249_) {
        super(p_57249_);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_57256_, BlockPos p_57257_, BlockState p_57258_) {
        return new ItemStack(ItemRegistry.CRYSTAL_FRUIT_SEEDS.get());
    }

    @Override
    public InteractionResult use(BlockState p_57275_, Level p_57276_, BlockPos p_57277_, Player p_57278_, InteractionHand p_57279_, BlockHitResult p_57280_) {
        int i = (Integer)p_57275_.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && p_57278_.getItemInHand(p_57279_).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 2) {
            popResource(p_57276_, p_57277_, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("bosses_of_mass_destruction:crystal_fruit")), 1));
            p_57276_.playSound((Player)null, p_57277_, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + p_57276_.random.nextFloat() * 0.4F);
            BlockState blockstate = (BlockState)p_57275_.setValue(AGE, 1);
            p_57276_.setBlock(p_57277_, blockstate, 2);
            p_57276_.gameEvent(GameEvent.BLOCK_CHANGE, p_57277_, GameEvent.Context.of(p_57278_, blockstate));
            return InteractionResult.sidedSuccess(p_57276_.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return Config.CRYSTAL_FRUIT_PLANTABLE_BLOCKS.contains(p_51042_.getBlock());
    }

    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
        BlockState below = p_51029_.getBlockState(p_51030_.below());
        return this.mayPlaceOn(below, p_51029_, p_51030_);
    }

    @Override
    public VoxelShape getShape(BlockState p_57291_, BlockGetter p_57292_, BlockPos p_57293_, CollisionContext p_57294_) {
        VoxelShape shape = super.getShape(p_57291_, p_57292_, p_57293_, p_57294_);
        switch(p_57291_.getValue(AGE)){
            case 0 -> shape = SHAPE_1;
            case 1 -> shape = SHAPE_2;
            case 2 -> shape = SHAPE_3;
            case 3 -> shape = SHAPE_4;
            default -> shape = super.getShape(p_57291_, p_57292_, p_57293_, p_57294_);
        }
        return shape;
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE_1 = Block.box(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
        SHAPE_2 = Block.box(3.0, 0.0, 3.0, 13.0, 5.0, 13.0);
        SHAPE_3 = Block.box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);
        SHAPE_4 = Block.box(1.0, 0.0, 1.0, 15.0, 10.0, 15.0);
    }
}