package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;


import com.yourname.admintools.manager.ShopManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;



public class SellCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){



        dispatcher.register(


                Commands.literal("sell")


                        .then(


                                Commands.argument(
                                        "item",
                                        StringArgumentType.word()
                                )


                                .then(


                                        Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(1)
                                        )


                                        .then(


                                                Commands.argument(
                                                        "price",
                                                        IntegerArgumentType.integer(1)
                                                )


                                                .executes(context -> {



                                                    ServerPlayer player =
                                                            context.getSource()
                                                            .getPlayer();



                                                    String itemName =
                                                            StringArgumentType.getString(
                                                                    context,
                                                                    "item"
                                                            );



                                                    int amount =
                                                            IntegerArgumentType.getInteger(
                                                                    context,
                                                                    "amount"
                                                            );



                                                    int price =
                                                            IntegerArgumentType.getInteger(
                                                                    context,
                                                                    "price"
                                                            );





                                                    var item =
                                                            BuiltInRegistries.ITEM.get(
                                                                    ResourceLocation.tryParse(
                                                                            itemName
                                                                    )
                                                            );





                                                    if(item == null){


                                                        context.getSource()
                                                                .sendFailure(

                                                                        Component.literal(
                                                                                "Invalid item"
                                                                        )

                                                                );


                                                        return 0;


                                                    }





                                                    int removed =
                                                            removeItems(

                                                                    player,

                                                                    itemName,

                                                                    amount

                                                            );





                                                    if(
                                                            removed < amount
                                                    ){


                                                        context.getSource()
                                                                .sendFailure(

                                                                        Component.literal(
                                                                                "You don't have enough items"
                                                                        )

                                                                );


                                                        return 0;


                                                    }





                                                    ServerLevel level =
                                                            context.getSource()
                                                            .getLevel();





                                                    ShopManager shop =
                                                            ShopManager.get(
                                                                    level
                                                            );





                                                    shop.addListing(

                                                            player.getUUID(),

                                                            itemName,

                                                            amount,

                                                            price

                                                    );





                                                    context.getSource()
                                                            .sendSuccess(

                                                                    () -> Component.literal(

                                                                            "Listed "
                                                                            + amount
                                                                            + "x "
                                                                            + itemName
                                                                            + " for $"
                                                                            + price

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






    private static int removeItems(
            ServerPlayer player,
            String itemName,
            int amount
    ){



        int removed = 0;



        for(
                int i = 0;
                i < player.getInventory().getContainerSize();
                i++
        ){


            ItemStack stack =
                    player.getInventory()
                    .getItem(i);



            String id =
                    BuiltInRegistries.ITEM
                    .getKey(
                            stack.getItem()
                    )
                    .toString();





            if(
                    id.equals(itemName)
            ){


                int take =
                        Math.min(
                                stack.getCount(),
                                amount - removed
                        );



                stack.shrink(take);



                removed += take;



                if(
                        removed >= amount
                ){

                    break;

                }


            }


        }



        return removed;


    }



}
