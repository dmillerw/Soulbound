package me.dmillerw.soulbound.command;

import me.dmillerw.soulbound.helper.BoundHelper;
import me.dmillerw.soulbound.lib.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class CommandSoulbind extends CommandBase {

    @Override
    public String getCommandName() {
        return Reference.COMMAND_SOULBIND;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return Reference.COMMAND_SOULBIND_USAGE;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] arguments) {
        if (!(sender instanceof EntityPlayer))
            return;

        int slot = -1;
        boolean canDrop = false;
        boolean canMove = false;
        boolean keepOnDeath = true;

        for (String arg : arguments) {
            String key;
            String value;

            if (arg.contains(":")) {
                String[] split = arg.split(":");
                key = split[0];
                value = split[1];
            } else if (arg.contains("=")) {
                String[] split = arg.split("=");
                key = split[0];
                value = split[1];
            } else {
                continue;
            }

            if (key.equalsIgnoreCase("slot")) {
                slot = Integer.valueOf(value);
            } if (key.equals("canDrop")) {
                canDrop = Boolean.valueOf(value);
            } else if (key.equalsIgnoreCase("canMove")) {
                canMove = Boolean.valueOf(value);
            } else if (key.equalsIgnoreCase("keepOnDeath")) {
                keepOnDeath = Boolean.valueOf(value);
            }
        }

        ItemStack itemStack;
        if (slot == -1) {
            itemStack = ((EntityPlayer)sender).getHeldItem();
        } else {
            itemStack = ((EntityPlayer)sender).inventory.getStackInSlot(slot);
        }

        BoundHelper.bindItem((EntityPlayer) sender, itemStack, canDrop, canMove, keepOnDeath);
    }
}
