package com.fez.fezmod;

import com.fez.fezmod.blocks.furnaceblock.FurnaceBlock;
import com.fez.fezmod.blocks.pulverblock.PulverBlock;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static PulverBlock pulverBlock;
	public static FurnaceBlock furnaceBlock;
	
	public static void init() {
		pulverBlock = new PulverBlock();
		furnaceBlock = new FurnaceBlock();
		
	}
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    }

}
