package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class NegativeCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("negative")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            int duration = 999999;
                            int amplifier = 254;


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.MOVEMENT_SLOWDOWN,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DIG_SLOWDOWN,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.WEAKNESS,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.BLINDNESS,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DARKNESS,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.CONFUSION,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.HUNGER,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.LEVITATION,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.POISON,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.WITHER,
                                            duration,
                                            amplifier
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Negative effects enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
