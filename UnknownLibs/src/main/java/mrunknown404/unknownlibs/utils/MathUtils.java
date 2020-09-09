package mrunknown404.unknownlibs.utils;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

/**A bunch of math utilities
 * @since 1.0.0
 * @author -Unknown-
 */
public class MathUtils {
	/** @since 1.0.0 */
	public static int floor(float value) {
		int i = (int) value;
		return value < i ? i - 1 : i;
	}
	
	/** @since 1.0.0 */
	public static int floor(double value) {
		int i = (int) value;
		return value < i ? i - 1 : i;
	}
	
	/** @since 1.0.0 */
	public static int ceil(float value) {
		int i = (int) value;
		return value > i ? i + 1 : i;
	}
	
	/** @since 1.0.0 */
	public static int ceil(double value) {
		int i = (int) value;
		return value > i ? i + 1 : i;
	}
	
	/** @since 1.0.0 */
	public static float clamp(float num, float min, float max) {
		return num < min ? min : (num > max ? max : num);
	}
	
	/** @since 1.0.0 */
	public static double clamp(double num, float min, float max) {
		return num < min ? min : (num > max ? max : num);
	}
	
	/** @since 1.0.0 */
	public static int fClamp(int num, int min, int max) {
		return floor(clamp(num, min, max));
	}
	
	/**@since 1.0.0
	 * @param number The number to round
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
	
	/**@since 1.0.0
	 * @param number The number to round
	 * @param decimal The decimal to round to
	 * @return The given number round to the given decimal
	 */
	public static float roundTo(double number, int decimal) {
		return roundTo((float) number, decimal);
	}
	
	/**@since 1.0.0
	 * @param number The number to normalize
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
	
	/**@since 1.0.0
	 * @param number The number to normalize
	 * @param min The minimum possible value
	 * @param max The maximum possible value
	 * @return The given number normalized to the given minimum & maximum
	 */
	public static double normalize(double number, double min, double max) {
		return normalize((float) number, (float) min, (float) max);
	}
	
	/** @since 1.0.0 */
	public static RayTraceResult rayTrace(Entity entity, double distance, float partialTicks, FluidMode fluid) {
		Vector3d look = entity.getLook(partialTicks);
		Vector3d end = entity.getEyePosition(partialTicks).add(look.x * distance, look.y * distance, look.z * distance);
		return entity.world.rayTraceBlocks(new RayTraceContext(look, end, BlockMode.OUTLINE, fluid, entity));
	}
	
	/** @since 1.0.0 */
	public static RayTraceResult rayTraceBlocksIgnore(World w, Vector3d vec31, Vector3d vec32, List<Block> blocksToIgnore) {
		if (!Double.isNaN(vec31.x) && !Double.isNaN(vec31.y) && !Double.isNaN(vec31.z)) {
			if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
				int i = MathHelper.floor(vec32.x);
				int j = MathHelper.floor(vec32.y);
				int k = MathHelper.floor(vec32.z);
				int l = MathHelper.floor(vec31.x);
				int i1 = MathHelper.floor(vec31.y);
				int j1 = MathHelper.floor(vec31.z);
				BlockPos blockpos = new BlockPos(l, i1, j1);
				BlockState iblockstate = w.getBlockState(blockpos);
				Block block = iblockstate.getBlock();
				
				if ((iblockstate.getCollisionShape(w, blockpos) != VoxelShapes.empty()) && !blocksToIgnore.contains(iblockstate.getBlock())) { //&& block.canCollideCheck(iblockstate, false)) {
					RayTraceResult raytraceresult = iblockstate.getCollisionShape(w, blockpos).rayTrace(vec31, vec32, blockpos);
					
					if (raytraceresult != null) {
						return raytraceresult;
					}
				}
				
				RayTraceResult raytraceresult2 = null;
				int k1 = 200;
				
				while (k1-- >= 0) {
					if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
						return null;
					}
					
					if (l == i && i1 == j && j1 == k) {
						return raytraceresult2;
					}
					
					boolean flag2 = true;
					boolean flag = true;
					boolean flag1 = true;
					double d0 = 999;
					double d1 = 999;
					double d2 = 999;
					
					if (i > l) {
						d0 = l + 1;
					} else if (i < l) {
						d0 = l;
					} else {
						flag2 = false;
					}
					
					if (j > i1) {
						d1 = i1 + 1;
					} else if (j < i1) {
						d1 = i1;
					} else {
						flag = false;
					}
					
					if (k > j1) {
						d2 = j1 + 1;
					} else if (k < j1) {
						d2 = 1;
					} else {
						flag1 = false;
					}
					
					double d3 = 999;
					double d4 = 999;
					double d5 = 999;
					double d6 = vec32.x - vec31.x;
					double d7 = vec32.y - vec31.y;
					double d8 = vec32.z - vec31.z;
					
					if (flag2) {
						d3 = (d0 - vec31.x) / d6;
					}
					
					if (flag) {
						d4 = (d1 - vec31.y) / d7;
					}
					
					if (flag1) {
						d5 = (d2 - vec31.z) / d8;
					}
					
					if (d3 == -0) {
						d3 = -1.0e-4;
					}
					
					if (d4 == -0) {
						d4 = -1.0e-4;
					}
					
					if (d5 == -0) {
						d5 = -1.0e-4;
					}
					
					Direction enumfacing;
					
					if (d3 < d4 && d3 < d5) {
						enumfacing = i > l ? Direction.WEST : Direction.EAST;
						vec31 = new Vector3d(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
					} else if (d4 < d5) {
						enumfacing = j > i1 ? Direction.DOWN : Direction.UP;
						vec31 = new Vector3d(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
					} else {
						enumfacing = k > j1 ? Direction.NORTH : Direction.SOUTH;
						vec31 = new Vector3d(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
					}
					
					l = MathHelper.floor(vec31.x) - (enumfacing == Direction.EAST ? 1 : 0);
					i1 = MathHelper.floor(vec31.y) - (enumfacing == Direction.UP ? 1 : 0);
					j1 = MathHelper.floor(vec31.z) - (enumfacing == Direction.SOUTH ? 1 : 0);
					blockpos = new BlockPos(l, i1, j1);
					BlockState iblockstate1 = w.getBlockState(blockpos);
					Block block1 = iblockstate1.getBlock();
					
					if (iblockstate1.getMaterial() == Material.PORTAL || iblockstate1.getCollisionShape(w, blockpos) != VoxelShapes.empty()) {
						if (!blocksToIgnore.contains(iblockstate1.getBlock())) { // && block1.canCollideCheck(iblockstate1, false)) {
							RayTraceResult raytraceresult1 = iblockstate1.getCollisionShape(w, blockpos).rayTrace(vec31, vec32, blockpos);
							
							if (raytraceresult1 != null) {
								return raytraceresult1;
							}
						} else {
							raytraceresult2 = BlockRayTraceResult.createMiss(vec31, enumfacing, blockpos);
						}
					}
				}
				
				return raytraceresult2;
			}
		}
		
		return null;
	}
	
	/** @since 1.0.3 */
	public static boolean within(float value, float min, float max) {
		return value >= min && value <= max;
	}
}
