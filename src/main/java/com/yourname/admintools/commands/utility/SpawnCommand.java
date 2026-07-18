package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;

import java.util.EnumSet;

public class SpawnCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("spawn")
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            ServerLevel overworld =
                                    context.getSource()
                                            .getServer()
                                            .overworld();

                            BlockPos spawnPos =
                                    overworld.getSharedSpawnPos();

                            player.teleportTo(
                                    overworld,
                                    spawnPos.getX() + 0.5D,
                                    spawnPos.getY(),
                                    spawnPos.getZ() + 0.5D,
                                    EnumSet.noneOf(RelativeMovement.class),
                                    player.getYRot(),
                                    player.getXRot()
                            );

                            return 1;
                        })
        );
    }
}
