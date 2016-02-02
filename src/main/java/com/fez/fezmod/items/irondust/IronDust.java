package com.fez.fezmod.items.irondust;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class IronDust extends Item{
    public IronDust() {
        setRegistryName("irondust");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("irondust");     // Used for localization (en_US.lang)
        GameRegistry.registerItem(this);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}
