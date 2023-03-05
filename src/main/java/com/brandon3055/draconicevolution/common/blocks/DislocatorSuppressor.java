package com.brandon3055.draconicevolution.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.common.ModBlocks;
import com.brandon3055.draconicevolution.common.lib.References;
import com.brandon3055.draconicevolution.common.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DislocatorSuppressor extends BlockDE {

    // TODO: make this range checked configurable in config file end retrieve it
    public static int suppressor_range = 4;

    // public static Set<BlockPosition> existing_suppressors = Collections.newSetFromMap(new HashMap<>());

    public DislocatorSuppressor() {
        super(Material.redstoneLight);
        this.setCreativeTab(DraconicEvolution.tabBlocksItems);
        this.setHardness(1.5f);
        this.setResistance(10.0f);
        this.setLightLevel(10.0f);
        this.setBlockName(Strings.dislocatorSuppressorName);
        ModBlocks.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + "magnet_suppressor");
    }

    /*
     * @Override public int onBlockPlaced(World worldIn, int x, int y, int z, int side, float subX, float subY, float
     * subZ, int meta) { //super.onBlockPlaced(worldIn, x, y, z, side, subX, subY, subZ, meta); BlockPosition temp = new
     * BlockPosition(x, y, z); if (existing_suppressors.isEmpty()) { existing_suppressors.add(temp); return
     * super.onBlockPlaced(worldIn, x, y, z, side, subX, subY, subZ, meta); } boolean test; for (BlockPosition bp :
     * existing_suppressors) { test = bp.equals(temp); if (test) { return super.onBlockPlaced(worldIn, x, y, z, side,
     * subX, subY, subZ, meta); } } existing_suppressors.add(temp); return super.onBlockPlaced(worldIn, x, y, z, side,
     * subX, subY, subZ, meta); }
     */

}
