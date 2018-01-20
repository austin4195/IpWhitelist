package com.cjm721.ipwhitelist;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedPlayerList;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.management.PlayerList;

import javax.annotation.Nonnull;
import java.net.SocketAddress;
import java.util.Arrays;

import static com.cjm721.ipwhitelist.IpWhitelistConfig.disconnectMessage;
import static com.cjm721.ipwhitelist.IpWhitelistConfig.whitelistedIps;

public class WhitelistPlayerList extends DedicatedPlayerList {

    public WhitelistPlayerList(DedicatedServer server) {
        super(server);
    }

    @Override
    public String allowUserToConnect(@Nonnull SocketAddress address, @Nonnull GameProfile profile) {
        String s = address.toString();

        if (s.contains("/"))
        {
            s = s.substring(s.indexOf(47) + 1);
        }
        if (s.contains(":"))
        {
            s = s.substring(0, s.indexOf(58));
        }

        if(Arrays.asList(whitelistedIps).contains(s)) {
            return super.allowUserToConnect(address, profile);
        } else {
            return disconnectMessage;
        }
    }
}
