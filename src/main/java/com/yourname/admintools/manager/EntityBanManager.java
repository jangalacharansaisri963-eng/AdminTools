package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityBanManager {


    private static final Set<UUID> BANNED_ENTITIES =
            ConcurrentHashMap.newKeySet();


    public static void ban(UUID uuid) {

        BANNED_ENTITIES.add(uuid);

    }


    public static boolean isBanned(UUID uuid) {

        return BANNED_ENTITIES.contains(uuid);

    }


    public static void unban(UUID uuid) {

        BANNED_ENTITIES.remove(uuid);

    }


}
