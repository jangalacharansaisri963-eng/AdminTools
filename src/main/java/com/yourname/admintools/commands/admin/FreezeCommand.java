package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import com.yourname.admintools.manager.FreezeManager;

public class FreezeCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("freeze")
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

                                    FreezeManager.freeze(
                                            target.getUUID()
                                    );

                                    target.sendSystemMessage(
                                            Component.literal(
                                                    "You have been frozen by an admin."
                                            )
                                    );

                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Froze "
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
