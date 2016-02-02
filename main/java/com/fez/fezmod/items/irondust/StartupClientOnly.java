package com.fez.fezmod.items.irondust;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly
{
	public static void preInitClientOnly()
	{
	}

	public static void initClientOnly()
	{
		// This is currently necessary in order to make your block render properly when it is an item (i.e. in the inventory
		//   or in your hand or thrown on the ground).
		// Minecraft knows to look for the item model based on the GameRegistry.registerBlock.  However the registration of
		//  the model for each item is normally done by RenderItem.registerItems(), and this is not currently aware
		//   of any extra items you have created.  Hence you have to do it manually.  This will probably change in future.
		// It must be done in the init phase, not preinit, and must be done on client only.

		Item itemIronDust = GameRegistry.findItem("fezmod", "irondust");
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("fezmod:irondust", "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemIronDust, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	}

	public static void postInitClientOnly()
	{
	}
}
