package com.fez.fezmod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {

	public static void init() {
		GameRegistry.addRecipe(new ItemStack(ModBlocks.pulverBlock),
		    	"AAA",
		    	"A A",
		    	"AAA",
		    	'A', Items.iron_ingot
		    	);
	}

}
