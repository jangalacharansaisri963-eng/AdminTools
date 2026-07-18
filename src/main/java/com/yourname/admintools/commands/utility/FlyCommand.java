package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class FlyCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("fly")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            boolean enabled =
                                    !player.getAbilities().mayfly;


                            player.getAbilities().mayfly = enabled;

                            player.getAbilities().flying = enabled;


                            player.onUpdateAbilities();


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            enabled
                                            ? "Flight enabled"
                                            : "Flight disabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
