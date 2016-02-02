package com.fez.fezmod.blocks.furnaceblock;

import com.fez.fezmod.FezMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FurnaceBlock extends BlockContainer{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool BURNINGBOOL = PropertyBool.create("burning");
	//public static final PropertyInteger BURNING_SIDES_COUNT = PropertyInteger.create("burning_sides_count", 0, 4);
	
	
    public FurnaceBlock() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        //this.setBlockBounds(1/16.0F, 0, 1/16.0F, 15/16.0F, 8/16.0F, 15/16.0F);
       setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNINGBOOL, false));
    }
    
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFurnaceBlock();
	}
	

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Uses the gui handler registered to your mod to open the gui for the given gui id
		// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;

		playerIn.openGui(FezMod.instance, GuiHandlerFurnaceBlock.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		IInventory inventory = worldIn.getTileEntity(pos) instanceof IInventory ? (IInventory)worldIn.getTileEntity(pos) : null;

		if (inventory != null){
			// For each slot in the inventory
			for (int i = 0; i < inventory.getSizeInventory(); i++){
				// If the slot is not empty
				if (inventory.getStackInSlot(i) != null)
				{
					// Create a new entity item with the item stack in the slot
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));

					// Apply some random motion to the item
					float multiplier = 0.1f;
					float motionX = worldIn.rand.nextFloat() - 0.5f;
					float motionY = worldIn.rand.nextFloat() - 0.5f;
					float motionZ = worldIn.rand.nextFloat() - 0.5f;

					item.motionX = motionX * multiplier;
					item.motionY = motionY * multiplier;
					item.motionZ = motionZ * multiplier;

					// Spawn the item in the world
					worldIn.spawnEntityInWorld(item);
				}
			}

			// Clear the inventory so nothing else (such as another mod) can do anything with the items
			inventory.clear();
		}

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}

	
	  @Override
		public int getLightValue(IBlockAccess world, BlockPos pos) {
			int lightValue = 0;
			IBlockState blockState = getActualState(getDefaultState(), world, pos);
			boolean burning = (boolean)blockState.getValue(BURNINGBOOL);

	   	if (burning == false) {
				lightValue = 0;
			} else {
				lightValue = 15;
			}
			lightValue = MathHelper.clamp_int(lightValue, 0, 15);
			return lightValue;
		}
		
	  
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileEntityFurnaceBlock) {
			TileEntityFurnaceBlock tileFurnaceBlock = (TileEntityFurnaceBlock)tileEntity;
			boolean burning = false;
			int burningSlots = tileFurnaceBlock.numberOfBurningFuelSlots();
			if (burningSlots > 0) burning = true; 
			return getDefaultState().withProperty(BURNINGBOOL, burning);
		}
		return state;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState()
						.withProperty(FACING, EnumFacing.getFront(meta & 7))
						.withProperty(BURNINGBOOL, (meta & 8) != 0);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
    
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
	}
	
    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
            (float) (entity.posX - clickedBlock.getX()),
            (float) (entity.posY - clickedBlock.getY()),
            (float) (entity.posZ - clickedBlock.getZ()));
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
    }

	// used by the renderer to control lighting and visibility of other blocks.
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	// used by the renderer to control lighting and visibility of other blocks, also by
	// (eg) wall or fence to control whether the fence joins itself to this block
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isFullCube() {
		return true;
	}
    
	@Override
	public int getRenderType(){
		return 3;
	}
	
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, FACING, BURNINGBOOL);
    }

}
