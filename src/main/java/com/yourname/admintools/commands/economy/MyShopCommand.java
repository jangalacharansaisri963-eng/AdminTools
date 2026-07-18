package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;

import com.yourname.admintools.manager.ShopManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;



public class MyShopCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){


        dispatcher.register(

                Commands.literal("myshop")

                        .executes(context -> {


                            ServerPlayer player =
                                    context.getSource()
                                    .getPlayer();


                            ServerLevel level =
                                    context.getSource()
                                    .getLevel();



                            ShopManager shop =
                                    ShopManager.get(level);



                            StringBuilder text =
                                    new StringBuilder(
                                            "Your Listings:\n"
                                    );



                            int id = 1;



                            for(
                                    ShopManager.ShopItem item :
                                    shop.getListings()
                            ){


                                if(
                                        item.seller.equals(
                                                player.getUUID()
                                        )
                                ){


                                    text.append("#")
                                            .append(id)
                                            .append(" ")
                                            .append(item.item)
                                            .append("\n");


                                }


                                id++;


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
