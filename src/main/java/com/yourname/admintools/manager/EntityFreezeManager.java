package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityFreezeManager {


    private static final Set<UUID> FROZEN_ENTITIES =
            ConcurrentHashMap.newKeySet();


    public static void freeze(UUID uuid) {

        FROZEN_ENTITIES.add(uuid);

    }


    public static boolean isFrozen(UUID uuid) {

        return FROZEN_ENTITIES.contains(uuid);

    }


    public static void unfreeze(UUID uuid) {

        FROZEN_ENTITIES.remove(uuid);

    }


}
