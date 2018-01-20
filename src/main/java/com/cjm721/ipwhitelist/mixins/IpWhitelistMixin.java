package com.cjm721.ipwhitelist.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.management.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.SocketAddress;
import java.util.Arrays;

import static com.cjm721.ipwhitelist.IpWhitelistConfig.disconnectMessage;
import static com.cjm721.ipwhitelist.IpWhitelistConfig.whitelistedIps;


@Mixin(PlayerList.class)
public class IpWhitelistMixin {

//    @Shadow
//    public String allowUserToConnect(SocketAddress address, GameProfile profile) {

//    /**
//     * @author
//     */
//    @Overwrite
//    public String allowUserToConnect(SocketAddress address, GameProfile profile) {
//        String s = address.toString();
//
//        if (s.contains("/")) {
//            s = s.substring(s.indexOf(47) + 1);
//        }
//        if (s.contains(":")) {
//            s = s.substring(0, s.indexOf(58));
//        }
//
//        if (Arrays.asList(settings.whitelistedIps).contains(s)) {
//            return null;
//        } else {
//            return settings.disconnectMessage;
//        }
//    }

    @Inject(at = @At(value = "HEAD"), method = "allowUserToConnect", require = 1, cancellable = true)
    public void id$allowUserToConnect(SocketAddress address, GameProfile profile, CallbackInfoReturnable<String> ci) {
        String s = address.toString();

        if (s.contains("/"))
        {
            s = s.substring(s.indexOf(47) + 1);
        }
        if (s.contains(":"))
        {
            s = s.substring(0, s.indexOf(58));
        }

        if(!Arrays.asList(whitelistedIps).contains(s)) {
            ci.setReturnValue(disconnectMessage);
        }
    }

//    @Redirect(at = @At(value = "INVOKE"), method = "allowUserToConnect", require = 1)
//    public String id$allowUserToConnect(PlayerList playerList, SocketAddress address, GameProfile profile) {
//        String s = address.toString();
//
//        if (s.contains("/"))
//        {
//            s = s.substring(s.indexOf(47) + 1);
//        }
//        if (s.contains(":"))
//        {
//            s = s.substring(0, s.indexOf(58));
//        }
//
//        if(Arrays.asList(settings.whitelistedIps).contains(s)) {
//            return playerList.allowUserToConnect(address,profile);
//        } else {
//            return settings.disconnectMessage;
//        }
//    }
}
