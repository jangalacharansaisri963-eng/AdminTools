package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class LaunchCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("launch")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.setDeltaMovement(
                                    player.getDeltaMovement().x,
                                    2.5,
                                    player.getDeltaMovement().z
                            );


                            player.hurtMarked = true;


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Launched!"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
