package com.yourname.admintools.events;

import com.yourname.admintools.manager.XrayManager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class XrayTickEvent {


    @SubscribeEvent
    public static void onServerTick(
            TickEvent.ServerTickEvent event
    ) {


        if (event.phase != TickEvent.Phase.END) {
            return;
        }


        for (ServerLevel level :
                event.getServer().getAllLevels()) {


            for (ServerPlayer player :
                    level.players()) {


                if (!XrayManager.isEnabled(player.getUUID())) {
                    continue;
                }


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
                                    block == Blocks.DEEPSLATE_DIAMOND_ORE
                            ) {


                                level.sendParticles(
                                        ParticleTypes.HAPPY_VILLAGER,
                                        pos.getX() + 0.5,
                                        pos.getY() + 0.5,
                                        pos.getZ() + 0.5,
                                        1,
                                        0,
                                        0,
                                        0,
                                        0
                                );


                            }

                        }

                    }

                }


            }

        }


    }

}
