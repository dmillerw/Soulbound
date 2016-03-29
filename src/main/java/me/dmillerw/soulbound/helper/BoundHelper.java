package me.dmillerw.soulbound.helper;

import me.dmillerw.soulbound.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

/**
 * @author dmillerw
 */
public class BoundHelper {

    public static class Data {

        public UUID owner;

        // Config
        public boolean canDrop;
        public boolean canMove;
        public boolean keepOnDeath;

        public boolean isOwner(EntityPlayer player) {
            return player.getGameProfile().getId().equals(owner);
        }
    }

    public static void bindItem(EntityPlayer player, ItemStack itemStack, boolean canDrop, boolean canMove, boolean keepOnDeath) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(Reference.NBT_KEY_OWNER, player.getGameProfile().getId().toString());
        tag.setBoolean(Reference.NBT_KEY_CAN_DROP, canDrop);
        tag.setBoolean(Reference.NBT_KEY_CAN_MOVE, canMove);
        tag.setBoolean(Reference.NBT_KEY_KEEP_ON_DEATH, keepOnDeath);

        NBTHelper.getOrCreate(itemStack).setTag(Reference.NBT_KEY_SOULBOUND, tag);
    }

    public static Data getData(ItemStack itemStack) {
        if (itemStack == null)
            return null;

        NBTTagCompound tag = NBTHelper.getOrCreate(itemStack);
        if (!tag.hasKey(Reference.NBT_KEY_SOULBOUND)) {
            return null;
        }

        tag = tag.getCompoundTag(Reference.NBT_KEY_SOULBOUND);

        Data data = new Data();
        data.owner = UUID.fromString(NBTHelper.getString(tag, Reference.NBT_KEY_OWNER));
        data.canDrop = NBTHelper.getBoolean(tag, Reference.NBT_KEY_CAN_DROP);
        data.canMove = NBTHelper.getBoolean(tag, Reference.NBT_KEY_CAN_MOVE);
        data.keepOnDeath = NBTHelper.getBoolean(tag, Reference.NBT_KEY_KEEP_ON_DEATH);
        return data;
    }
}
