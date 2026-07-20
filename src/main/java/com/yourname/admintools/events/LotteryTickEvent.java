package com.yourname.admintools.events;

import com.yourname.admintools.manager.LotteryManager;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LotteryTickEvent {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(
            TickEvent.ServerTickEvent event
    ) {

        if(event.phase != TickEvent.Phase.END){
            return;
        }

        tickCounter++;

        // Every 5 seconds
        if(tickCounter >= 100){

            tickCounter = 0;

            /*
             * Future:
             * After 1 real-life month:
             * - Check winners
             * - Give jackpot
             * - Clear tickets
             * - Wait for admins to create next lottery
             */

        }

    }

}
