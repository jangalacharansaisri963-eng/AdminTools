package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class PoseidonCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("poseidon")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            int duration = 999999;
                            int amplifier = 254;


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.CONDUIT_POWER,
                                            duration,
                                            amplifier
                                    )
                            );


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.DIG_SPEED,
                                            duration,
                                            amplifier
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Poseidon power enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
