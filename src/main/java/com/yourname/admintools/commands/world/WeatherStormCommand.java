package com.yourname.admintools.commands.world;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class WeatherStormCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("weatherstorm")
                        .executes(context -> {


                            context.getSource()
                                    .getLevel()
                                    .setWeatherParameters(
                                            0,
                                            12000,
                                            true,
                                            true
                                    );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Storm started"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
