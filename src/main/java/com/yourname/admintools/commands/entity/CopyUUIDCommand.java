package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public class CopyUUIDCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("copyuuid")
                        .then(
                                Commands.argument(
                                        "entity",
                                        EntityArgument.entity()
                                )
                                .executes(context -> {


                                    Entity entity =
                                            EntityArgument.getEntity(
                                                    context,
                                                    "entity"
                                            );


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "UUID: "
                                                    + entity.getUUID()
                                            ),
                                            false
                                    );


                                    return 1;

                                })
                        )
        );


    }

}
