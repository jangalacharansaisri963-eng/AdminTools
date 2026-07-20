package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import com.yourname.admintools.manager.EconomyManager;
import com.yourname.admintools.manager.LotteryManager;

public class LotteryBuyCommand {

    private static final int PRICE =
            1000;

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(

                Commands.literal("lotterybuy")

                        .then(

                                Commands.argument(
                                        "amount",
                                        IntegerArgumentType.integer(
                                                1,
                                                10
                                        )
                                )

                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource()
                                                            .getPlayer();

                                            ServerLevel level =
                                                    context.getSource()
                                                            .getLevel();

                                            EconomyManager economy =
                                                    EconomyManager.get(
                                                            level
                                                    );

                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );

                                            int totalPrice =
                                                    amount * PRICE;

                                            if (!economy.removeMoney(
                                                    player.getUUID(),
                                                    totalPrice
                                            )) {

                                                context.getSource()
                                                        .sendFailure(

                                                                Component.literal(
                                                                        "Not enough money."
                                                                )

                                                        );

                                                return 0;

                                            }

                                            StringBuilder builder =
                                                    new StringBuilder();

                                            builder.append(
                                                    "Purchased "
                                            );

                                            builder.append(
                                                    amount
                                            );

                                            builder.append(
                                                    " ticket(s)\n\n"
                                            );

                                            for (int i = 0; i < amount; i++) {

                                                LotteryManager.buyTicket(
                                                        player.getUUID()
                                                );

                                                String ticket =
                                                        LotteryManager
                                                                .getTickets(
                                                                        player.getUUID()
                                                                )
                                                                .get(
                                                                        LotteryManager
                                                                                .getTickets(
                                                                                        player.getUUID()
                                                                                )
                                                                                .size() - 1
                                                                );

                                                builder.append(
                                                        ticket
                                                );

                                                builder.append(
                                                        "\n"
                                                );

                                            }

                                            context.getSource()
                                                    .sendSuccess(

                                                            () -> Component.literal(
                                                                    builder.toString()
                                                            ),

                                                            false

                                                    );

                                            return 1;

                                        })

                        )

        );

    }

}
