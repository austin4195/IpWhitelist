package com.cjm721.ipwhitelist.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.management.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.SocketAddress;
import java.util.Arrays;

import static com.cjm721.ipwhitelist.IpWhitelistConfig.disconnectMessage;
import static com.cjm721.ipwhitelist.IpWhitelistConfig.whitelistedIps;

@Mixin(PlayerList.class)
public class IpWhitelistMixin {

    @Redirect(at = @At("INVOKE"), method = "allowUserToConnect", require = 1)
    public String allowUserToConnect(PlayerList playerList, SocketAddress address, GameProfile profile) {
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
            return playerList.allowUserToConnect(address,profile);
        } else {
            return disconnectMessage;
        }
    }
}
