package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import com.yourname.admintools.manager.MuteManager;

public class UnmuteCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("unmute")
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

                                    MuteManager.unmute(
                                            target.getUUID()
                                    );

                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Unmuted "
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
