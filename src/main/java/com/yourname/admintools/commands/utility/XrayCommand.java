package com.yourname.admintools.commands.utility;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class XrayCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {


        dispatcher.register(
                Commands.literal("xray")
                        .executes(context -> {


                            var player =
                                    context.getSource().getPlayer();


                            Level level =
                                    player.level();


                            int radius = 5;


                            BlockPos center =
                                    player.blockPosition();


                            for (int x = -radius; x <= radius; x++) {

                                for (int y = -radius; y <= radius; y++) {

                                    for (int z = -radius; z <= radius; z++) {


                                        BlockPos pos =
                                                center.offset(
                                                        x,
                                                        y,
                                                        z
                                                );


                                        var block =
                                                level.getBlockState(pos)
                                                        .getBlock();


                                        if (
                                                block == Blocks.DIAMOND_ORE ||
                                                block == Blocks.DEEPSLATE_DIAMOND_ORE ||
                                                block == Blocks.EMERALD_ORE ||
                                                block == Blocks.GOLD_ORE ||
                                                block == Blocks.IRON_ORE ||
                                                block == Blocks.REDSTONE_ORE ||
                                                block == Blocks.LAPIS_ORE
                                        ) {


                                            level.addParticle(
                                                    ParticleTypes.HAPPY_VILLAGER,
                                                    pos.getX() + 0.5,
                                                    pos.getY() + 0.5,
                                                    pos.getZ() + 0.5,
                                                    0,
                                                    0,
                                                    0
                                            );


                                        }

                                    }

                                }

                            }


                            context.getSource().sendSuccess(
                                    () -> Component.literal(
                                            "Xray scan complete"
                                    ),
                                    false
                            );


                            return 1;

                        })
        );


    }

}
