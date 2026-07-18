package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class GlowCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("glow")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.addEffect(
                                    new MobEffectInstance(
                                            MobEffects.GLOWING,
                                            999999,
                                            0
                                    )
                            );


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Glow enabled"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
