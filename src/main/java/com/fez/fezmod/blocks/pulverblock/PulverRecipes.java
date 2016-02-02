package com.fez.fezmod.blocks.pulverblock;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fez.fezmod.items.golddust.GoldDust;
import com.fez.fezmod.items.irondust.IronDust;
import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PulverRecipes 
{
    private static final PulverRecipes PulverBase = new PulverRecipes();
    /** The list of Pulver results. */
    private final Map pulverList = Maps.newHashMap();
    private final Map pulverTimeList = Maps.newHashMap();

    public static PulverRecipes instance()
    {
        return PulverBase;
    }

    private PulverRecipes()
    {
        addPulverRecipe(
              new ItemStack(Item.getItemFromBlock(Blocks.iron_ore)), 
              new ItemStack(Item.getByNameOrId("fezmod:irondust")), 10);
        addPulverRecipe(
              new ItemStack(Item.getItemFromBlock(Blocks.gold_ore)), 
              new ItemStack(Item.getByNameOrId("fezmod:golddust")), 10);
    }

    public void addPulverRecipe(ItemStack parItemStackIn, 
          ItemStack parItemStackOut, int parPulverTime)
    {
        pulverList.put(parItemStackIn, parItemStackOut);
        pulverTimeList.put(parItemStackIn, parPulverTime);
    }

    /**
     * Returns the Pulver result of an item.
     */
    public ItemStack getPulverResult(ItemStack parItemStack)
    {
        Iterator iterator = pulverList.entrySet().iterator();
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

    public Map getPulverList()
    {
        return pulverList;
    }

    public int getPulverTime(ItemStack parItemStack)
    {
        Iterator iterator = pulverTimeList.entrySet().iterator();
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

        return (Integer)entry.getValue();
    }
}
