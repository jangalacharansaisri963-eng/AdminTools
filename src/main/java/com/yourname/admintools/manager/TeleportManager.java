package com.yourname.admintools.manager;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TeleportManager {


    private static final Map<UUID, SavedPos> POSITIONS =
            new ConcurrentHashMap<>();


    public static void savePosition(Entity entity) {

        POSITIONS.put(
                entity.getUUID(),
                new SavedPos(
                        entity.level().dimension(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        entity.getYRot(),
                        entity.getXRot()
                )
        );

    }


    public static SavedPos getPosition(UUID uuid) {

        return POSITIONS.get(uuid);

    }


    public static boolean hasPosition(UUID uuid) {

        return POSITIONS.containsKey(uuid);

    }


    public static void clearPosition(UUID uuid) {

        POSITIONS.remove(uuid);

    }



    public record SavedPos(

            ResourceKey<Level> dimensionKey,

            double x,

            double y,

            double z,

            float yRot,

            float xRot

    ) {


        public ServerLevel getLevel(
                MinecraftServer server
        ) {

            return server.getLevel(
                    dimensionKey
            );

        }

    }

}
