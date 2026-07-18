package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public class KillEntityCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("killentity")
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


                                    entity.kill();


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Killed entity: "
                                                    + entity.getName()
                                                    .getString()
                                            ),
                                            false
                                    );


                                    return 1;

                                })
                        )
        );


    }

}
