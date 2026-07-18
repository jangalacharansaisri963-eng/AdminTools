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



public class RemoveMoneyCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){


        dispatcher.register(


                Commands.literal("removemoney")


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



                                            boolean removed =
                                                    economy.removeMoney(
                                                            player.getUUID(),
                                                            amount
                                                    );



                                            if(!removed){


                                                context.getSource()
                                                        .sendFailure(
                                                                Component.literal(
                                                                        "Player does not have enough money"
                                                                )
                                                        );


                                                return 0;


                                            }




                                            context.getSource()
                                                    .sendSuccess(


                                                            () -> Component.literal(

                                                                    "Removed $"
                                                                    + amount
                                                                    + " from "
                                                                    + player.getName()
                                                                    .getString()

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
