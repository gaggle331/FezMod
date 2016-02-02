package com.fez.fezmod.blocks.furnaceblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerFurnaceBlock implements IGuiHandler{
	private static final int FURNACEBLOCK = 32;
	public static int getGuiID() {return FURNACEBLOCK;}

	// Gets the server side element for the given gui id this should return a container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityFurnaceBlock) {
			TileEntityFurnaceBlock TileEntityFurnaceBlock = (TileEntityFurnaceBlock) tileEntity;
			return new ContainerFurnace(player.inventory, TileEntityFurnaceBlock);
		}
		return null;
	}

	// Gets the client side element for the given gui id this should return a gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityFurnaceBlock) {
			TileEntityFurnaceBlock TileEntityFurnaceBlock = (TileEntityFurnaceBlock) tileEntity;
			return new GuiInventoryFurnace(player.inventory, TileEntityFurnaceBlock);
		}
		return null;
	}
}