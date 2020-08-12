package mrunknown404.unknownlibs.entity;

import net.minecraft.entity.EntityLiving;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
@SuppressWarnings("javadoc")
public class EntityInfo {
	public final String name;
	public final Class<? extends EntityLiving> clazz;
	public final int id, range, color1, color2;
	
	/** Creates an EntityInfo to be used in entity registering
	 * @param name Name of the Entity
	 * @param clazz Class of the entity
	 * @param id ID of the entity
	 * @param range Range of the entity
	 * @param color1 Color1 of the entity egg
	 * @param color2 Color2 of the entity egg
	 */
	public EntityInfo(String name, Class<? extends EntityLiving> clazz, int id, int range, int color1, int color2) {
		this.name = name;
		this.clazz = clazz;
		this.id = id;
		this.range = range;
		this.color1 = color1;
		this.color2 = color2;
	}
}
