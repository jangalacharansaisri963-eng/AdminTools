package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.network.chat.Component;


public class SellCancelCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(

                Commands.literal("sellcancel")

                        .then(
                                Commands.argument(
                                        "listing",
                                        IntegerArgumentType.integer(1)
                                )

                                .executes(context -> {


                                    int id =
                                            IntegerArgumentType.getInteger(
                                                    context,
                                                    "listing"
                                            );


                                    context.getSource()
                                            .sendSuccess(
                                                    () -> Component.literal(
                                                            "Removed listing #"
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
