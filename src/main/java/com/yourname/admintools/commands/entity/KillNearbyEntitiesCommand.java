package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class KillNearbyEntitiesCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("killnearbyentities")
                        .then(
                                Commands.argument(
                                        "radius",
                                        IntegerArgumentType.integer(1, 700)
                                )
                                .executes(context -> {


                                    int radius =
                                            IntegerArgumentType.getInteger(
                                                    context,
                                                    "radius"
                                            );


                                    CommandSourceStack source =
                                            context.getSource();


                                    var level =
                                            source.getLevel();


                                    var position =
                                            source.getPosition();


                                    int killed = 0;


                                    for (Entity entity :
                                            level.getEntities(
                                                    null,
                                                    new net.minecraft.world.phys.AABB(
                                                            position.x - radius,
                                                            position.y - radius,
                                                            position.z - radius,
                                                            position.x + radius,
                                                            position.y + radius,
                                                            position.z + radius
                                                    )
                                            )) {


                                        if (entity instanceof Player) {
                                            continue;
                                        }


                                        entity.kill();

                                        killed++;

                                    }


                                    int finalKilled = killed;


                                    source.sendSuccess(
                                            () -> Component.literal(
                                                    "Killed "
                                                    + finalKilled
                                                    + " nearby entities."
                                            ),
                                            false
                                    );


                                    return 1;

                                })
                        )
        );


    }

}
