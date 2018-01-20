package com.cjm721.ipwhitelist;

import net.minecraftforge.common.config.Config;

import static com.cjm721.ipwhitelist.IpWhitelist.MODID;

@Config(modid = MODID)
public class IpWhitelistConfig {
    public static String[] whitelistedIps = new String[] {"127.0.0.1"};
    public static String disconnectMessage = "You are not authorized to correct.";
}
