package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class PlayerInfoCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("playerinfo")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Player: "
                                            + player.getName()
                                            .getString()
                                            + "\nUUID: "
                                            + player.getUUID()
                                            + "\nHealth: "
                                            + player.getHealth()
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
