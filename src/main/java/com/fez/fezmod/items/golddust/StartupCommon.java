package com.fez.fezmod.items.golddust;




import com.fez.fezmod.FezMod;
import com.fez.fezmod.GuiHandlerRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class StartupCommon
{
	public static Item goldDust;  // this holds the unique instance of your item

	public static void preInitCommon()
	{
		// each instance of your item should have a name that is unique within your mod.  use lower case.
		goldDust = new GoldDust().setUnlocalizedName("golddust");
		GameRegistry.registerItem(goldDust, "golddust");
	}

	public static void initCommon()
	{
	}

	public static void postInitCommon()
	{
	}
}
