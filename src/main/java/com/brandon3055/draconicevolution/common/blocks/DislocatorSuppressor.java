package com.brandon3055.draconicevolution.common.blocks;

import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.common.ModBlocks;
import com.brandon3055.draconicevolution.common.lib.References;
import com.brandon3055.draconicevolution.common.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class DislocatorSuppressor extends BlockDE {

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
    public void registerBlockIcons(final IIconRegister iconRegister){
        blockIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + "magnet_suppressor");
    }
}
