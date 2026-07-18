package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class InvisCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("invis")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.INVISIBILITY,
                                            999999,
                                            0
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Invisibility enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
