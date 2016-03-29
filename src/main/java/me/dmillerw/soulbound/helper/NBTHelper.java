package me.dmillerw.soulbound.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author dmillerw
 */
public class NBTHelper {

    public static NBTTagCompound getOrCreate(ItemStack itemStack) {
        NBTTagCompound tag = itemStack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            itemStack.setTagCompound(tag);
        }
        return tag;
    }

    public static String getString(NBTTagCompound tag, String key) {
        if (!tag.hasKey(key)) {
            throw new IllegalStateException("[Soulbound]: Tried to construct Soulbound data from invalid NBT tag");
        }
        return tag.getString(key);
    }

    public static long getLong(NBTTagCompound tag, String key) {
        if (!tag.hasKey(key)) {
            throw new IllegalStateException("[Soulbound]: Tried to construct Soulbound data from invalid NBT tag");
        }
        return tag.getLong(key);
    }

    public static boolean getBoolean(NBTTagCompound tag, String key) {
        if (!tag.hasKey(key)) {
            throw new IllegalStateException("[Soulbound]: Tried to construct Soulbound data from invalid NBT tag");
        }
        return tag.getBoolean(key);
    }
}
