package com.yourname.admintools.commands.economy;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SetBankNameCommand {

    private static String BANK_NAME = "Server Bank";

    public static String getBankName() {
        return BANK_NAME;
    }

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher
    ){

        dispatcher.register(

                Commands.literal("setbankname")

                        .requires(source -> source.hasPermission(2))

                        .then(

                                Commands.argument(
                                        "name",
                                        StringArgumentType.greedyString()
                                )

                                        .executes(context -> {

                                            String name =
                                                    StringArgumentType.getString(
                                                            context,
                                                            "name"
                                                    );

                                            if (name.startsWith("\"") && name.endsWith("\"") && name.length() >= 2) {
                                                name = name.substring(1, name.length() - 1);
                                            }

                                            BANK_NAME = name;

                                            context.getSource()
                                                    .sendSuccess(

                                                            () -> Component.literal(
                                                                    "Bank name changed to: " + BANK_NAME
                                                            ),

                                                            true

                                                    );

                                            return 1;

                                        })

                        )

        );

    }

}
