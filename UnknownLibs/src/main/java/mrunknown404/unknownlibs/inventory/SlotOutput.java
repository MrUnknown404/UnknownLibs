package mrunknown404.unknownlibs.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class SlotOutput extends Slot {
	public SlotOutput(IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
