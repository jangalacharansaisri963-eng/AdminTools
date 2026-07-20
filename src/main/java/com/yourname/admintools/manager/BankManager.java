package com.yourname.admintools.manager;

import net.minecraft.server.level.ServerLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BankManager {

    private static String bankName = "Server Bank";

    private static final Map<UUID, Integer> bankBalances =
            new HashMap<>();

    private static final Map<UUID, Boolean> frozenAccounts =
            new HashMap<>();

    public static String getBankName() {
        return bankName;
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getBankBalance(
            UUID player
    ) {

        return bankBalances.getOrDefault(
                player,
                0
        );

    }

    public static void deposit(
            ServerLevel level,
            UUID player,
            int amount
    ) {

        if (isFrozen(player)) {
            return;
        }

        EconomyManager economy =
                EconomyManager.get(level);

        if (!economy.removeMoney(player, amount)) {
            return;
        }

        bankBalances.put(
                player,
                getBankBalance(player) + amount
        );

    }

    public static boolean withdraw(
            ServerLevel level,
            UUID player,
            int amount
    ) {

        if (isFrozen(player)) {
            return false;
        }

        int bank =
                getBankBalance(player);

        if (bank < amount) {
            return false;
        }

        bankBalances.put(
                player,
                bank - amount
        );

        EconomyManager.get(level)
                .addMoney(player, amount);

        return true;

    }

    public static void freezeAccount(
            UUID player
    ) {

        frozenAccounts.put(
                player,
                true
        );

    }

    public static void unfreezeAccount(
            UUID player
    ) {

        frozenAccounts.remove(
                player
        );

    }

    public static boolean isFrozen(
            UUID player
    ) {

        return frozenAccounts.getOrDefault(
                player,
                false
        );

    }

}
