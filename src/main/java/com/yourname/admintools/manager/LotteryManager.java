package com.yourname.admintools.manager;

import java.util.*;

public class LotteryManager {

    public static final int MAX_TICKETS = 10;

    private static long jackpot = 45000000L;

    private static String winningTicket =
            "0000-0000-0000";

    private static final Map<UUID, List<String>> tickets =
            new HashMap<>();

    public static void setJackpot(
            long amount
    ) {

        jackpot = amount;

    }

    public static long getJackpot() {

        return jackpot;

    }

    public static void setWinningTicket(
            String ticket
    ) {

        winningTicket = ticket;

    }

    public static String getWinningTicket() {

        return winningTicket;

    }

    public static List<String> getTickets(
            UUID player
    ) {

        return tickets.computeIfAbsent(
                player,
                p -> new ArrayList<>()
        );

    }

    public static boolean buyTicket(
            UUID player
    ) {

        List<String> playerTickets =
                getTickets(player);

        if (playerTickets.size() >= MAX_TICKETS) {
            return false;
        }

        playerTickets.add(
                generateTicket()
        );

        return true;

    }

    public static boolean isWinner(
            UUID player
    ) {

        return getTickets(player)
                .contains(winningTicket);

    }

    public static void clearLottery() {

        tickets.clear();

    }

    public static String generateTicket() {

        Random random =
                new Random();

        return String.format(
                "%04d-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000)
        );

    }

}
