package com.yourname.admintools.events;

import com.yourname.admintools.manager.TaxManager;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TaxTickEvent {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(
            TickEvent.ServerTickEvent event
    ) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        tickCounter++;

        // Every 5 seconds (20 ticks = 1 second)
        if (tickCounter >= 100) {

            tickCounter = 0;

            TaxManager.updateExpiredTaxes();

        }

    }

}
