package com.yourname.admintools.commands.world;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;

public class WorldCommands {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        WeatherStormCommand.register(dispatcher);

        LockWeatherCommand.register(dispatcher);


    }

}
