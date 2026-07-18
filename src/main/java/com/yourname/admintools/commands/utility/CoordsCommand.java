package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;

public class CoordsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("coords")
                        .executes(context -> {

                            ServerPlayer player =
                                    context.getSource().getPlayerOrException();

                            BlockPos pos = player.blockPosition();

                            ResourceLocation dimension =
                                    player.level().dimension().location();

                            Holder<Biome> biomeHolder =
                                    player.level().getBiome(pos);

                            String biomeString =
                                    biomeHolder.unwrapKey()
                                            .map(key -> key.location().toString())
                                            .orElse("unknown:unregistered_biome");

                            player.sendSystemMessage(
                                    Component.literal(
                                            "§6--- Coordinates ---§r\n" +
                                            "§eX:§r " + pos.getX() + "\n" +
                                            "§eY:§r " + pos.getY() + "\n" +
                                            "§eZ:§r " + pos.getZ() + "\n\n" +
                                            "§bDimension:§r " + dimension + "\n" +
                                            "§aBiome:§r " + biomeString
                                    )
                            );

                            return 1;
                        })
        );
    }
}
