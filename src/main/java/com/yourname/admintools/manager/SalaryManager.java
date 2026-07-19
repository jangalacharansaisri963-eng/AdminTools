package com.yourname.admintools.manager;

import net.minecraft.server.level.ServerLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SalaryManager {

    public static final int DEFAULT_SALARY = 1000;

    private static final long COOLDOWN =
            24L * 60L * 60L * 1000L;

    private static final Map<UUID, Long> lastSalary =
            new HashMap<>();

    public static boolean canReceiveSalary(
            UUID player
    ) {

        long last =
                lastSalary.getOrDefault(
                        player,
                        0L
                );

        return System.currentTimeMillis() - last
                >= COOLDOWN;

    }

    public static boolean paySalary(
            ServerLevel level,
            UUID player
    ) {

        if (!canReceiveSalary(player)) {
            return false;
        }

        EconomyManager.get(level)
                .addMoney(
                        player,
                        DEFAULT_SALARY
                );

        lastSalary.put(
                player,
                System.currentTimeMillis()
        );

        return true;

    }

}
