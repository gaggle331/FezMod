package com.fez.fezmod;


import com.fez.fezmod.items.golddust.GoldDust;
import com.fez.fezmod.items.irondust.IronDust;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static IronDust ironDust; 
	public static GoldDust goldDust; 
	
	public static void init() {
		ironDust = new IronDust();
		goldDust = new GoldDust();
	}

    @SideOnly(Side.CLIENT)
    public static void initModels() {
    }
	
}
