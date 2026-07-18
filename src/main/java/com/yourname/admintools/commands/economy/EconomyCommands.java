package com.yourname.admintools.commands.economy;


import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;



public class EconomyCommands {


    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){


        BalanceCommand.register(dispatcher);

        ShopCommand.register(dispatcher);

        SellCommand.register(dispatcher);

        BuyCommand.register(dispatcher);

        MyShopCommand.register(dispatcher);

        SellCancelCommand.register(dispatcher);

        AvailableWorksCommand.register(dispatcher);

        AcceptWorkCommand.register(dispatcher);


        AddMoneyCommand.register(dispatcher);

        SetMoneyCommand.register(dispatcher);

        RemoveMoneyCommand.register(dispatcher);

        SendMoneyCommand.register(dispatcher);


        SetupWorkCommand.register(dispatcher);

        CancelWorkCommand.register(dispatcher);
        EcoCommand.register(dispatcher);


    }

}
