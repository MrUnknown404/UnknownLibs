package mrunknown404.unknownlibs.inventory.container;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @since 1.1.0
 * @author -Unknown-
 */
public interface IEasyTransferStack {
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
	
	int getAmountOfInputSlots();
	int getAmountOfOutputSlots();
	
	default int getAmountOfSlots() {
		return getAmountOfInputSlots() + getAmountOfOutputSlots();
	}
	
	List<Slot> getSlots();
	boolean IMergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection);
}
