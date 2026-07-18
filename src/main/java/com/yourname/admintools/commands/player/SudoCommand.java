package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SudoCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("sudo")
                        .requires(source -> source.hasPermission(2))
                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )
                                .then(
                                        Commands.argument(
                                                "command",
                                                StringArgumentType.greedyString()
                                        )
                                        .executes(context -> {

                                            ServerPlayer target =
                                                    EntityArgument.getPlayer(
                                                            context,
                                                            "player"
                                                    );

                                            String command =
                                                    StringArgumentType.getString(
                                                            context,
                                                            "command"
                                                    );


                                            // Execute command as target player
                                            target.getServer()
                                                    .getCommands()
                                                    .performPrefixedCommand(
                                                            target.createCommandSourceStack(),
                                                            command
                                                    );


                                            context.getSource()
                                                    .sendSuccess(
                                                            () -> Component.literal(
                                                                    "Forced "
                                                                    + target.getScoreboardName()
                                                                    + " to run: /"
                                                                    + command
                                                            ),
                                                            true
                                                    );

                                            return 1;
                                        })
                                )
                        )
        );
    }
}
