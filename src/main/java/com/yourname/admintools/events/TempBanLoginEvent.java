package com.yourname.admintools.events;

import com.mojang.authlib.GameProfile;
import com.yourname.admintools.manager.TempBanManager;

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.PlayerNegotiationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class TempBanLoginEvent {

    @SubscribeEvent
    public static void onPlayerNegotiation(
            PlayerNegotiationEvent event
    ) {

        GameProfile profile = event.getProfile();

        if (profile == null) {
            return;
        }

        UUID uuid = profile.getId();

        TempBanManager.TempBan ban =
                TempBanManager.getBan(uuid);

        if (ban == null) {
            return;
        }

        Connection connection =
                event.getConnection();

        String remaining =
                formatDuration(
                        ban.getRemainingMillis()
                );

        Component message =
                Component.literal(
                        "You are temporarily banned!\n\n"
                                + "Reason: "
                                + ban.reason()
                                + "\n"
                                + "Time remaining: "
                                + remaining
                );

        connection.disconnect(message);
    }

    private static String formatDuration(
            long millis
    ) {

        long totalSeconds =
                millis / 1000;

        long days =
                totalSeconds / 86400;

        totalSeconds %= 86400;

        long hours =
                totalSeconds / 3600;

        totalSeconds %= 3600;

        long minutes =
                totalSeconds / 60;

        long seconds =
                totalSeconds % 60;


        if (days > 0) {

            return days + "d "
                    + hours + "h "
                    + minutes + "m";
        }

        if (hours > 0) {

            return hours + "h "
                    + minutes + "m "
                    + seconds + "s";
        }

        if (minutes > 0) {

            return minutes + "m "
                    + seconds + "s";
        }

        return seconds + "s";
    }
}
