package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;


import com.yourname.admintools.manager.EconomyManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;



public class EcoCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(


                Commands.literal("eco")


                        .then(

                                Commands.literal("give")


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



                                                            ServerLevel level =
                                                                    context.getSource()
                                                                    .getLevel();



                                                            EconomyManager economy =
                                                                    EconomyManager.get(level);



                                                            economy.addMoney(

                                                                    target.getUUID(),

                                                                    amount

                                                            );



                                                            context.getSource()
                                                                    .sendSuccess(

                                                                            () -> Component.literal(

                                                                                    "Gave $"
                                                                                    + amount
                                                                                    + " to "
                                                                                    + target.getName()
                                                                                    .getString()

                                                                            ),

                                                                            false
                                                                    );


                                                            return 1;


                                                        })

                                                )


                                        )

                        )





                        .then(

                                Commands.literal("take")


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



                                                            ServerLevel level =
                                                                    context.getSource()
                                                                    .getLevel();



                                                            EconomyManager economy =
                                                                    EconomyManager.get(level);



                                                            economy.removeMoney(

                                                                    target.getUUID(),

                                                                    amount

                                                            );



                                                            context.getSource()
                                                                    .sendSuccess(

                                                                            () -> Component.literal(

                                                                                    "Removed $"
                                                                                    + amount
                                                                                    + " from "
                                                                                    + target.getName()
                                                                                    .getString()

                                                                            ),

                                                                            false
                                                                    );



                                                            return 1;



                                                        })


                                                )


                                        )


                        )




        );


    }


                                                                              }
