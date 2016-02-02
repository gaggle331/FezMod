package com.fez.fezmod;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class ModRecipes implements IRecipe{
	
	private final ItemStack recipeInput;
	private final ItemStack recipeOutput;

	
	public ModRecipes(ItemStack output, ItemStack input){
		this.recipeInput = input;
		this.recipeOutput = output;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.recipeOutput.copy();
	}

	@Override
	public int getRecipeSize() {
		return 0;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		return null;
	}

}
