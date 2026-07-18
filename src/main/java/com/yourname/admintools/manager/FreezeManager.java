package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FreezeManager {


    private static final Set<UUID> FROZEN_PLAYERS =
            ConcurrentHashMap.newKeySet();


    public static void freeze(UUID uuid) {

        FROZEN_PLAYERS.add(uuid);

    }


    public static void unfreeze(UUID uuid) {

        FROZEN_PLAYERS.remove(uuid);

    }


    public static boolean isFrozen(UUID uuid) {

        return FROZEN_PLAYERS.contains(uuid);

    }


}
