package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class GodSmiteCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("godsmite")
                        .then(
                                Commands.argument(
                                        "target",
                                        EntityArgument.entity()
                                )
                                .executes(context -> {


                                    Entity target =
                                            EntityArgument.getEntity(
                                                    context,
                                                    "target"
                                            );


                                    Level level =
                                            target.level();


                                    LightningBolt lightning =
                                            new LightningBolt(
                                                    EntityType.LIGHTNING_BOLT,
                                                    level
                                            );


                                    lightning.moveTo(
                                            target.getX(),
                                            target.getY(),
                                            target.getZ()
                                    );


                                    level.addFreshEntity(
                                            lightning
                                    );


                                    target.hurt(
                                            level.damageSources().lightningBolt(),
                                            1000000F
                                    );


                                    context.getSource().sendSuccess(
                                            () -> Component.literal(
                                                    "God smite unleashed"
                                            ),
                                            false
                                    );


                                    return 1;

                                })
                        )
        );


    }

}
