package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class FlySpeedCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("flyspeed")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            player.getAbilities()
                                    .setFlyingSpeed(0.2F);

                            player.onUpdateAbilities();


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Fly speed increased"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
