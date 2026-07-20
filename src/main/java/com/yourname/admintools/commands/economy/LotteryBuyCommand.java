package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

import com.yourname.admintools.manager.LotteryManager;

public class LotteryBuyCommand {

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

                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );

                                            if (!LotteryManager.buyTickets(
                                                    level,
                                                    player.getUUID(),
                                                    amount
                                            )) {

                                                context.getSource()
                                                        .sendFailure(
                                                                Component.literal(
                                                                        "Unable to purchase lottery tickets."
                                                                )
                                                        );

                                                return 0;

                                            }

                                            List<String> tickets =
                                                    LotteryManager.getTickets(
                                                            player.getUUID()
                                                    );

                                            int start =
                                                    tickets.size() - amount;

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

                                            for (
                                                    int i = start;
                                                    i < tickets.size();
                                                    i++
                                            ) {

                                                builder.append(
                                                        tickets.get(i)
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
