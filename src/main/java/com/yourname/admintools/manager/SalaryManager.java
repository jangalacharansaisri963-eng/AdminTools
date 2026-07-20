package com.yourname.admintools.manager;

import net.minecraft.server.level.ServerLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SalaryManager {

    private static final Map<UUID, Integer> salaries =
            new HashMap<>();

    private static final Map<UUID, Long> nextPayDay =
            new HashMap<>();

    public static final long SALARY_INTERVAL =
            30L * 24000L;

    public static void setSalary(
            UUID player,
            int amount,
            long currentDay
    ) {

        salaries.put(
                player,
                amount
        );

        nextPayDay.put(
                player,
                currentDay + SALARY_INTERVAL
        );

    }

    public static int getSalary(
            UUID player
    ) {

        return salaries.getOrDefault(
                player,
                0
        );

    }

    public static boolean hasSalary(
            UUID player
    ) {

        return salaries.containsKey(
                player
        );

    }

    public static void removeSalary(
            UUID player
    ) {

        salaries.remove(
                player
        );

        nextPayDay.remove(
                player
        );

    }

    public static void tick(
            ServerLevel level
    ) {

        long day =
                level.getDayTime();

        for(UUID player : salaries.keySet()){

            if(day >= nextPayDay.get(player)){

                EconomyManager.get(level)
                        .addMoney(
                                player,
                                salaries.get(player)
                        );

                nextPayDay.put(
                        player,
                        day + SALARY_INTERVAL
                );

            }

        }

    }

}
