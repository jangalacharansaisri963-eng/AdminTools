package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import com.yourname.admintools.manager.EconomyManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class DepositCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("deposit")

                        .then(
                                Commands.argument(
                                        "amount",
                                        IntegerArgumentType.integer(1)
                                )

                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource()
                                                            .getPlayer();

                                            ServerLevel level =
                                                    context.getSource()
                                                            .getLevel();

                                            EconomyManager economy =
                                                    EconomyManager.get(level);

                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );

                                            economy.removeMoney(
                                                    player.getUUID(),
                                                    amount
                                            );

                                            context.getSource().sendSuccess(
                                                    () -> Component.literal(
                                                            "Deposited $" + amount
                                                    ),
                                                    false
                                            );

                                            return 1;

                                        })

                        )

        );

    }

}
