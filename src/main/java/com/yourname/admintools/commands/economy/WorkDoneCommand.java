package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.yourname.admintools.manager.WorkManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class WorkDoneCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("workdone")
            .requires(source -> source.hasPermission(2))
            .then(Commands.argument("target", EntityArgument.player())
                .then(Commands.argument("workName", StringArgumentType.greedyString())
                    .executes(context -> {
                        ServerPlayer target = EntityArgument.getPlayer(context, "target");
                        String workName = StringArgumentType.getString(context, "workName");
                        Long elapsed = WorkManager.getElapsedTimeSeconds(target.getUUID());

                        if (elapsed == null) {
                            context.getSource().sendFailure(Component.literal("Player has no active work session."));
                            return 0;
                        }

                        context.getSource().sendSuccess(() -> Component.literal(
                            "Work '" + workName + "' finished for " + target.getScoreboardName() + 
                            ". Time taken: " + elapsed + " seconds."
                        ), true);
                        
                        WorkManager.clearSession(target.getUUID());
                        return 1;
                    })
                )
            )
        );
    }
}
