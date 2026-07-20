package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import com.yourname.admintools.manager.TaxManager;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class TaxCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("tax")

                        .requires(source -> source.hasPermission(2))

                        .then(

                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )

                                        .then(

                                                Commands.argument(
                                                        "amount",
                                                        IntegerArgumentType.integer(1)
                                                )

                                                        .executes(context -> {

                                                            ServerPlayer target =
                                                                    EntityArgument.getPlayer(
                                                                            context,
                                                                            "player"
                                                                    );

                                                            int amount =
                                                                    IntegerArgumentType.getInteger(
                                                                            context,
                                                                            "amount"
                                                                    );

                                                            TaxManager.createTax(
                                                                    target.getUUID(),
                                                                    amount
                                                            );

                                                            context.getSource()
                                                                    .sendSuccess(

                                                                            () -> Component.literal(
                                                                                    "Tax of $" + amount +
                                                                                            " assigned to " +
                                                                                            target.getName().getString()
                                                                            ),

                                                                            true

                                                                    );

                                                            target.sendSystemMessage(

                                                                    Component.literal(
                                                                            "You have received a tax bill of $" +
                                                                                    amount +
                                                                                    ". Use /paytax before it expires."
                                                                    )

                                                            );

                                                            return 1;

                                                        })

                                        )

                        )

        );

    }

}
