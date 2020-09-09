package mrunknown404.unknownlibs.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class NBTUtils {
	/** @since 1.0.0 */
	public static ItemStack addLore(ItemStack item, String... lore) {
		CompoundNBT tag = item.getTag();
		if (tag == null) {
			tag = new CompoundNBT();
		}
		
		if (!tag.hasUniqueId("display")) {
			tag.put("display", new CompoundNBT());
		}
		
		ListNBT l = new ListNBT();
		for (String s : lore) {
			l.add(StringNBT.valueOf(s));
		}
		
		tag.getCompound("display").put("Lore", l);
		item.setTag(tag);
		return item;
	}
}
