package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.yourname.admintools.manager.TempBanManager;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public class TempBanCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("tempban")
                        .requires(source -> source.hasPermission(3))

                        .then(
                                Commands.argument(
                                        "player",
                                        StringArgumentType.word()
                                )

                                .then(
                                        Commands.argument(
                                                "duration",
                                                StringArgumentType.word()
                                        )

                                        .executes(context -> {

                                            String playerName =
                                                    StringArgumentType.getString(
                                                            context,
                                                            "player"
                                                    );

                                            String duration =
                                                    StringArgumentType.getString(
                                                            context,
                                                            "duration"
                                                    );

                                            return executeTempBan(
                                                    context.getSource(),
                                                    playerName,
                                                    duration,
                                                    "Temporarily banned"
                                            );
                                        })

                                        .then(
                                                Commands.argument(
                                                        "reason",
                                                        StringArgumentType.greedyString()
                                                )

                                                .executes(context -> {

                                                    String playerName =
                                                            StringArgumentType.getString(
                                                                    context,
                                                                    "player"
                                                            );

                                                    String duration =
                                                            StringArgumentType.getString(
                                                                    context,
                                                                    "duration"
                                                            );

                                                    String reason =
                                                            StringArgumentType.getString(
                                                                    context,
                                                                    "reason"
                                                            );

                                                    return executeTempBan(
                                                            context.getSource(),
                                                            playerName,
                                                            duration,
                                                            reason
                                                    );
                                                })
                                        )
                                )
                        )
        );
    }

    private static int executeTempBan(
            CommandSourceStack source,
            String playerName,
            String durationString,
            String reason
    ) {

        ServerPlayer player =
                source.getServer()
                        .getPlayerList()
                        .getPlayerByName(playerName);

        if (player == null) {

            source.sendFailure(
                    Component.literal(
                            "Player '" +
                                    playerName +
                                    "' is not online."
                    )
            );

            return 0;
        }

        long durationMillis =
                parseDuration(durationString);

        if (durationMillis <= 0) {

            source.sendFailure(
                    Component.literal(
                            "Invalid duration: "
                                    + durationString
                    )
            );

            source.sendFailure(
                    Component.literal(
                            "Use: 30s, 10m, 2h, or 7d."
                    )
            );

            return 0;
        }

        TempBanManager.addBan(
                player.getUUID(),
                player.getGameProfile().getName(),
                durationMillis,
                reason
        );

        player.connection.disconnect(
                Component.literal(
                        "You have been temporarily banned!\n\n"
                                + "Duration: "
                                + durationString
                                + "\nReason: "
                                + reason
                )
        );

        source.sendSuccess(
                () -> Component.literal(
                        "Temporarily banned "
                                + playerName
                                + " for "
                                + durationString
                                + "."
                ),
                true
        );

        return 1;
    }

    private static long parseDuration(
            String duration
    ) {

        if (duration == null ||
                duration.length() < 2) {

            return -1;
        }

        String numberPart =
                duration.substring(
                        0,
                        duration.length() - 1
                );

        char unit =
                Character.toLowerCase(
                        duration.charAt(
                                duration.length() - 1
                        )
                );

        long number;

        try {

            number =
                    Long.parseLong(numberPart);

        } catch (NumberFormatException exception) {

            return -1;
        }

        if (number <= 0) {
            return -1;
        }

        switch (unit) {

            case 's':
                return number * 1_000L;

            case 'm':
                return number * 60_000L;

            case 'h':
                return number * 3_600_000L;

            case 'd':
                return number * 86_400_000L;

            default:
                return -1;
        }
    }
}
