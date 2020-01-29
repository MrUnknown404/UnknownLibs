package mrunknown404.unknownlibs.utils;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
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
	public static int ceil(float value) {
		int i = (int) value;
		return value > i ? i + 1 : i;
	}
	
	/** @since 1.0.0 */
	public static float clamp(float num, float min, float max) {
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
	
	/** @since 1.0.0 */
	public static RayTraceResult rayTrace(Entity entity, double distance, float partialTicks, boolean ignoresLiquid) {
		Vec3d look = entity.getLook(partialTicks);
		Vec3d end = entity.getPositionEyes(partialTicks).addVector(look.x * distance, look.y * distance, look.z * distance);
		return entity.world.rayTraceBlocks(entity.getPositionEyes(partialTicks), end, !ignoresLiquid, false, true);
	}
	
	/** @since 1.0.3 */
	public static RayTraceResult rayTraceBlocksIgnore(World w, Vec3d vec31, Vec3d vec32, List<Block> blocksToIgnore) {
		if (!Double.isNaN(vec31.x) && !Double.isNaN(vec31.y) && !Double.isNaN(vec31.z)) {
			if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
				int i = MathHelper.floor(vec32.x);
				int j = MathHelper.floor(vec32.y);
				int k = MathHelper.floor(vec32.z);
				int l = MathHelper.floor(vec31.x);
				int i1 = MathHelper.floor(vec31.y);
				int j1 = MathHelper.floor(vec31.z);
				BlockPos blockpos = new BlockPos(l, i1, j1);
				IBlockState iblockstate = w.getBlockState(blockpos);
				Block block = iblockstate.getBlock();
				
				if ((iblockstate.getCollisionBoundingBox(w, blockpos) != Block.NULL_AABB) && !blocksToIgnore.contains(iblockstate.getBlock()) &&  block.canCollideCheck(iblockstate, false)) {
					RayTraceResult raytraceresult = iblockstate.collisionRayTrace(w, blockpos, vec31, vec32);
					
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
					
					EnumFacing enumfacing;
					
					if (d3 < d4 && d3 < d5) {
						enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
						vec31 = new Vec3d(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
					} else if (d4 < d5) {
						enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
						vec31 = new Vec3d(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
					} else {
						enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
						vec31 = new Vec3d(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
					}
					
					l = MathHelper.floor(vec31.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
					i1 = MathHelper.floor(vec31.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
					j1 = MathHelper.floor(vec31.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
					blockpos = new BlockPos(l, i1, j1);
					IBlockState iblockstate1 = w.getBlockState(blockpos);
					Block block1 = iblockstate1.getBlock();
					
					if (iblockstate1.getMaterial() == Material.PORTAL || iblockstate1.getCollisionBoundingBox(w, blockpos) != Block.NULL_AABB) {
						if (!blocksToIgnore.contains(iblockstate1.getBlock()) && block1.canCollideCheck(iblockstate1, false)) {
							RayTraceResult raytraceresult1 = iblockstate1.collisionRayTrace(w, blockpos, vec31, vec32);
							
							if (raytraceresult1 != null) {
								return raytraceresult1;
							}
						} else {
							raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
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
