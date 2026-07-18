package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ExtinguishCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("extinguish")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.clearFire();


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Fire removed"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
