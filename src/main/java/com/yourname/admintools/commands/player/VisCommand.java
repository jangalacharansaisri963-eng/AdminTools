package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;

public class VisCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("vis")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.removeEffect(
                                    MobEffects.INVISIBILITY
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Invisibility removed"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
