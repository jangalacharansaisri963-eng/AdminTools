package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ExplodeCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("explode")
                        .requires(source -> source.hasPermission(2))
                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )
                                        .executes(context -> {

                                            ServerPlayer target =
                                                    EntityArgument.getPlayer(
                                                            context,
                                                            "player"
                                                    );

                                            if (target.level() instanceof ServerLevel serverLevel) {

                                                // Visual explosion only
                                                serverLevel.explode(
                                                        null,
                                                        null,
                                                        null,
                                                        target.getX(),
                                                        target.getY(),
                                                        target.getZ(),
                                                        3.0F,
                                                        false,
                                                        Level.ExplosionInteraction.NONE
                                                );

                                                // Guaranteed death
                                                target.kill();

                                                context.getSource().sendSuccess(
                                                        () -> net.minecraft.network.chat.Component.literal(
                                                                "Exploded " + target.getScoreboardName()
                                                        ),
                                                        true
                                                );
                                            }

                                            return 1;
                                        })
                        )
        );
    }
}
