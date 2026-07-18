package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.EnumSet;

public class TopCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("top")
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            int x = player.blockPosition().getX();
                            int z = player.blockPosition().getZ();

                            int topY =
                                    player.level().getHeight(
                                            Heightmap.Types.MOTION_BLOCKING,
                                            x,
                                            z
                                    );

                            player.teleportTo(
                                    (net.minecraft.server.level.ServerLevel) player.level(),
                                    x + 0.5D,
                                    topY + 1,
                                    z + 0.5D,
                                    EnumSet.noneOf(RelativeMovement.class),
                                    player.getYRot(),
                                    player.getXRot()
                            );

                            return 1;
                        })
        );
    }
}
