package mrunknown404.unknownlibs.utils;

import net.minecraft.item.Item;

public interface CommonProxy {
	void preInit();
	void init();
	void postInit();
	
	default void registerItemRenderer(Item item, int meta, String id) {}
}
