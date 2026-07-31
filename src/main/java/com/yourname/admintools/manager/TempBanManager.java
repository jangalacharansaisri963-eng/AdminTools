package com.yourname.admintools.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TempBanManager {

    private static final Map<UUID, TempBan> BANS = new HashMap<>();

    // =========================================================
    // Add Ban
    // =========================================================

    public static void addBan(
            UUID uuid,
            String playerName,
            long durationMillis,
            String reason
    ) {

        long expirationTime =
                System.currentTimeMillis() + durationMillis;

        BANS.put(
                uuid,
                new TempBan(
                        uuid,
                        playerName,
                        expirationTime,
                        reason
                )
        );
    }

    // =========================================================
    // Remove Ban
    // =========================================================

    public static boolean removeBan(UUID uuid) {

        return BANS.remove(uuid) != null;
    }

    // =========================================================
    // Check Ban
    // =========================================================

    public static boolean isBanned(UUID uuid) {

        TempBan ban = BANS.get(uuid);

        if (ban == null) {
            return false;
        }

        if (ban.isExpired()) {

            BANS.remove(uuid);

            return false;
        }

        return true;
    }

    // =========================================================
    // Get Ban
    // =========================================================

    public static TempBan getBan(UUID uuid) {

        if (!isBanned(uuid)) {
            return null;
        }

        return BANS.get(uuid);
    }

    // =========================================================
    // Get Remaining Time
    // =========================================================

    public static long getRemainingMillis(UUID uuid) {

        TempBan ban = getBan(uuid);

        if (ban == null) {
            return 0;
        }

        return ban.getRemainingMillis();
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public static void cleanupExpiredBans() {

        BANS.entrySet().removeIf(
                entry -> entry.getValue().isExpired()
        );
    }

    // =========================================================
    // Count
    // =========================================================

    public static int getBanCount() {

        cleanupExpiredBans();

        return BANS.size();
    }

    // =========================================================
    // Temp Ban Data
    // =========================================================

    public static class TempBan {

        private final UUID uuid;
        private final String playerName;
        private final long expirationTime;
        private final String reason;

        public TempBan(
                UUID uuid,
                String playerName,
                long expirationTime,
                String reason
        ) {

            this.uuid = uuid;
            this.playerName = playerName;
            this.expirationTime = expirationTime;
            this.reason = reason;
        }

        public UUID uuid() {
            return uuid;
        }

        public String playerName() {
            return playerName;
        }

        public long expirationTime() {
            return expirationTime;
        }

        public String reason() {
            return reason;
        }

        public boolean isExpired() {

            return System.currentTimeMillis()
                    >= expirationTime;
        }

        public long getRemainingMillis() {

            return Math.max(
                    expirationTime
                            - System.currentTimeMillis(),
                    0
            );
        }
    }
}
