package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class SetupWorkCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(

                Commands.literal("setupwork")

                        .executes(context -> {


                            context.getSource()
                                    .sendSuccess(
                                            () -> Component.literal(
                                                    "Usage: /setupwork <type> <item> <amount> <x> <y> <z> <reward>"
                                            ),
                                            false
                                    );


                            return 1;


                        })

        );


    }

}
