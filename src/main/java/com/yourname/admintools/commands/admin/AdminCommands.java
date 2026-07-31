package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

public class AdminCommands {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher,
            CommandBuildContext context
    ) {

        // ------------------------------------------
        // Moderation
        // ------------------------------------------

        KickCommand.register(dispatcher);

        BanCommand.register(dispatcher);
        UnbanCommand.register(dispatcher);
        TempBanCommand.register(dispatcher);

        // ------------------------------------------
        // Chat Moderation
        // ------------------------------------------

        MuteCommand.register(dispatcher);
        UnmuteCommand.register(dispatcher);
        WarnCommand.register(dispatcher);

        MuteChatCommand.register(dispatcher);
        StaffChatCommand.register(dispatcher);
        SocialSpyCommand.register(dispatcher);

        // ------------------------------------------
        // Player Management
        // ------------------------------------------

        ClearInventoryCommand.register(dispatcher);
        FreezeCommand.register(dispatcher);

        // Jail
        JailCommand.register(dispatcher);
        UnJailCommand.register(dispatcher);

        // Ender Chest
        EnderchestCommand.register(dispatcher);

        // Teleport
        TeleportAllCommand.register(dispatcher);

        // Effects
        ClearEffectsCommand.register(dispatcher);

        // ------------------------------------------
        // Permission Commands
        // ------------------------------------------

        OpCommand.register(dispatcher);

        // ------------------------------------------
        // Advanced Commands
        // ------------------------------------------

        EnchantXCommand.register(
                dispatcher,
                context
        );
    }
}
