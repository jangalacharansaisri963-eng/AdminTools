package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class StaffChatCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("staffchat")

                        .requires(
                                source -> source.hasPermission(2)
                        )

                        .then(
                                Commands.argument(
                                        "message",
                                        StringArgumentType.greedyString()
                                )

                                .executes(
                                        StaffChatCommand::sendStaffMessage
                                )
                        )
        );
    }


    private static int sendStaffMessage(
            CommandContext<CommandSourceStack> context
    ) {

        String message =
                StringArgumentType.getString(
                        context,
                        "message"
                );

        String sender;

        if (context.getSource().getEntity()
                instanceof ServerPlayer player) {

            sender = player.getName().getString();

        } else {

            sender = "CONSOLE";
        }


        Component staffMessage =
                Component.literal(
                        "[Staff] "
                                + sender
                                + ": "
                                + message
                );


        for (
                ServerPlayer player
                : context.getSource()
                        .getServer()
                        .getPlayerList()
                        .getPlayers()
        ) {

            if (player.hasPermissions(2)) {

                player.sendSystemMessage(
                        staffMessage
                );
            }
        }


        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Staff message sent."
                ),
                false
        );


        return 1;
    }
}
