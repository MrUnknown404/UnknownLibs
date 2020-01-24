package mrunknown404.unknownlibs.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class NBTUtils {
	public static ItemStack addLore(ItemStack item, String... lore) {
		NBTTagCompound tag = item.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		if (!tag.hasKey("display", Constants.NBT.TAG_COMPOUND)) {
			tag.setTag("display", new NBTTagCompound());
		}
		
		NBTTagList l = new NBTTagList();
		for (String s : lore) {
			l.appendTag(new NBTTagString(s));
		}
		
		tag.getCompoundTag("display").setTag("Lore", l);
		item.setTagCompound(tag);
		return item;
	}
}
