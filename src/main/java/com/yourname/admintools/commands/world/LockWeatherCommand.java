package com.yourname.admintools.commands.world;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class LockWeatherCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("lockweather")

                        .then(
                                Commands.literal("clear")
                                        .executes(context -> {


                                            context.getSource()
                                                    .getLevel()
                                                    .setWeatherParameters(
                                                            Integer.MAX_VALUE,
                                                            0,
                                                            false,
                                                            false
                                                    );


                                            return 1;

                                        })
                        )


                        .then(
                                Commands.literal("rain")
                                        .executes(context -> {


                                            context.getSource()
                                                    .getLevel()
                                                    .setWeatherParameters(
                                                            0,
                                                            Integer.MAX_VALUE,
                                                            true,
                                                            false
                                                    );


                                            return 1;

                                        })
                        )


                        .then(
                                Commands.literal("storm")
                                        .executes(context -> {


                                            context.getSource()
                                                    .getLevel()
                                                    .setWeatherParameters(
                                                            0,
                                                            Integer.MAX_VALUE,
                                                            true,
                                                            true
                                                    );


                                            return 1;

                                        })
                        )

        );


    }

}
