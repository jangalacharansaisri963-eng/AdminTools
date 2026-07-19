package com.yourname.admintools.manager;

import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class RewardManager {

    public static final int DEFAULT_REWARD = 500;

    public static void rewardPlayer(
            ServerLevel level,
            UUID player
    ) {

        EconomyManager.get(level)
                .addMoney(
                        player,
                        DEFAULT_REWARD
                );

    }

    public static void rewardPlayer(
            ServerLevel level,
            UUID player,
            int amount
    ) {

        EconomyManager.get(level)
                .addMoney(
                        player,
                        amount
                );

    }

}
