package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class WarnCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("warn")
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

                                    target.sendSystemMessage(
                                            Component.literal(
                                                    "[ADMIN WARNING] Please follow the server rules."
                                            )
                                    );

                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Warned "
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
