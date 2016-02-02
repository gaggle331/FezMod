package com.fez.fezmod.items.golddust;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GoldDust extends Item{
    public GoldDust() {
        setRegistryName("golddust");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("golddust");     // Used for localization (en_US.lang)
        GameRegistry.registerItem(this);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}
