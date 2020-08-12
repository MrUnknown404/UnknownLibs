package mrunknown404.unknownlibs.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @since 1.0.4
 * @author -Unknown-
 */
public class CreativeTab extends CreativeTabs {
	protected final String itemID;
	protected final boolean hasSearchBar;
	private Item item;
	
	/**
	 * @param label Name for display
	 * @param itemID Item ID for the label
	 * @param hasSearchBar Whether or not it has a search bar
	 */
	public CreativeTab(String label, String itemID, boolean hasSearchBar) {
		super(label);
		this.itemID = itemID;
		this.hasSearchBar = hasSearchBar;
		
		if (hasSearchBar) {
			setBackgroundImageName("item_search.png");
		}
	}
	
	@Override
	public ItemStack getTabIconItem() {
		if (item == null) {
			if ((item = Item.getByNameOrId(itemID)) == null) {
				return ItemStack.EMPTY;
			}
		}
		
		return new ItemStack(item);
	}
	
	@Override
	public boolean hasSearchBar() {
		return hasSearchBar;
	}
}
