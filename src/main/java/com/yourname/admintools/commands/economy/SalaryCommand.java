package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import com.yourname.admintools.manager.SalaryManager;

public class SalaryCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(

                Commands.literal("salary")

                        .requires(source ->
                                source.hasPermission(2)
                        )

                        .then(

                                Commands.argument(
                                        "player",
                                        net.minecraft.commands.arguments.EntityArgument.player()
                                )

                                        .then(

                                                Commands.argument(
                                                        "amount",
                                                        IntegerArgumentType.integer(
                                                                1
                                                        )
                                                )

                                                        .executes(context -> {

                                                            ServerPlayer target =
                                                                    net.minecraft.commands.arguments.EntityArgument.getPlayer(
                                                                            context,
                                                                            "player"
                                                                    );

                                                            int amount =
                                                                    IntegerArgumentType.getInteger(
                                                                            context,
                                                                            "amount"
                                                                    );

                                                            ServerLevel level =
                                                                    context.getSource()
                                                                            .getLevel();

                                                            SalaryManager.setSalary(

                                                                    target.getUUID(),

                                                                    amount,

                                                                    level.getDayTime()

                                                            );

                                                            context.getSource()
                                                                    .sendSuccess(

                                                                            () -> Component.literal(

                                                                                    "Salary created for "
                                                                                            + target.getName().getString()
                                                                                            + ": $"
                                                                                            + amount
                                                                                            + " every 30 Minecraft days."

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
