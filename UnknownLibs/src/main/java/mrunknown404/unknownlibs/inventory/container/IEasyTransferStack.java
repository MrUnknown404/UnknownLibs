package mrunknown404.unknownlibs.inventory.container;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @since 1.1.0
 * @author -Unknown-
 */
public interface IEasyTransferStack {
	/** Used to make {@link Container#transferStackInSlot} easier
	 * @param player The player using the container
	 * @param index The slot to check
	 */
	default ItemStack transferStack(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = getSlots().get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if (index <= getAmountOfInputSlots()) {
				if (!IMergeItemStack(stack1, getAmountOfInputSlots(), 36 + getAmountOfSlots(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!IMergeItemStack(stack1, 0, getAmountOfSlots(), false)) {
				return ItemStack.EMPTY;
			}
			
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if (stack1.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, stack1);
		}
		
		return stack;
	}
	
	/** @return Amount of input slots in the container */
	int getAmountOfInputSlots();
	/** @return Amount of output slots in the container */
	int getAmountOfOutputSlots();
	
	/** Used internally */
	default int getAmountOfSlots() {
		return getAmountOfInputSlots() + getAmountOfOutputSlots();
	}
	
	/** @return The inventory */
	List<Slot> getSlots();
	/** @return replace this with {@link Container#mergeItemStack} */
	boolean IMergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection);
}
