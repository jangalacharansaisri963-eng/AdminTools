package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public class UnJailCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("unjail")
                        .requires(source -> source.hasPermission(2))

                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )

                                .executes(
                                        UnJailCommand::unjailPlayer
                                )
                        )
        );
    }


    private static int unjailPlayer(
            CommandContext<CommandSourceStack> context
    ) {

        ServerPlayer target;

        try {

            target = EntityArgument.getPlayer(
                    context,
                    "player"
            );

        } catch (Exception exception) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Player not found."
                    )
            );

            return 0;
        }


        UUID uuid = target.getUUID();


        if (!JailCommand.isJailed(uuid)) {

            context.getSource().sendFailure(
                    Component.literal(
                            target.getGameProfile().getName()
                                    + " is not jailed."
                    )
            );

            return 0;
        }


        JailCommand.PlayerPosition originalPosition =
                JailCommand.removeJailedPlayer(uuid);


        if (originalPosition == null) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Could not retrieve the player's "
                                    + "original position."
                    )
            );

            return 0;
        }


        target.teleportTo(
                originalPosition.getX(),
                originalPosition.getY(),
                originalPosition.getZ()
        );


        target.setYRot(
                originalPosition.getYaw()
        );

        target.setXRot(
                originalPosition.getPitch()
        );


        target.sendSystemMessage(
                Component.literal(
                        "You have been released from jail."
                )
        );


        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Unjailed "
                                + target.getGameProfile().getName()
                                + " and returned them to their "
                                + "original position."
                ),
                true
        );


        return 1;
    }
}
