package me.dmillerw.soulbound.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import me.dmillerw.soulbound.helper.BoundHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author dmillerw
 */
public class EventHandler {

    private static Map<UUID, List<ItemStack>> toReAdd = Maps.newHashMap();

    @SubscribeEvent
    public void onPlayerDropItem(ItemTossEvent event) {
        BoundHelper.Data data = BoundHelper.getData(event.entityItem.getEntityItem());
        if (data == null)
            return;

        ItemStack item = event.entityItem.getEntityItem().copy();

        if (data.isOwner(event.player) && !data.canDrop) {
            event.setCanceled(true);
            if (event.player.getHeldItem() == null) {
                event.player.inventory.setInventorySlotContents(event.player.inventory.currentItem, item);
            } else {
                event.player.inventory.addItemStackToInventory(item);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDrops(PlayerDropsEvent event) {
        ArrayList<EntityItem> newDrops = Lists.newArrayList();
        ArrayList<ItemStack> toSave = Lists.newArrayList();

        for (EntityItem drop : event.drops) {
            BoundHelper.Data data = BoundHelper.getData(drop.getEntityItem());
            if (data == null)
                continue;

            ItemStack item = drop.getEntityItem().copy();

            if (data.isOwner(event.entityPlayer) && data.keepOnDeath) {
                toSave.add(item);
                event.entityPlayer.inventory.addItemStackToInventory(item);
                drop.setDead();
            } else {
                newDrops.add(drop);
            }
        }

        event.drops.clear();
        event.drops.addAll(newDrops);

        toReAdd.put(event.entityPlayer.getGameProfile().getId(), toSave);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        List<ItemStack> items = toReAdd.get(event.player.getGameProfile().getId());
        if (items == null || items.isEmpty())
            return;

        for (ItemStack item : items) {
            event.player.inventory.addItemStackToInventory(item.copy());
        }

        toReAdd.remove(event.player.getGameProfile().getId());
    }
}