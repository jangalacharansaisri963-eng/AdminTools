package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class SmiteCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("smite")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            Level level =
                                    player.level();


                            LightningBolt lightning =
                                    new LightningBolt(
                                            EntityType.LIGHTNING_BOLT,
                                            level
                                    );


                            lightning.moveTo(
                                    player.getX(),
                                    player.getY(),
                                    player.getZ()
                            );


                            level.addFreshEntity(
                                    lightning
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Smited!"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
