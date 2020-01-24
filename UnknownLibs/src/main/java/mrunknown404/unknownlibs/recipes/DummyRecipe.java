package mrunknown404.unknownlibs.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	private final ItemStack output;
	
	public DummyRecipe(ItemStack output) {
		this.output = output;
	}
	
	public static IRecipe from(IRecipe other) {
		return new DummyRecipe(other.getRecipeOutput()).setRegistryName(other.getRegistryName());
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return false;
	}
}
