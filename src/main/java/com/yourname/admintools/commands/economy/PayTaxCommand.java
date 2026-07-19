package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import com.yourname.admintools.manager.TaxManager;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class PayTaxCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("paytax")

                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayer();

                            ServerLevel level =
                                    context.getSource()
                                            .getLevel();

                            if(!TaxManager.hasTax(
                                    player.getUUID()
                            )){

                                context.getSource()
                                        .sendFailure(

                                                Component.literal(
                                                        "You have no pending taxes."
                                                )

                                        );

                                return 0;

                            }

                            int tax =
                                    TaxManager.getTax(
                                            player.getUUID()
                                    );

                            if(

                                    TaxManager.payTax(
                                            level,
                                            player.getUUID()
                                    )

                            ){

                                context.getSource()
                                        .sendSuccess(

                                                () -> Component.literal(
                                                        "You paid your tax of $" + tax
                                                ),

                                                false

                                        );

                            }else{

                                context.getSource()
                                        .sendFailure(

                                                Component.literal(
                                                        "You don't have enough money to pay your taxes."
                                                )

                                        );

                            }

                            return 1;

                        })

        );

    }

}
