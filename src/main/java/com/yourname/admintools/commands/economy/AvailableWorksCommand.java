package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class AvailableWorksCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(

                Commands.literal("availableworks")

                        .executes(context -> {


                            context.getSource()
                                    .sendSuccess(
                                            () -> Component.literal(
                                                    "Available works:\nNo works found"
                                            ),
                                            false
                                    );


                            return 1;


                        })

        );


    }


}
