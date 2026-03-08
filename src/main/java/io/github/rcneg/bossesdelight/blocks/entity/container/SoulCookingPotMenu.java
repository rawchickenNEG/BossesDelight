package io.github.rcneg.bossesdelight.blocks.entity.container;

import com.mojang.datafixers.util.Pair;
import io.github.rcneg.bossesdelight.BossesDelight;
import io.github.rcneg.bossesdelight.blocks.entity.SoulCookingPotBlockEntity;
import io.github.rcneg.bossesdelight.init.BlockRegistry;
import io.github.rcneg.bossesdelight.init.MenuTypesRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Objects;

public class SoulCookingPotMenu extends RecipeBookMenu<RecipeWrapper> {
    public static final ResourceLocation EMPTY_CONTAINER_SLOT_BOWL = new ResourceLocation("farmersdelight", "item/empty_container_slot_bowl");
    public final SoulCookingPotBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerData cookingPotData;
    private final ContainerLevelAccess canInteractWithCallable;
    protected final Level level;

    public SoulCookingPotMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
    }

    public SoulCookingPotMenu(int windowId, Inventory playerInventory, SoulCookingPotBlockEntity blockEntity, ContainerData cookingPotDataIn) {
        super((MenuType) MenuTypesRegistry.SOUL_COOKING_POT.get(), windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.cookingPotData = cookingPotDataIn;
        this.level = playerInventory.player.level();
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        int startX = 8;
        int startY = 18;
        int inputStartX = 30;
        int inputStartY = 17;
        int borderSlotSize = 18;

        int startPlayerInvY;
        int column;
        for(startPlayerInvY = 0; startPlayerInvY < 2; ++startPlayerInvY) {
            for(column = 0; column < 3; ++column) {
                this.addSlot(new SlotItemHandler(this.inventory, startPlayerInvY * 3 + column, inputStartX + column * borderSlotSize, inputStartY + startPlayerInvY * borderSlotSize));
            }
        }

        this.addSlot(new SoulCookingPotMealSlot(this.inventory, 6, 124, 26));
        this.addSlot(new SlotItemHandler(this.inventory, 7, 92, 55) {
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, SoulCookingPotMenu.EMPTY_CONTAINER_SLOT_BOWL);
            }
        });
        this.addSlot(new SoulCookingPotResultSlot(playerInventory.player, blockEntity, this.inventory, 8, 124, 55));
        startPlayerInvY = startY * 4 + 12;

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, 9 + row * 9 + col, startX + col * borderSlotSize, startPlayerInvY + row * borderSlotSize));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, startX + col * borderSlotSize, 142));
        }

        this.addDataSlots(cookingPotDataIn);
    }

    private static SoulCookingPotBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof SoulCookingPotBlockEntity) {
            return (SoulCookingPotBlockEntity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteractWithCallable, playerIn, (Block) BlockRegistry.SOUL_COOKING_POT.get());
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        int indexMealDisplay = 6;
        int indexContainerInput = 7;
        int indexOutput = 8;
        int startPlayerInv = indexOutput + 1;
        int endPlayerInv = startPlayerInv + 36;
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            slotStackCopy = slotStack.copy();
            if (index == indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index <= indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                boolean isValidContainer = slotStack.is(ModTags.SERVING_CONTAINERS) || slotStack.is(this.blockEntity.getContainer().getItem());
                if (isValidContainer && !this.moveItemStackTo(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, 0, indexMealDisplay, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, indexContainerInput, indexOutput, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == slotStackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return slotStackCopy;
    }

    public int getCookProgressionScaled() {
        int i = this.cookingPotData.get(0);
        int j = this.cookingPotData.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public boolean isHeated() {
        return this.blockEntity.isHeated();
    }

    public void fillCraftSlotsStackedContents(StackedContents helper) {
        for(int i = 0; i < this.inventory.getSlots(); ++i) {
            helper.accountSimpleStack(this.inventory.getStackInSlot(i));
        }

    }

    public void clearCraftingContent() {
        for(int i = 0; i < 6; ++i) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }

    }

    public boolean recipeMatches(Recipe<? super RecipeWrapper> recipe) {
        return recipe.matches(new RecipeWrapper(this.inventory), this.level);
    }

    public int getResultSlotIndex() {
        return 7;
    }

    public int getGridWidth() {
        return 3;
    }

    public int getGridHeight() {
        return 2;
    }

    public int getSize() {
        return 7;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return BossesDelight.RECIPE_TYPE_SOUL_COOKING;
    }

    public boolean shouldMoveToInventory(int slot) {
        return slot < this.getGridWidth() * this.getGridHeight();
    }
}
