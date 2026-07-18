package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

public class PlaytimeCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("playtime")
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            int ticks =
                                    player.getStats()
                                            .getValue(Stats.CUSTOM,
                                                    Stats.PLAY_TIME);

                            long seconds = ticks / 20L;
                            long minutes = seconds / 60L;
                            long hours = minutes / 60L;

                            minutes %= 60;

                            player.sendSystemMessage(
                                    Component.literal(
                                            "Playtime: "
                                                    + hours + " hours "
                                                    + minutes + " minutes"
                                    )
                            );

                            return 1;
                        })
        );
    }
  }
