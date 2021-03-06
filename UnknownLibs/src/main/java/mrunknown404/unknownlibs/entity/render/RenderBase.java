package mrunknown404.unknownlibs.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class RenderBase<T extends EntityLiving> extends RenderLiving<T> {
	
	private final ModelBase model;
	private final ResourceLocation resourceLoc;
	private final float shadow;
	
	protected RenderBase(RenderManager manager, ModelBase model, ResourceLocation resourceLoc, float shadow) {
		super(manager, model, shadow);
		this.model = model;
		this.shadow = shadow;
		this.resourceLoc = resourceLoc;
	}
	
	/** @since 1.0.0 */
	public RenderBase<T> createNew(RenderManager manager) {
		return new RenderBase<T>(manager, model, resourceLoc, shadow);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return resourceLoc;
	}
}
