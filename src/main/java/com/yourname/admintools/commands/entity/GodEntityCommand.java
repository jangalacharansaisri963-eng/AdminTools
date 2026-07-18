package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GodEntityCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("godentity")
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


                                    if (entity instanceof LivingEntity living) {


                                        int duration = 999999;
                                        int amplifier = 255;


                                        living.addEffect(
                                                new MobEffectInstance(
                                                        MobEffects.DAMAGE_RESISTANCE,
                                                        duration,
                                                        amplifier
                                                )
                                        );


                                        living.addEffect(
                                                new MobEffectInstance(
                                                        MobEffects.DAMAGE_BOOST,
                                                        duration,
                                                        amplifier
                                                )
                                        );


                                        living.addEffect(
                                                new MobEffectInstance(
                                                        MobEffects.CONDUIT_POWER,
                                                        duration,
                                                        amplifier
                                                )
                                        );


                                        living.addEffect(
                                                new MobEffectInstance(
                                                        MobEffects.NIGHT_VISION,
                                                        duration,
                                                        amplifier
                                                )
                                        );


                                        context.getSource().sendSuccess(
                                                () -> Component.literal(
                                                        "God mode enabled for "
                                                        + living.getName()
                                                                .getString()
                                                ),
                                                false
                                        );


                                    } else {


                                        context.getSource().sendSuccess(
                                                () -> Component.literal(
                                                        "This entity cannot have effects"
                                                ),
                                                false
                                        );

                                    }


                                    return 1;

                                })
                        )
        );


    }

}
