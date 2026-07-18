package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class GodCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("god")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            int duration = 999999;
                            int amplifier = 255;


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DAMAGE_RESISTANCE,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DAMAGE_BOOST,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.CONDUIT_POWER,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.NIGHT_VISION,
                                            duration,
                                            amplifier
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "God mode enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
