package com.brandon3055.draconicevolution.common.utills;

import com.google.common.collect.AbstractIterator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BlockPosition {

    private final int X;
    private final int Y;
    private final int Z;

    public BlockPosition(int x, int y, int z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public BlockPosition(NBTTagCompound nbtTagCompound) {
        this.X = nbtTagCompound.getInteger("bp_i");
        this.Y = nbtTagCompound.getInteger("bp_j");
        this.Z = nbtTagCompound.getInteger("bp_k");
    }

    public BlockPosition(TileEntity tileEntity) {
        this.X = tileEntity.xCoord;
        this.Y = tileEntity.yCoord;
        this.Z = tileEntity.zCoord;
    }

    public int getX() { return this.X; }

    public int getY() { return this.Y; }

    public int getZ() { return this.Z; }

    public String getBlockUnLocName(World world){
        return world.getBlock(this.X, this.Y, this.Z).getUnlocalizedName();
    }

    public HashMap<BlockPosition, String> debugMatchingArea(World world, Iterable<BlockPosition> iterable){
        HashMap<BlockPosition, String> blocksInArea = new HashMap<>();
        iterable.forEach(i -> blocksInArea.put(i, i.getBlockUnLocName(world)));
        return blocksInArea;
    }

    public HashMap<BlockPosition, String> debugMatchingAreaNoAir(World world, int range){
        HashMap<BlockPosition, String> blocksInArea = new HashMap<>();
        Iterable<BlockPosition> checkedArea = getAllInBox(
                this.addToAll(-range), this.addToAll(range));
        checkedArea.forEach(i -> {
            if (!i.getBlockUnLocName(world).equals("tile.air"))
                blocksInArea.put(i, i.getBlockUnLocName(world));
        });
        return blocksInArea;
    }

    public List<BlockPosition> CheckedArea(Iterable<BlockPosition> iterable){
        List<BlockPosition> returned = new ArrayList<>();
        iterable.forEach(returned::add);

        return returned;
    }

    public BlockPosition addToAll(int in){
        return in == 0 ?
                this:
                new BlockPosition(this.getX() + in, this.getY() + in, this.getZ() + in);
    }

    public BlockPosition add(int x, int y, int z){
        return x == 0 && y == 0 && z == 0 ?
                this :
                new BlockPosition(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    public static Iterable<BlockPosition> getAllInBox(BlockPosition from, BlockPosition to) {
        return getAllInBox(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()), Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
    }

    public static Iterable<BlockPosition> getAllInBox(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        return new Iterable<BlockPosition>() {
            public Iterator<BlockPosition> iterator() {
                return new AbstractIterator<BlockPosition>() {
                    private boolean first = true;
                    private int lastPosX;
                    private int lastPosY;
                    private int lastPosZ;

                    protected BlockPosition computeNext() {
                        if (this.first) {
                            this.first = false;
                            this.lastPosX = x1;
                            this.lastPosY = y1;
                            this.lastPosZ = z1;
                            return new BlockPosition(x1, y1, z1);
                        } else if (this.lastPosX == x2 && this.lastPosY == y2 && this.lastPosZ == z2) {
                            return (BlockPosition)this.endOfData();
                        } else {
                            if (this.lastPosX < x2) {
                                ++this.lastPosX;
                            } else if (this.lastPosY < y2) {
                                this.lastPosX = x1;
                                ++this.lastPosY;
                            } else if (this.lastPosZ < z2) {
                                this.lastPosX = x1;
                                this.lastPosY = y1;
                                ++this.lastPosZ;
                            }

                            return new BlockPosition(this.lastPosX, this.lastPosY, this.lastPosZ);
                        }
                    }
                };
            }
        };
    }
}
