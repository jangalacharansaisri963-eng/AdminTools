package com.yourname.admintools.events;

import net.minecraft.server.level.ServerPlayer;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.yourname.admintools.manager.FreezeManager;

@Mod.EventBusSubscriber
public class FreezeEvent {

    @SubscribeEvent
    public static void onPlayerTick(
            TickEvent.PlayerTickEvent event
    ) {

        if (!(event.player instanceof ServerPlayer player)) {
            return;
        }

        if (!FreezeManager.isFrozen(
                player.getUUID()
        )) {
            return;
        }

        player.setDeltaMovement(
                0.0D,
                0.0D,
                0.0D
        );

        player.hurtMarked = true;

    }

}
