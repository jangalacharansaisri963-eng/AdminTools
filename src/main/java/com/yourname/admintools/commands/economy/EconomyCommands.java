package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;

public class EconomyCommands {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ) {

        // Economy
        BalanceCommand.register(dispatcher);
        BankCommand.register(dispatcher);

        DepositCommand.register(dispatcher);
        WithdrawCommand.register(dispatcher);

        SendMoneyCommand.register(dispatcher);

        AddMoneyCommand.register(dispatcher);
        RemoveMoneyCommand.register(dispatcher);
        SetMoneyCommand.register(dispatcher);

        RewardCommand.register(dispatcher);
        FineCommand.register(dispatcher);

        EcoCommand.register(dispatcher);

        // Shop
        ShopCommand.register(dispatcher);
        BuyCommand.register(dispatcher);
        SellCommand.register(dispatcher);

        MyShopCommand.register(dispatcher);
        SellCancelCommand.register(dispatcher);

        // Work
        AvailableWorksCommand.register(dispatcher);
        AcceptWorkCommand.register(dispatcher);

        SetupWorkCommand.register(dispatcher);
        CancelWorkCommand.register(dispatcher);
        WorkDoneCommand.register(dispatcher);

        // Bank
        SetBankNameCommand.register(dispatcher);

        // Tax
        TaxCommand.register(dispatcher);
        PayTaxCommand.register(dispatcher);

        // Lottery
        LotteryCommand.register(dispatcher);
        LotteryBuyCommand.register(dispatcher);
        LotteryAddCommand.register(dispatcher);

        // Salary
        SalaryCommand.register(dispatcher);

    }

}
