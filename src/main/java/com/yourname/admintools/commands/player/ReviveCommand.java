package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ReviveCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("revive")
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


                                    target.setHealth(
                                            target.getMaxHealth()
                                    );


                                    target.getFoodData()
                                            .setFoodLevel(20);


                                    target.removeAllEffects();


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Revived "
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
