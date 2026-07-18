package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;

public class UtilityCommands {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        FlyCommand.register(dispatcher);

        RepairCommand.register(dispatcher);

        ItemInfoCommand.register(dispatcher);

        PlayerInfoCommand.register(dispatcher);

        XrayCommand.register(dispatcher);

        BackCommand.register(dispatcher);

        NearbyCommand.register(dispatcher);

        CoordsCommand.register(dispatcher);

        DimensionCommand.register(dispatcher);

        PlaytimeCommand.register(dispatcher);

        SpawnCommand.register(dispatcher);

        TopCommand.register(dispatcher);

    }

}
