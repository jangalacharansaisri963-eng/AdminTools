package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import com.yourname.admintools.manager.EconomyManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;



public class SetMoneyCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(


                Commands.literal("setmoney")


                        .then(


                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )


                                .then(


                                        Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )


                                        .executes(context -> {



                                            ServerPlayer player =
                                                    EntityArgument.getPlayer(
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



                                            EconomyManager economy =
                                                    EconomyManager.get(
                                                            level
                                                    );



                                            economy.setMoney(
                                                    player.getUUID(),
                                                    amount
                                            );



                                            context.getSource()
                                                    .sendSuccess(


                                                            () -> Component.literal(

                                                                    "Set "
                                                                    + player.getName()
                                                                    .getString()
                                                                    + " balance to $"
                                                                    + amount

                                                            ),


                                                            false

                                                    );



                                            return 1;



                                        })


                                )


                        )


        );



    }


}
