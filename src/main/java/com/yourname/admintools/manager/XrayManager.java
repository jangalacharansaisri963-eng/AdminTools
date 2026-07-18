package com.yourname.admintools.manager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class XrayManager {


    private static final Set<UUID> XRAY_PLAYERS =
            ConcurrentHashMap.newKeySet();



    public static void enable(UUID uuid) {

        XRAY_PLAYERS.add(uuid);

    }



    public static void disable(UUID uuid) {

        XRAY_PLAYERS.remove(uuid);

    }



    public static void toggle(UUID uuid) {

        if (XRAY_PLAYERS.contains(uuid)) {

            XRAY_PLAYERS.remove(uuid);

        } else {

            XRAY_PLAYERS.add(uuid);

        }

    }



    public static boolean isEnabled(UUID uuid) {

        return XRAY_PLAYERS.contains(uuid);

    }


}
