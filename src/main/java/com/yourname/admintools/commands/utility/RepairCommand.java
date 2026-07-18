package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class RepairCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("repair")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            ItemStack item =
                                    player.getMainHandItem();


                            if (!item.isEmpty()
                                    && item.isDamageableItem()) {


                                item.setDamageValue(0);


                                context.getSource().sendSuccess(
                                        () -> Component.literal(
                                                "Item repaired"
                                        ),
                                        false
                                );


                            } else {


                                context.getSource().sendSuccess(
                                        () -> Component.literal(
                                                "Hold a repairable item"
                                        ),
                                        false
                                );


                            }


                            return 1;

                        })
        );


    }

}
