package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class FakeOpCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("fakeop")
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
                                                            "[Server: Opped "
                                                                    + target.getScoreboardName()
                                                                    + "]"
                                                    )
                                            );

                                            context.getSource().sendSuccess(
                                                    () -> Component.literal(
                                                            "Fake OP sent to "
                                                                    + target.getScoreboardName()
                                                    ),
                                                    true
                                            );

                                            return 1;
                                        })
                        )
        );
    }
                                              }
