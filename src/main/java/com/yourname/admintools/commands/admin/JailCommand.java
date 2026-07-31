package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JailCommand {

    private static final Map<UUID, PlayerPosition> JAILED_PLAYERS = new HashMap<>();

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("jail")
                        .requires(source -> source.hasPermission(2))

                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )

                                .then(
                                        Commands.argument(
                                                "x",
                                                DoubleArgumentType.doubleArg()
                                        )

                                        .then(
                                                Commands.argument(
                                                        "y",
                                                        DoubleArgumentType.doubleArg()
                                                )

                                                .then(
                                                        Commands.argument(
                                                                "z",
                                                                DoubleArgumentType.doubleArg()
                                                        )

                                                        .executes(
                                                                JailCommand::jailPlayer
                                                        )
                                                )
                                        )
                                )
                        )
        );
    }

    private static int jailPlayer(
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
                    Component.literal("Player not found.")
            );

            return 0;
        }

        UUID uuid = target.getUUID();

        if (JAILED_PLAYERS.containsKey(uuid)) {

            context.getSource().sendFailure(
                    Component.literal(
                            target.getGameProfile().getName()
                                    + " is already jailed."
                    )
            );

            return 0;
        }

        PlayerPosition originalPosition =
                new PlayerPosition(
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        target.getYRot(),
                        target.getXRot()
                );

        JAILED_PLAYERS.put(
                uuid,
                originalPosition
        );

        double x = DoubleArgumentType.getDouble(
                context,
                "x"
        );

        double y = DoubleArgumentType.getDouble(
                context,
                "y"
        );

        double z = DoubleArgumentType.getDouble(
                context,
                "z"
        );

        target.teleportTo(x, y, z);

        target.sendSystemMessage(
                Component.literal(
                        "You have been jailed by an administrator."
                )
        );

        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Jailed "
                                + target.getGameProfile().getName()
                                + " at "
                                + x + ", "
                                + y + ", "
                                + z
                                + "."
                ),
                true
        );

        return 1;
    }

    public static boolean isJailed(UUID uuid) {
        return JAILED_PLAYERS.containsKey(uuid);
    }

    public static PlayerPosition getOriginalPosition(UUID uuid) {
        return JAILED_PLAYERS.get(uuid);
    }

    public static PlayerPosition removeJailedPlayer(UUID uuid) {
        return JAILED_PLAYERS.remove(uuid);
    }

    public static class PlayerPosition {

        private final double x;
        private final double y;
        private final double z;

        private final float yaw;
        private final float pitch;

        public PlayerPosition(
                double x,
                double y,
                double z,
                float yaw,
                float pitch
        ) {

            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public float getYaw() {
            return yaw;
        }

        public float getPitch() {
            return pitch;
        }
    }
}
