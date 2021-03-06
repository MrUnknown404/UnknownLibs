package mrunknown404.unknownlibs.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class EntityRenderHelper {
	/** @since 1.0.0 */
	public static void registerRender(Class<? extends EntityLiving> entityClazz, RenderBase<? super EntityLiving> render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClazz, new EntityRenderFactory(render));
	}
	
	/** @since 1.0.0 */
	private static class EntityRenderFactory implements IRenderFactory<EntityLiving> {
		private final RenderBase<? super EntityLiving> render;
		
		private EntityRenderFactory(RenderBase<? super EntityLiving> render) {
			this.render = render;
		}
		
		@Override
		public Render<? super EntityLiving> createRenderFor(RenderManager manager) {
			return render.createNew(manager);
		}
	}
}
