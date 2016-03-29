package me.dmillerw.soulbound;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import me.dmillerw.soulbound.command.CommandSoulbind;
import me.dmillerw.soulbound.handler.EventHandler;
import me.dmillerw.soulbound.lib.Reference;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author dmillerw
 */
@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION)
public class Soulbound {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        final EventHandler eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSoulbind());
    }
}
