package mrunknown404.unknownlibs.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegisterHelper {
	public static void registerEntities(List<EntityInfo> entities, Object mod, String modid) {
		for (EntityInfo info : entities) {
			registerEntity(mod, modid, info.name, info.clazz, info.id, info.range, info.color1, info.color2);
		}
	}
	
	private static void registerEntity(Object mod, String modid, String name, Class<? extends Entity> clazz, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(modid, name), clazz, name, id, mod, range, 1, true, color1, color2);
	}
}
