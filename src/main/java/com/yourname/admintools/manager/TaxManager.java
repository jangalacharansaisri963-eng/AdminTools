package com.yourname.admintools.manager;

import net.minecraft.server.level.ServerLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaxManager {

    private static final Map<UUID, Integer> taxes =
            new HashMap<>();

    private static final Map<UUID, Long> dueDates =
            new HashMap<>();

    public static final long TAX_DURATION =
            48L * 60L * 60L * 1000L;

    public static void createTax(
            UUID player,
            int amount
    ) {

        taxes.put(
                player,
                amount
        );

        dueDates.put(
                player,
                System.currentTimeMillis() + TAX_DURATION
        );

    }

    public static int getTax(
            UUID player
    ) {

        return taxes.getOrDefault(
                player,
                0
        );

    }

    public static boolean hasTax(
            UUID player
    ) {

        return taxes.containsKey(player);

    }

    public static boolean payTax(
            ServerLevel level,
            UUID player
    ) {

        if (!hasTax(player)) {
            return false;
        }

        int amount =
                taxes.get(player);

        EconomyManager economy =
                EconomyManager.get(level);

        if (!economy.removeMoney(player, amount)) {
            return false;
        }

        taxes.remove(player);
        dueDates.remove(player);

        BankManager.unfreezeAccount(
                player
        );

        return true;

    }

    public static boolean isExpired(
            UUID player
    ) {

        if (!dueDates.containsKey(player)) {
            return false;
        }

        return System.currentTimeMillis()
                >= dueDates.get(player);

    }

    public static void updateExpiredTaxes() {

        for (UUID player : dueDates.keySet()) {

            if (
                    isExpired(player)
                    &&
                    !BankManager.isFrozen(player)
            ) {

                BankManager.freezeAccount(
                        player
                );

            }

        }

    }

}
