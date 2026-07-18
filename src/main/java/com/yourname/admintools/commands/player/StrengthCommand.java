package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class StrengthCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("strength")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DAMAGE_BOOST,
                                            999999,
                                            254
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Strength enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
