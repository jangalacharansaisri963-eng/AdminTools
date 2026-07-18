package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

public class OpCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("op")
                        .requires(source -> source.hasPermission(4))
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

                                    PlayerList playerList =
                                            target.getServer()
                                                    .getPlayerList();


                                    playerList.op(
                                            target.getGameProfile()
                                    );


                                    context.getSource()
                                            .sendSuccess(
                                                    () -> Component.literal(
                                                            "Made "
                                                            + target.getScoreboardName()
                                                            + " an operator."
                                                    ),
                                                    true
                                            );

                                    return 1;
                                })
                        )
        );
    }
}
