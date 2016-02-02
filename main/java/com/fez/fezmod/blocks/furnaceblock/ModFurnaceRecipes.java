package com.fez.fezmod.blocks.furnaceblock;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fez.fezmod.ModItems;
import com.fez.fezmod.items.golddust.GoldDust;
import com.fez.fezmod.items.irondust.IronDust;
import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModFurnaceRecipes 
{
    private static final ModFurnaceRecipes FurnaceBase = new ModFurnaceRecipes();
    /** The list of Furnace results. */
    private final Map furnaceList = Maps.newHashMap();
    private final Map timeList = Maps.newHashMap();

    public static ModFurnaceRecipes instance()
    {
        return FurnaceBase;
    }

    private ModFurnaceRecipes()
    {
        addFurnaceRecipe(
              new ItemStack(Item.getByNameOrId("fezmod:irondust")), 
              new ItemStack(Items.iron_ingot), 10000);
        addFurnaceRecipe(
                new ItemStack(Item.getByNameOrId("fezmod:golddust")), 
                new ItemStack(Items.gold_ingot), 10);
    }

    public void addFurnaceRecipe(ItemStack parItemStackIn, 
          ItemStack parItemStackOut, int cookTimeInTicks)
    {
        furnaceList.put(parItemStackIn, parItemStackOut);
        timeList.put(parItemStackIn, cookTimeInTicks);
    }

    /**
     * Returns the Furnace result of an item.
     */
    public ItemStack getFurnaceResult(ItemStack parItemStack)
    {
        Iterator iterator = furnaceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, (ItemStack)entry
              .getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack parItemStack1, 
          ItemStack parItemStack2)
    {
        return parItemStack2.getItem() == parItemStack1.getItem() 
              && (parItemStack2.getMetadata() == 32767 
              || parItemStack2.getMetadata() == parItemStack1
              .getMetadata());
    }

    public Map getFurnaceList()
    {
        return furnaceList;
    }

    public int getFurnaceCookTime(ItemStack parItemStack)
    {
        Iterator iterator = timeList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0;
            }

            entry = (Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, 
              (ItemStack)entry.getKey()));

        return (Integer) (entry.getValue());
    }
}
