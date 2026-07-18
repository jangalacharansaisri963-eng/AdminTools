package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class NightVisionCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("nightvision")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.NIGHT_VISION,
                                            999999,
                                            0
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Night vision enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
