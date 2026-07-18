package com.yourname.admintools;

import com.yourname.admintools.commands.CommandManager;
import com.yourname.admintools.manager.BanManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod("admintools")
public class AdminTools {


    public static final String MOD_ID = "admintools";


    public AdminTools() {


        BanManager.load();


        MinecraftForge.EVENT_BUS.register(this);


    }


    @SubscribeEvent
    public void registerCommands(
            RegisterCommandsEvent event
    ) {


        CommandManager.register(
                event.getDispatcher()
        );


    }


}
