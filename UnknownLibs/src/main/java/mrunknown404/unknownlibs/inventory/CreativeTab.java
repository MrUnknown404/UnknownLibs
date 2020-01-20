package mrunknown404.unknownlibs.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
	
	protected final Item item;
	protected final boolean hasSearchBar;
	
	public CreativeTab(String label, Item item, boolean hasSearchBar) {
		super(label);
		this.item = item;
		this.hasSearchBar = hasSearchBar;
		
		if (hasSearchBar) {
			setBackgroundImageName("item_search.png");
		}
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(item);
	}
	
	@Override
	public boolean hasSearchBar() {
		return hasSearchBar;
	}
}
