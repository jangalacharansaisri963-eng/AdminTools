package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import com.yourname.admintools.manager.EconomyManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class RewardCommand {

    private static final int REWARD_AMOUNT = 500;

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("reward")

                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayer();

                            ServerLevel level =
                                    context.getSource()
                                            .getLevel();

                            EconomyManager economy =
                                    EconomyManager.get(level);

                            economy.addMoney(
                                    player.getUUID(),
                                    REWARD_AMOUNT
                            );

                            context.getSource()
                                    .sendSuccess(

                                            () -> Component.literal(
                                                    "You received a reward of $" + REWARD_AMOUNT
                                            ),

                                            false

                                    );

                            return 1;

                        })

        );

    }

}
