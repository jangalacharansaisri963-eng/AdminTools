package com.yourname.admintools.events;

import com.yourname.admintools.manager.BanManager;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerJoinEvent {

    @SubscribeEvent
    public static void onJoin(
            PlayerEvent.PlayerLoggedInEvent event
    ) {

        var player = event.getEntity();

        if (
                BanManager.isBanned(
                        player.getUUID()
                )
        ) {

            if (player instanceof ServerPlayer serverPlayer) {

                serverPlayer.connection.disconnect(
                        Component.literal(
                                "You are banned by an admin"
                        )
                );

            }

        }

    }

}
