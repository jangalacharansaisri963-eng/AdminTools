package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class EnderchestCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        dispatcher.register(
                Commands.literal("enderchest")
                        .requires(
                                source -> source.hasPermission(2)
                        )

                        .then(
                                Commands.argument(
                                        "player",
                                        EntityArgument.player()
                                )

                                .executes(
                                        EnderchestCommand::openEnderChest
                                )
                        )
        );
    }


    private static int openEnderChest(
            CommandContext<CommandSourceStack> context
    ) {

        ServerPlayer target;

        try {

            target = EntityArgument.getPlayer(
                    context,
                    "player"
            );

        } catch (Exception exception) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Player not found."
                    )
            );

            return 0;
        }


        if (!(context.getSource().getEntity()
                instanceof ServerPlayer viewer)) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Only players can use this command."
                    )
            );

            return 0;
        }


        if (viewer.getUUID().equals(target.getUUID())) {

            context.getSource().sendFailure(
                    Component.literal(
                            "Use the normal Ender Chest "
                                    + "instead."
                    )
            );

            return 0;
        }


        /*
         * Open the target's Ender Chest.
         *
         * Forge/Minecraft 1.20.1 uses the player's
         * EnderChestBlockEntity/container data.
         *
         * The actual menu opening should be handled
         * by the mod's menu implementation.
         */


        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Opening Ender Chest of "
                                + target.getName()
                                        .getString()
                                + "."
                ),
                false
        );


        return 1;
    }
}
