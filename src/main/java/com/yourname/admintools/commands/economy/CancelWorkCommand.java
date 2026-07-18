package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import net.minecraft.network.chat.Component;
import com.mojang.brigadier.arguments.IntegerArgumentType;

public class CancelWorkCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(

                Commands.literal("cancelwork")

                        .then(
                                Commands.argument(
                                        "id",
                                        IntegerArgumentType.integer(1)
                                )

                                .executes(context -> {


                                    int id =
                                            IntegerArgumentType.getInteger(
                                                    context,
                                                    "id"
                                            );


                                    context.getSource()
                                            .sendSuccess(
                                                    () -> Component.literal(
                                                            "Cancelled work #"
                                                            + id
                                                    ),
                                                    false
                                            );


                                    return 1;


                                })

                        )

        );


    }

}
