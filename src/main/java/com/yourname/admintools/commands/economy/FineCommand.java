package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class FineCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("fine")
            .requires(source -> source.hasPermission(3))
            .then(Commands.argument("target", EntityArgument.player())
                .executes(context -> {
                    ServerPlayer target = EntityArgument.getPlayer(context, "target");
                    
                    target.sendSystemMessage(Component.literal("§c[ADMIN] You have been fined $1200! Pay within 24 hours or you will be banned for 10 days."));
                    
                    context.getSource().sendSuccess(() -> Component.literal("Fine issued to " + target.getScoreboardName()), true);
                    return 1;
                })
            )
        );
    }
}
