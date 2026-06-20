package io.github.rcneg.bossesdelight.blocks;

import io.github.rcneg.bossesdelight.init.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ObsidianGlazedDragonHeadBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 9);
    protected static final VoxelShape CONTAINER_SHAPE_BACK_S = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_BACK_W = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_BACK_N = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_BACK_E = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_FRONT_S = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_FRONT_W = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_FRONT_N = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    protected static final VoxelShape CONTAINER_SHAPE_FRONT_E = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    public final List<Supplier<Item>> riceRollServings;


    public ObsidianGlazedDragonHeadBlock(Properties props) {
        super(props);
        this.riceRollServings = Arrays.asList(
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD,
                ItemRegistry.OBSIDIAN_GLAZED_DRAGON_TONGUE,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_BRAIN,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_BRAIN,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD,
                ItemRegistry.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SERVINGS, 0)
                .setValue(PART, Part.FRONT));
    }

    public enum Part implements StringRepresentable {
        FRONT("front"),
        BACK("back");

        private final String name;
        Part(String name) { this.name = name; }
        @Override public String getSerializedName() { return this.name; }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS, PART);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction extBACKDir = getExtendDir(ctx);

        for (int i = 0; i < 2; i++) {
            BlockPos p = pos.relative(extBACKDir, i);
            if (!level.getWorldBorder().isWithinBounds(p)) return null;
            if (!level.getBlockState(p).canBeReplaced(ctx)) return null;
        }

        return this.defaultBlockState()
                .setValue(FACING, extBACKDir)
                .setValue(PART, Part.FRONT)
                .setValue(SERVINGS, 9);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (level.isClientSide) return;
        Direction d = state.getValue(FACING);

        level.setBlock(pos.relative(d, 1), state.setValue(PART, Part.BACK), Block.UPDATE_ALL);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.isClientSide) {
            super.playerWillDestroy(level, pos, state, player);
            return;
        }
        BlockPos front = getStartPos(pos, state);
        Direction facing = state.getValue(FACING);
        BlockState frontState = level.getBlockState(front);

        if (!player.getAbilities().instabuild && frontState.is(this)) {
            Block.dropResources(frontState, level, front, null, player, player.getMainHandItem());
        }

        int flags = Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS;
        for (int i = 0; i < 2; i++) {
            BlockPos p = front.relative(facing, i);
            BlockState s = level.getBlockState(p);
            if (s.is(this)) {
                level.setBlock(p, Blocks.AIR.defaultBlockState(), flags);
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if (!isStructureValid(level, pos, state)) return Blocks.AIR.defaultBlockState();
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    private boolean isStructureValid(LevelAccessor level, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        BlockPos front = getStartPos(pos, state);

        BlockState frontState = level.getBlockState(front);
        if (!frontState.is(this)) return false;

        int servings = frontState.getValue(SERVINGS);

        return isPart(level, front, facing, Part.FRONT, servings)
                && isPart(level, front.relative(facing, 1), facing, Part.BACK, servings);
    }

    private boolean isPart(LevelAccessor level, BlockPos pos, Direction facing, Part part, int servings) {
        BlockState s = level.getBlockState(pos);
        return s.is(this)
                && s.getValue(FACING) == facing
                && s.getValue(PART) == part
                && s.getValue(SERVINGS) == servings;
    }

    private BlockPos getStartPos(BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        return switch (state.getValue(PART)) {
            case FRONT -> pos;
            case BACK -> pos.relative(facing.getOpposite(), 1);
        };
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return this.rotate(state, mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    private static Direction getExtendDir(BlockPlaceContext ctx) {
        var player = ctx.getPlayer();
        BlockPos pos = ctx.getClickedPos();

        if (player == null) return ctx.getHorizontalDirection();

        double dx = (pos.getX() + 0.5) - player.getX();
        double dz = (pos.getZ() + 0.5) - player.getZ();

        Direction d = Direction.getNearest(dx, 0.0, dz);
        if (d.getAxis().isVertical()) d = ctx.getHorizontalDirection();
        return d;
    }

    public IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        return this.takeServing(level, pos, state, player, hand);
    }

    protected InteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        BlockPos front = getStartPos(pos, state);
        BlockState frontState = level.getBlockState(front);
        if (!frontState.is(this)) return InteractionResult.PASS;

        int servings = frontState.getValue(this.getServingsProperty());

        if (servings == 0) {
            level.playSound(null, front, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);

            Direction facing = frontState.getValue(FACING);

            level.destroyBlock(front, true);
            for (int i = 1; i < 2; i++) {
                BlockPos p = front.relative(facing, i);
                if (level.getBlockState(p).is(this)) {
                    level.setBlock(p, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                }
            }
            return InteractionResult.SUCCESS;
        }

        ItemStack serving = this.getServingItem(frontState);
        ItemStack heldStack = player.getItemInHand(hand);

        if (!serving.hasCraftingRemainingItem() || ItemStack.isSameItem(heldStack, serving.getCraftingRemainingItem())) {
            int next = servings - 1;

            setServingsAll(level, front, frontState, next);

            if (!player.getAbilities().instabuild && serving.hasCraftingRemainingItem()) {
                heldStack.shrink(1);
            }

            if (!player.getInventory().add(serving)) {
                player.drop(serving, false);
            }

            level.playSound(null, front, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        player.displayClientMessage(
                TextUtils.getTranslation("block.feast.use_container", serving.getCraftingRemainingItem().getHoverName()),
                true
        );
        return InteractionResult.PASS;
    }

    private void setServingsAll(LevelAccessor level, BlockPos anyPos, BlockState anyState, int servings) {
        BlockPos front = getStartPos(anyPos, anyState);
        Direction facing = anyState.getValue(FACING);

        int flags = Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE;

        for (int i = 0; i < 2; i++) {
            BlockPos p = front.relative(facing, i);
            BlockState s = level.getBlockState(p);
            if (s.is(this)) {
                level.setBlock(p, s.setValue(SERVINGS, servings), flags);
            }
        }
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = CONTAINER_SHAPE_BACK_N;
        if(state.getValue(PART)== Part.BACK){
            switch (state.getValue(FACING)){
                case NORTH -> shape = CONTAINER_SHAPE_BACK_N;
                case SOUTH -> shape = CONTAINER_SHAPE_BACK_S;
                case EAST -> shape = CONTAINER_SHAPE_BACK_E;
                case WEST -> shape = CONTAINER_SHAPE_BACK_W;
            }
        }else if(state.getValue(PART)== Part.FRONT){
            switch (state.getValue(FACING)){
                case NORTH -> shape = CONTAINER_SHAPE_FRONT_N;
                case SOUTH -> shape = CONTAINER_SHAPE_FRONT_S;
                case EAST -> shape = CONTAINER_SHAPE_FRONT_E;
                case WEST -> shape = CONTAINER_SHAPE_FRONT_W;
            }
        }
        return shape;
    }
}