package com.yourname.admintools.commands.entity;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;

public class EntityCommands {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        EntityInfoCommand.register(dispatcher);

        CopyUUIDCommand.register(dispatcher);

        KillEntityCommand.register(dispatcher);

        KickEntityCommand.register(dispatcher);

        KillNearbyEntitiesCommand.register(dispatcher);

        BanEntityCommand.register(dispatcher);

        FreezeEntityCommand.register(dispatcher);

        GodEntityCommand.register(dispatcher);


    }

}
