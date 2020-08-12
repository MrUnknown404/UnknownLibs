package mrunknown404.unknownlibs.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class SlotOutput extends Slot {
	/**
	 * @param inv Slot's {@link IInventory}
	 * @param index Slot ID
	 * @param x X coordinate of the slot
	 * @param y Y coordinate of the slot
	 */
	public SlotOutput(IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
