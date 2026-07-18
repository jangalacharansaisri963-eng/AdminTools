package com.yourname.admintools;

import com.yourname.admintools.commands.CommandManager;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "admintools")
public class CommandRegistrationHandler {

    @SubscribeEvent
public static void onRegisterCommands(RegisterCommandsEvent event) {

    CommandManager.register(
            event.getDispatcher(),
            event.getBuildContext()
    );

}
