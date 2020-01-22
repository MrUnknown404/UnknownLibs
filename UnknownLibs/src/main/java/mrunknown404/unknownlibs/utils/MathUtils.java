package mrunknown404.unknownlibs.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

/**A bunch of math utilities
 * @author -Unknown-
 */
public class MathUtils {
	public static int floor(float value) {
		int i = (int) value;
		return value < i ? i - 1 : i;
	}
	
	public static int ceil(float value) {
		int i = (int) value;
		return value > i ? i + 1 : i;
	}
	
	public static float clamp(float num, float min, float max) {
		return num < min ? min : (num > max ? max : num);
	}
	
	public static int fClamp(int num, int min, int max) {
		return floor(clamp(num, min, max));
	}
	
	/**@param number The number to round
	 * @param decimal The decimal to round to
	 * @return The given number round to the given decimal
	 */
	public static float roundTo(float number, int decimal) {
		double tempDecimal = 1;
		for (int i = 0; i < decimal; i++) {
			tempDecimal *= 10;
		}
		
		return (float) (Math.round(number * tempDecimal) / tempDecimal);
	}
	
	/**@param number The number to normalize
	 * @param min The minimum possible value
	 * @param max The maximum possible value
	 * @return The given number normalized to the given minimum & maximum
	 */
	public static float normalize(float number, float min, float max) {
		if (number > max) {
			return max;
		} else if (number < min) {
			return min;
		}
		
		return (number - min) / (max - min);
	}
	
	public static RayTraceResult rayTrace(Entity entity, double distance, float partialTicks, boolean ignoresLiquid) {
		Vec3d look = entity.getLook(partialTicks);
		Vec3d end = entity.getPositionEyes(partialTicks).addVector(look.x * distance, look.y * distance, look.z * distance);
		return entity.world.rayTraceBlocks(entity.getPositionEyes(partialTicks), end, !ignoresLiquid, false, true);
	}
}
