package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ItemInfoCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("iteminfo")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            ItemStack item =
                                    player.getMainHandItem();


                            if (!item.isEmpty()) {


                                context.getSource().sendSuccess(
                                        () -> Component.literal(
                                                "Item: "
                                                + item.getHoverName()
                                                .getString()
                                                + "\nID: "
                                                + item.getItem()
                                                .builtInRegistryHolder()
                                                .key()
                                                .location()
                                                + "\nCount: "
                                                + item.getCount()
                                        ),
                                        false
                                );


                            }


                            return 1;

                        })
        );


    }

}
