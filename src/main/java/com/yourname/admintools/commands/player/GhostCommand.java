package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;
import com.yourname.admintools.manager.GhostManager;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class GhostCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("ghost")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            if (!GhostManager.isGhosted(
                                    player.getUUID()
                            )) {

                                GhostManager.ghost(
                                        player.getUUID()
                                );

                                player.addEffect(
                                        new MobEffectInstance(
                                                MobEffects.INVISIBILITY,
                                                Integer.MAX_VALUE,
                                                0,
                                                false,
                                                false,
                                                false
                                        )
                                );

                                player.sendSystemMessage(
                                        Component.literal(
                                                "Ghost mode enabled."
                                        ).withStyle(
                                                ChatFormatting.GREEN
                                        )
                                );

                            } else {

                                GhostManager.unghost(
                                        player.getUUID()
                                );

                                player.removeEffect(
                                        MobEffects.INVISIBILITY
                                );

                                player.sendSystemMessage(
                                        Component.literal(
                                                "Ghost mode disabled."
                                        ).withStyle(
                                                ChatFormatting.RED
                                        )
                                );
                            }

                            return 1;
                        })
        );
    }
                                  }
