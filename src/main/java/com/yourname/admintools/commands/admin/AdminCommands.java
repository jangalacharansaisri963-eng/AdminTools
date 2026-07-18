package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public class AdminCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        KickCommand.register(dispatcher);

        // Ban system
        BanCommand.register(dispatcher);
        UnbanCommand.register(dispatcher);

        // Chat moderation
        MuteCommand.register(dispatcher);
        UnmuteCommand.register(dispatcher);
        WarnCommand.register(dispatcher);

        // Player management
        ClearInventoryCommand.register(dispatcher);
        FreezeCommand.register(dispatcher);

        // Permission commands
        OpCommand.register(dispatcher);
    }
}
