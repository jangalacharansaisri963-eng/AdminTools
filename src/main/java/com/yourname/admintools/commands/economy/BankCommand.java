package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import com.yourname.admintools.manager.EconomyManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class BankCommand {

    private static final String BANK_NAME =
            "Server Bank";

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("bank")

                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayer();

                            ServerLevel level =
                                    context.getSource()
                                            .getLevel();

                            EconomyManager economy =
                                    EconomyManager.get(level);

                            int balance =
                                    economy.getBalance(
                                            player.getUUID()
                                    );

                            String status;

                            if(balance >= 1000000000000L){

                                status = "Trillionaire";

                            }else if(balance >= 1000000000){

                                status = "Billionaire";

                            }else if(balance >= 1000000){

                                status = "Millionaire";

                            }else if(balance >= 100000){

                                status = "Wealthy";

                            }else if(balance >= 20000){

                                status = "Upper-Class";

                            }else if(balance >= 5000){

                                status = "Middle-Class";

                            }else if(balance >= 1000){

                                status = "Lower-Class";

                            }else{

                                status = "Poor";

                            }

                            context.getSource().sendSuccess(

                                    () -> Component.literal(
                                            "\n======== Minecraft ========\n\n" +
                                            "Balance : $" + balance + "\n" +
                                            "Status  : " + status + "\n" +
                                            "Bank    : " + BANK_NAME + "\n\n" +
                                            "==========================="
                                    ),

                                    false

                            );

                            return 1;

                        })

        );

    }

}
