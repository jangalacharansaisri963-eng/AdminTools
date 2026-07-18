package com.yourname.admintools.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;

import com.yourname.admintools.commands.admin.AdminCommands;
import com.yourname.admintools.commands.entity.EntityCommands;
import com.yourname.admintools.commands.player.PlayerCommands;
import com.yourname.admintools.commands.utility.UtilityCommands;
import com.yourname.admintools.commands.world.WorldCommands;
import com.yourname.admintools.commands.economy.EconomyCommands;

public class CommandManager {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        EntityCommands.register(dispatcher);

        UtilityCommands.register(dispatcher);

        WorldCommands.register(dispatcher);

        PlayerCommands.register(dispatcher);

        AdminCommands.register(dispatcher);

        EconomyCommands.register(dispatcher);
    }

}
