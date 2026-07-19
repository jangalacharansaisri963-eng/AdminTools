package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import com.yourname.admintools.manager.EconomyManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class SalaryCommand {

    private static final int SALARY_AMOUNT = 1000;

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("salary")

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
                                    SALARY_AMOUNT
                            );

                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "You received your salary of $" + SALARY_AMOUNT
                                    ),
                                    false
                            );

                            return 1;

                        })

        );

    }

}
