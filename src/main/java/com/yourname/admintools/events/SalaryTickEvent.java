package com.yourname.admintools.events;

import com.yourname.admintools.manager.SalaryManager;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SalaryTickEvent {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(
            TickEvent.ServerTickEvent event
    ) {

        if(event.phase != TickEvent.Phase.END){
            return;
        }

        tickCounter++;

        if(tickCounter >= 100){

            tickCounter = 0;

            MinecraftServer server =
                    net.minecraftforge.server.ServerLifecycleHooks
                            .getCurrentServer();

            if(server == null){
                return;
            }

            for(ServerLevel level : server.getAllLevels()){

                SalaryManager.tick(
                        level
                );

            }

        }

    }

}
