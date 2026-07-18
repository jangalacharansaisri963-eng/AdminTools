package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class RestoreCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("restore")
                        .executes(context -> {


                            ServerPlayer player =
                                    context.getSource().getPlayer();


                            player.setHealth(
                                    player.getMaxHealth()
                            );


                            player.getFoodData()
                                    .setFoodLevel(20);


                            player.removeAllEffects();


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Player restored"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
