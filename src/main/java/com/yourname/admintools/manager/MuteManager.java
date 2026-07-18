package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MuteManager {


    private static final Set<UUID> MUTED_PLAYERS =
            ConcurrentHashMap.newKeySet();


    public static void mute(UUID uuid) {

        MUTED_PLAYERS.add(uuid);

    }


    public static void unmute(UUID uuid) {

        MUTED_PLAYERS.remove(uuid);

    }


    public static boolean isMuted(UUID uuid) {

        return MUTED_PLAYERS.contains(uuid);

    }


}
