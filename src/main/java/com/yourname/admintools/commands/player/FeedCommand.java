package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class FeedCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("feed")
                        .executes(context -> {


                            ServerPlayer player =
                                    context.getSource().getPlayer();


                            player.getFoodData()
                                    .setFoodLevel(20);


                            player.getFoodData()
                                    .setSaturation(20);


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Hunger restored"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
