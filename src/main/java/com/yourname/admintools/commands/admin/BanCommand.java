package com.yourname.admintools.commands.admin;


import com.mojang.brigadier.CommandDispatcher;

import com.yourname.admintools.manager.BanManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;



public class BanCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(

                Commands.literal("ban")

                        .then(

                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )


                                .executes(context -> {



                                    ServerPlayer target =
                                            EntityArgument.getPlayer(
                                                    context,
                                                    "player"
                                            );



                                    BanManager.ban(
                                            target.getUUID()
                                    );



                                    context.getSource()
                                            .sendSuccess(

                                                    () -> Component.literal(
                                                            "Banned "
                                                            + target.getName()
                                                                    .getString()
                                                    ),

                                                    false
                                            );



                                    return 1;


                                })

                        )

        );


    }


}
