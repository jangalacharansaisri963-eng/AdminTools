package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.yourname.admintools.manager.BankManager;
import com.yourname.admintools.manager.LotteryManager;

public class LotteryCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(

                Commands.literal("lottery")

                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayer();

                            context.getSource()
                                    .sendSuccess(

                                            () -> Component.literal(

                                                    "========== LOTTERY ==========\n"

                                                    + "Bank: "
                                                    + BankManager.getBankName()

                                                    + "\n\n"

                                                    + "Jackpot: $"
                                                    + LotteryManager.getJackpot()

                                                    + "\n"

                                                    + "Ticket Price: $1000"

                                                    + "\n"

                                                    + "Owned Tickets: "
                                                    + LotteryManager
                                                            .getTickets(
                                                                    player.getUUID()
                                                            )
                                                            .size()

                                                    + "\n\n"

                                                    + "Use /lotterybuy <1-10>"

                                            ),

                                            false

                                    );

                            return 1;

                        })

        );

    }

}
