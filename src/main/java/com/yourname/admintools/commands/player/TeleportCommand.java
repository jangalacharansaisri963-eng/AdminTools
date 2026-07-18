package com.yourname.admintools.commands.player;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.yourname.admintools.manager.TeleportManager;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.Entity;


import java.util.Comparator;
import java.util.UUID;


public class TeleportCommand {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {



        /*
         * /tphere <entity>
         */

        dispatcher.register(
                Commands.literal("tphere")
                        .then(
                                Commands.argument(
                                        "entity",
                                        EntityArgument.entity()
                                )
                                .executes(context -> {


                                    ServerPlayer sender =
                                            context.getSource()
                                                    .getPlayerOrException();


                                    Entity target =
                                            EntityArgument.getEntity(
                                                    context,
                                                    "entity"
                                            );


                                    // Save the entity being moved
                                    TeleportManager.savePosition(target);



                                    if(target.level() != sender.level()) {

                                        target.changeDimension(
                                                (ServerLevel) sender.level()
                                        );

                                    }



                                    target.teleportTo(
                                            sender.getX(),
                                            sender.getY(),
                                            sender.getZ()
                                    );



                                    sender.sendSystemMessage(
                                            Component.literal(
                                                    "Teleported entity here."
                                            )
                                    );


                                    return 1;

                                })
                        )
        );



        /*
         * /tpentity
         */

        dispatcher.register(
                Commands.literal("tpentity")
                        .executes(context -> {


                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayerOrException();



                            Entity nearest =
                                    player.level()
                                            .getEntities(
                                                    player,
                                                    player.getBoundingBox()
                                                            .inflate(20),
                                                    Entity::isAlive
                                            )
                                            .stream()
                                            .min(
                                                    Comparator.comparingDouble(
                                                            e -> e.distanceToSqr(player)
                                                    )
                                            )
                                            .orElse(null);



                            if(nearest == null) {


                                player.sendSystemMessage(
                                        Component.literal(
                                                "No entity nearby."
                                        )
                                );


                                return 0;

                            }



                            TeleportManager.savePosition(player);



                            player.teleportTo(
                                    nearest.getX(),
                                    nearest.getY(),
                                    nearest.getZ()
                            );


                            player.sendSystemMessage(
                                    Component.literal(
                                            "Teleported to nearest entity."
                                    )
                            );


                            return 1;

                        })
        );




        /*
         * /tpuuid <uuid>
         */

        dispatcher.register(
                Commands.literal("tpuuid")
                        .then(
                                Commands.argument(
                                        "uuid",
                                        StringArgumentType.greedyString()
                                )
                                .executes(context -> {


                                    ServerPlayer player =
                                            context.getSource()
                                                    .getPlayerOrException();



                                    UUID uuid;


                                    try {


                                        uuid =
                                                UUID.fromString(
                                                        StringArgumentType.getString(
                                                                context,
                                                                "uuid"
                                                        )
                                                );


                                    }
                                    catch(Exception e) {


                                        player.sendSystemMessage(
                                                Component.literal(
                                                        "Invalid UUID."
                                                )
                                        );


                                        return 0;

                                    }



                                    Entity entity = null;



                                    for(ServerLevel level :
                                            context.getSource()
                                                    .getServer()
                                                    .getAllLevels()) {


                                        Entity found =
                                                level.getEntity(uuid);



                                        if(found != null) {

                                            entity = found;

                                            break;

                                        }

                                    }



                                    if(entity == null) {


                                        player.sendSystemMessage(
                                                Component.literal(
                                                        "Entity not found."
                                                )
                                        );


                                        return 0;

                                    }



                                    TeleportManager.savePosition(player);



                                    if(player.level() != entity.level()) {


                                        player.changeDimension(
                                                (ServerLevel) entity.level()
                                        );

                                    }



                                    player.teleportTo(
                                            entity.getX(),
                                            entity.getY(),
                                            entity.getZ()
                                    );



                                    return 1;

                                })
                        )
        );




        /*
         * /tplast
         */

        dispatcher.register(
                Commands.literal("tplast")
                        .executes(context -> {


                            ServerPlayer player =
                                    context.getSource()
                                            .getPlayerOrException();



                            TeleportManager.SavedPos pos =
                                    TeleportManager.getPosition(
                                            player.getUUID()
                                    );



                            if(pos == null) {


                                player.sendSystemMessage(
                                        Component.literal(
                                                "No teleport history found."
                                        )
                                );


                                return 0;

                            }



                            ServerLevel level =
                                    pos.getLevel(
                                            context.getSource()
                                                    .getServer()
                                    );



                            if(level == null) {


                                player.sendSystemMessage(
                                        Component.literal(
                                                "Saved dimension no longer exists."
                                        )
                                );


                                return 0;

                            }



                            if(player.level() != level) {


                                player.changeDimension(level);

                            }



                            player.teleportTo(
                                    pos.x(),
                                    pos.y(),
                                    pos.z()
                            );


                            return 1;

                        })
        );


    }

}
