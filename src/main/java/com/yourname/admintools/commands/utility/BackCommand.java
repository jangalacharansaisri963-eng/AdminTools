package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;
import com.yourname.admintools.manager.TeleportManager;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;

import java.util.EnumSet;

public class BackCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("back")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            TeleportManager.SavedPos pos =
                                    TeleportManager.getPosition(player.getUUID());

                            if (pos == null) {
                                player.sendSystemMessage(
                                        Component.literal("No saved location found.")
                                );
                                return 0;
                            }

                            ServerLevel targetLevel =
                                    pos.getLevel(context.getSource().getServer());

                            if (targetLevel == null) {
                                player.sendSystemMessage(
                                        Component.literal("Saved dimension no longer exists.")
                                );
                                return 0;
                            }

                            player.teleportTo(
                                    targetLevel,
                                    pos.x(),
                                    pos.y(),
                                    pos.z(),
                                    EnumSet.noneOf(RelativeMovement.class),
                                    pos.yRot(),
                                    pos.xRot()
                            );

                            player.sendSystemMessage(
                                    Component.literal("Returned to previous location.")
                            );

                            return 1;
                        })
        );
    }
}
