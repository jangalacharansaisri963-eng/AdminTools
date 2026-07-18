package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class BurnCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("burn")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.setSecondsOnFire(10);


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "You are now burning"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
