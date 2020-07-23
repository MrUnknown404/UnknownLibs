package mrunknown404.unknownlibs.utils;

import net.minecraft.item.Item;

/**
 * @since 1.0.2
 * @author -Unknown-
 */
public interface ICommonProxy {
	void preInit();
	void init();
	void postInit();
	
	@SuppressWarnings("unused")
	default void registerItemRenderer( Item item, int meta, String id) {}
}
