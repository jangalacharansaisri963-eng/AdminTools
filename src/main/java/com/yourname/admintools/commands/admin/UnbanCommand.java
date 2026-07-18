package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class UnbanCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("unban")
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

                                    context.getSource()
                                            .getServer()
                                            .getPlayerList()
                                            .getBans()
                                            .remove(
                                                    target.getGameProfile()
                                            );

                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Unbanned "
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
