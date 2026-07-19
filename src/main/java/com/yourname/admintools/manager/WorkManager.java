package com.yourname.admintools.manager;

import java.util.ArrayList;
import java.util.HashMap; // Added import
import java.util.List;
import java.util.UUID;   // Added import

public class WorkManager {

    // --- YOUR ORIGINAL CODE ---
    private static final List<String> works = new ArrayList<>();

    public static void addWork(String work) {
        works.add(work);
    }

    public static List<String> getWorks() {
        return new ArrayList<>(works);
    }

    public static boolean removeWork(int id) {
        if (id < 0 || id >= works.size()) {
            return false;
        }
        works.remove(id);
        return true;
    }

    // --- ADDED TIMING LOGIC (Nothing of yours was removed) ---
    private static final HashMap<UUID, Long> workStartTimes = new HashMap<>();

    public static void startWork(UUID playerUUID) {
        workStartTimes.put(playerUUID, System.currentTimeMillis());
    }

    public static Long getElapsedTimeSeconds(UUID playerUUID) {
        if (!workStartTimes.containsKey(playerUUID)) return null;
        return (System.currentTimeMillis() - workStartTimes.get(playerUUID)) / 1000;
    }

    public static void clearSession(UUID playerUUID) {
        workStartTimes.remove(playerUUID);
    }
}
