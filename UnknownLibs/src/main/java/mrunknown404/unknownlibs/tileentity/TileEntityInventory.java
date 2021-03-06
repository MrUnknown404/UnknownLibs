package mrunknown404.unknownlibs.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * @since 1.0.0
 * @author -Unknown-
 */
public abstract class TileEntityInventory extends TileEntity implements IInventory {
	protected NonNullList<ItemStack> inv;
	protected String customName;
	
	/** @param size The inventory size */
	public TileEntityInventory(int size) {
		this.inv = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
	}
	
	@Override
	public boolean hasCustomName() {
		return customName != null && !customName.isEmpty();
	}
	
	private void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public int getSizeInventory() {
		return inv.size();
	}
	
	@Override
	public boolean isEmpty() {
		for (ItemStack item : inv) {
			if (!item.isEmpty()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return inv.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(inv, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inv, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack item = inv.get(index);
		inv.set(index, stack);
		
		if (item.getCount() > getInventoryStackLimit()) {
			item.setCount(getInventoryStackLimit());
		}
		
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(pos) != this ? false : player.getDistanceSq(new BlockPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)) <= 64;
	}
	
	@Override public void openInventory(EntityPlayer player) {}
	@Override public void closeInventory(EntityPlayer player) {}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound c) {
		super.writeToNBT(c);
		ItemStackHelper.saveAllItems(c, inv);
		if (hasCustomName()) {
			c.setString("CustomName", customName);
		}
		
		return c;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound c) {
		super.readFromNBT(c);
		ItemStackHelper.loadAllItems(c, inv = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY));
		
		if (c.hasKey("CustomName", 8)) {
			setCustomName(c.getString("CustomName"));
		}
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		return writeToNBT(nbt);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	@Override public void setField(int id, int value) {}
	@Override public int getField(int id) { return 0; }
	@Override public int getFieldCount() { return 0; }
	
	@Override
	public void clear() {
		inv.clear();
	}
}
