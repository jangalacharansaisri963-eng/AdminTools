package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public class EntityInfoCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("entityinfo")
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
                                                    "Name: "
                                                    + entity.getName().getString()
                                                    + "\nUUID: "
                                                    + entity.getUUID()
                                                    + "\nID: "
                                                    + entity.getType()
                                                    .builtInRegistryHolder()
                                                    .key()
                                                    .location()
                                            ),
                                            false
                                    );


                                    return 1;

                                })
                        )
        );


    }

}
