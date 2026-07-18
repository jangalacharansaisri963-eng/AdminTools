package com.yourname.admintools.commands.utility;


import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerPlayer;



public class NearbyCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("nearby")
                        .executes(context -> {


                            if (!(context.getSource()
                                    .getEntity() instanceof ServerPlayer player)) {


                                context.getSource()
                                        .sendFailure(
                                                Component.literal(
                                                        "Only players can use this command."
                                                )
                                        );


                                return 0;

                            }



                            int count = 0;



                            player.sendSystemMessage(
                                    Component.literal(
                                            "Players within 5 blocks:"
                                    )
                            );



                            for(ServerPlayer target :
                                    player.serverLevel()
                                            .players()) {



                                if(target == player) {
                                    continue;
                                }



                                double distance =
                                        player.distanceTo(target);



                                if(distance <= 5) {


                                    player.sendSystemMessage(
                                            Component.literal(
                                                    "- "
                                                    + target.getScoreboardName()
                                                    + " ("
                                                    + Math.round(distance)
                                                    + " blocks)"
                                            )
                                    );


                                    count++;

                                }

                            }



                            if(count == 0) {


                                player.sendSystemMessage(
                                        Component.literal(
                                                "No players nearby."
                                        )
                                );


                            }



                            return count;


                        })
        );


    }


}
