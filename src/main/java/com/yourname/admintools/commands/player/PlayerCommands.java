package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;


public class PlayerCommands {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        HealCommand.register(dispatcher);

        FeedCommand.register(dispatcher);

        RestoreCommand.register(dispatcher);

        ReviveCommand.register(dispatcher);

        GodCommand.register(dispatcher);

        SpeedCommand.register(dispatcher);

        BurnCommand.register(dispatcher);

        SmiteCommand.register(dispatcher);

        GodSmiteCommand.register(dispatcher);

        LaunchCommand.register(dispatcher);


        InvisCommand.register(dispatcher);

        VisCommand.register(dispatcher);


        ExtinguishCommand.register(dispatcher);

        NightVisionCommand.register(dispatcher);

        FlySpeedCommand.register(dispatcher);


        TeleportCommand.register(dispatcher);


        // V2 Player Commands
        ExplodeCommand.register(dispatcher);

        GhostCommand.register(dispatcher);

        SudoCommand.register(dispatcher);


    }

}
