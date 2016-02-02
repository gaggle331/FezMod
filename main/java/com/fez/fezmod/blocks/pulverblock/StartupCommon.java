package com.fez.fezmod.blocks.pulverblock;




import com.fez.fezmod.FezMod;
import com.fez.fezmod.GuiHandlerRegistry;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class StartupCommon
{
	public static Block pulverBlock;  // this holds the unique instance of your block

	public static void preInitCommon()
	{
		// each instance of your block should have a name that is unique within your mod.  use lower case.
		pulverBlock = new PulverBlock().setUnlocalizedName("pulverblock");
		GameRegistry.registerBlock(pulverBlock, "pulverblock");
		// Each of your tile entities needs to be registered with a name that is unique to your mod.
		GameRegistry.registerTileEntity(TileEntityPulverBlock.class, "tile_pulverblock");
		// you don't need to register an item corresponding to the block, GameRegistry.registerBlock does this automatically.

		// You need to register a GUIHandler for the container.  However there can be only one handler per mod, so for the purposes
		//   of this project, we create a single GuiHandlerRegistry for all examples.
		// We register this GuiHandlerRegistry with the NetworkRegistry, and then tell the GuiHandlerRegistry about
		//   each example's GuiHandler, in this case GuiHandlerMBE30, so that when it gets a request from NetworkRegistry,
		//   it passes the request on to the correct example's GuiHandler.
		NetworkRegistry.INSTANCE.registerGuiHandler(FezMod.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerPulverBlock(), GuiHandlerPulverBlock.getGuiID());
	}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
