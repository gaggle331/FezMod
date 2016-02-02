package com.fez.fezmod.blocks.pulverblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerPulverBlock implements IGuiHandler{
	private static final int PULVERBLOCK = 31;
	public static int getGuiID() {return PULVERBLOCK;}

	// Gets the server side element for the given gui id this should return a container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityPulverBlock) {
			TileEntityPulverBlock TileEntityPulverBlock = (TileEntityPulverBlock) tileEntity;
			return new ContainerPulver(player.inventory, TileEntityPulverBlock);
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
		if (tileEntity instanceof TileEntityPulverBlock) {
			TileEntityPulverBlock TileEntityPulverBlock = (TileEntityPulverBlock) tileEntity;
			return new GuiInventoryPulver(player.inventory, TileEntityPulverBlock);
		}
		return null;
	}
}