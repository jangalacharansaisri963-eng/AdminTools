package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import com.yourname.admintools.manager.EntityFreezeManager;

public class FreezeEntityCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("freezeentity")
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


                                    EntityFreezeManager.freeze(
                                            entity.getUUID()
                                    );


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Frozen entity: "
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
