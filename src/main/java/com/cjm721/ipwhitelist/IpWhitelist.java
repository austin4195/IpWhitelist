package com.cjm721.ipwhitelist;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = IpWhitelist.MODID, version = IpWhitelist.VERSION,
        acceptedMinecraftVersions = "[1.12,1.13)",
        useMetadata = true,
        acceptableRemoteVersions = "*"
)
public class IpWhitelist {

    @Mod.Instance(IpWhitelist.MODID)
    public static IpWhitelist instance;

    public static final String MODID = "ipwhitelist";
    public static final String VERSION = "${mod_version}";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @Mod.EventHandler
//    public void serverStart(FMLServerStartingEvent event) {
//        MinecraftServer server = event.getServer();
//
//        if(server instanceof DedicatedServer)
//            server.setPlayerList(new WhitelistPlayerList((DedicatedServer)server));
//    }
}
