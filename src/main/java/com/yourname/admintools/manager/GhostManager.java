package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GhostManager {


    private static final Set<UUID> GHOSTED_PLAYERS =
            ConcurrentHashMap.newKeySet();


    public static boolean isGhosted(UUID uuid) {

        return GHOSTED_PLAYERS.contains(uuid);

    }


    public static void ghost(UUID uuid) {

        GHOSTED_PLAYERS.add(uuid);

    }


    public static void unghost(UUID uuid) {

        GHOSTED_PLAYERS.remove(uuid);

    }


}
