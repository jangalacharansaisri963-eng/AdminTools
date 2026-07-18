package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;


import com.yourname.admintools.manager.EconomyManager;
import com.yourname.admintools.manager.ShopManager;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;



public class BuyCommand {



    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){


        dispatcher.register(


                Commands.literal("buy")


                        .then(


                                Commands.argument(
                                        "listing",
                                        IntegerArgumentType.integer(1)
                                )


                                .then(


                                        Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(1)
                                        )


                                        .executes(context -> {



                                            ServerPlayer buyer =
                                                    context.getSource()
                                                    .getPlayer();



                                            int id =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "listing"
                                                    );



                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );



                                            ServerLevel level =
                                                    context.getSource()
                                                    .getLevel();



                                            ShopManager shop =
                                                    ShopManager.get(
                                                            level
                                                    );



                                            ShopManager.ShopItem listing =
                                                    shop.getListing(id);





                                            if(listing == null){


                                                context.getSource()
                                                        .sendFailure(

                                                                Component.literal(
                                                                        "Listing not found"
                                                                )

                                                        );


                                                return 0;


                                            }






                                            if(amount > listing.amount){


                                                context.getSource()
                                                        .sendFailure(

                                                                Component.literal(
                                                                        "Not enough items in listing"
                                                                )

                                                        );


                                                return 0;


                                            }






                                            int total =
                                                    listing.price * amount;






                                            EconomyManager economy =
                                                    EconomyManager.get(level);





                                            if(!economy.removeMoney(

                                                    buyer.getUUID(),

                                                    total

                                            )){


                                                context.getSource()
                                                        .sendFailure(

                                                                Component.literal(
                                                                        "Not enough money"
                                                                )

                                                        );


                                                return 0;


                                            }






                                            economy.addMoney(

                                                    listing.seller,

                                                    total

                                            );






                                            var item =
                                                    BuiltInRegistries.ITEM.get(

                                                            ResourceLocation.tryParse(
                                                                    listing.item
                                                            )

                                                    );






                                            buyer.getInventory()
                                                    .add(

                                                            new ItemStack(

                                                                    item,

                                                                    amount

                                                            )

                                                    );







                                            listing.amount -= amount;





                                            if(
                                                    listing.amount <= 0
                                            ){


                                                shop.removeListing(id);


                                            }




                                            shop.setDirty();






                                            context.getSource()
                                                    .sendSuccess(


                                                            () -> Component.literal(

                                                                    "Bought "
                                                                    + amount
                                                                    + "x "
                                                                    + listing.item

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
