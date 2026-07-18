package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;


import com.yourname.admintools.manager.ShopManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;



public class ShopCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(


                Commands.literal("shop")


                        .executes(context -> {



                            ServerLevel level =
                                    context.getSource()
                                    .getLevel();



                            ShopManager shop =
                                    ShopManager.get(level);




                            StringBuilder text =
                                    new StringBuilder();



                            text.append(
                                    "Shop Listings:\n\n"
                            );



                            int id = 1;



                            for(
                                    ShopManager.ShopItem item :
                                    shop.getListings()
                            ){



                                text.append("#")
                                        .append(id)
                                        .append(" ")

                                        .append(item.amount)
                                        .append("x ")

                                        .append(item.item)

                                        .append(" $")

                                        .append(item.price)

                                        .append("\n");



                                id++;


                            }




                            if(id == 1){


                                text.append(
                                        "No items found"
                                );


                            }




                            context.getSource()
                                    .sendSuccess(

                                            () -> Component.literal(
                                                    text.toString()
                                            ),

                                            false

                                    );



                            return 1;



                        })


        );



    }


}
