package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.yourname.admintools.manager.EconomyManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;



public class BalanceCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(


                Commands.literal("balance")


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



                            int balance =
                                    economy.getBalance(
                                            player.getUUID()
                                    );



                            context.getSource()
                                    .sendSuccess(


                                            () -> Component.literal(

                                                    "Balance: $"
                                                    + balance

                                            ),


                                            false


                                    );



                            return 1;



                        })


        );



    }


}
