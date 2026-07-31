package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SocialSpyCommand {

    private static final Set<UUID> SOCIAL_SPY_PLAYERS =
            new HashSet<>();

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("socialspy")
                        .requires(source -> source.hasPermission(2))

                        // /socialspy
                        .executes(
                                SocialSpyCommand::toggleSelf
                        )

                        // /socialspy <player>
                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )

                                .executes(
                                        SocialSpyCommand::togglePlayer
                                )
                        )
        );
    }


    private static int toggleSelf(
            CommandContext<CommandSourceStack> context
    ) {

        if (!(context.getSource().getEntity()
                instanceof ServerPlayer player)) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Only players can use this form."
                    )
            );

            return 0;
        }

        UUID uuid = player.getUUID();

        boolean enabled;

        if (SOCIAL_SPY_PLAYERS.contains(uuid)) {

            SOCIAL_SPY_PLAYERS.remove(uuid);
            enabled = false;

        } else {

            SOCIAL_SPY_PLAYERS.add(uuid);
            enabled = true;
        }


        sendStatus(
                context.getSource(),
                player.getName().getString(),
                enabled
        );

        return 1;
    }


    private static int togglePlayer(
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

        boolean enabled;

        if (SOCIAL_SPY_PLAYERS.contains(uuid)) {

            SOCIAL_SPY_PLAYERS.remove(uuid);
            enabled = false;

        } else {

            SOCIAL_SPY_PLAYERS.add(uuid);
            enabled = true;
        }


        sendStatus(
                context.getSource(),
                target.getName().getString(),
                enabled
        );

        return 1;
    }


    private static void sendStatus(
            CommandSourceStack source,
            String playerName,
            boolean enabled
    ) {

        String status =
                enabled
                        ? "enabled"
                        : "disabled";

        source.sendSuccess(
                () -> Component.literal(
                        "Social Spy "
                                + status
                                + " for "
                                + playerName
                                + "."
                ),
                false
        );
    }


    public static boolean isEnabled(UUID uuid) {
        return SOCIAL_SPY_PLAYERS.contains(uuid);
    }


    public static void sendSpyMessage(
            ServerPlayer sender,
            ServerPlayer receiver,
            String message
    ) {

        String senderName =
                sender.getName().getString();

        String receiverName =
                receiver.getName().getString();


        Component spyMessage =
                Component.literal(
                        "[SocialSpy] "
                                + senderName
                                + " -> "
                                + receiverName
                                + ": "
                                + message
                );


        for (
                ServerPlayer staff
                : sender.getServer()
                        .getPlayerList()
                        .getPlayers()
        ) {

            if (isEnabled(staff.getUUID())) {

                staff.sendSystemMessage(
                        spyMessage
                );
            }
        }
    }
}
