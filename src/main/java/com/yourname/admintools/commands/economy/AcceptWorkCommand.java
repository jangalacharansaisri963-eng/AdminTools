package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.network.chat.Component;


public class AcceptWorkCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(

                Commands.literal("acceptwork")

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
                                                            "Accepted work #"
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
