package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import com.yourname.admintools.manager.EntityBanManager;

public class BanEntityCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("banentity")
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


                                    EntityBanManager.ban(
                                            entity.getUUID()
                                    );


                                    entity.discard();


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "Entity banned: "
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
