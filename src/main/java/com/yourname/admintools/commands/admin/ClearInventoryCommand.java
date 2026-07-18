package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ClearInventoryCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("clearinv")
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

                                    target.getInventory()
                                            .clearContent();

                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Cleared inventory of "
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
