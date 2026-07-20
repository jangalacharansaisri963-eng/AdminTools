package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import com.yourname.admintools.manager.LotteryManager;

public class LotteryAddCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(

                Commands.literal("lotteryadd")

                        .requires(source ->
                                source.hasPermission(2)
                        )

                        .then(

                                Commands.argument(
                                        "jackpot",
                                        LongArgumentType.longArg(
                                                1L,
                                                1_000_000_000_000_000_000L
                                        )
                                )

                                        .then(

                                                Commands.argument(
                                                        "ticket",
                                                        StringArgumentType.greedyString()
                                                )

                                                        .executes(context -> {

                                                            long jackpot =
                                                                    LongArgumentType.getLong(
                                                                            context,
                                                                            "jackpot"
                                                                    );

                                                            String ticket =
                                                                    StringArgumentType.getString(
                                                                            context,
                                                                            "ticket"
                                                                    );

                                                            LotteryManager.setJackpot(
                                                                    jackpot
                                                            );

                                                            LotteryManager.setWinningTicket(
                                                                    ticket
                                                            );

                                                            LotteryManager.clearLottery();

                                                            context.getSource()
                                                                    .sendSuccess(

                                                                            () -> Component.literal(
                                                                                    "Lottery created.\n"
                                                                                            + "Jackpot: $"
                                                                                            + jackpot
                                                                                            + "\nWinning Ticket: "
                                                                                            + ticket
                                                                            ),

                                                                            true

                                                                    );

                                                            return 1;

                                                        })

                                        )

                        )

        );

    }

}
