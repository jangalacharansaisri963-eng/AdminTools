package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class TeleportAllCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("teleportall")
                        .requires(
                                source -> source.hasPermission(2)
                        )
                        .executes(
                                TeleportAllCommand::teleportAll
                        )
        );
    }


    private static int teleportAll(
            CommandContext<CommandSourceStack> context
    ) {

        if (!(context.getSource().getEntity()
                instanceof ServerPlayer sourcePlayer)) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Only players can use this command."
                    )
            );

            return 0;
        }


        double x = sourcePlayer.getX();
        double y = sourcePlayer.getY();
        double z = sourcePlayer.getZ();

        float yaw = sourcePlayer.getYRot();
        float pitch = sourcePlayer.getXRot();


        int count = 0;


        for (
                ServerPlayer player
                : sourcePlayer.getServer()
                        .getPlayerList()
                        .getPlayers()
        ) {

            if (player.getUUID().equals(
                    sourcePlayer.getUUID()
            )) {
                continue;
            }


            player.teleportTo(
                    x,
                    y,
                    z
            );

            player.setYRot(yaw);
            player.setXRot(pitch);


            player.sendSystemMessage(
                    Component.literal(
                            "You have been teleported "
                                    + "to an administrator."
                    )
            );

            count++;
        }


        final int teleported = count;


        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Teleported "
                                + teleported
                                + " player(s) to you."
                ),
                true
        );


        return teleported;
    }
  }
