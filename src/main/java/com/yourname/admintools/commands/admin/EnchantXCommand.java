package com.yourname.admintools.commands.admin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import com.yourname.admintools.api.IItemStackExtension;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;

import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantXCommand {

    public static void register(
            CommandDispatcher<CommandSourceStack> dispatcher,
            CommandBuildContext buildContext
    ) {

        dispatcher.register(

                Commands.literal("enchantx")
                        .requires(source -> source.hasPermission(2))
                        .then(

                                Commands.argument("target", EntityArgument.player())
                                        .then(

                                                Commands.argument(
                                                        "enchantment",
                                                        ResourceArgument.resource(
                                                                buildContext,
                                                                Registries.ENCHANTMENT
                                                        )
                                                )
                                                .then(

                                                        Commands.argument("level", IntegerArgumentType.integer(1, 1000))
                                                                .executes(context -> {

                                                                    Player target = EntityArgument.getPlayer(context, "target");

                                                                    Holder<Enchantment> enchantment = ResourceArgument.getEnchantment(context, "enchantment");

                                                                    int level = IntegerArgumentType.getInteger(context, "level");

                                                                    ItemStack stack = target.getMainHandItem();

                                                                    if (stack.isEmpty()) {

                                                                        context.getSource().sendFailure(
                                                                                Component.literal("Target is not holding an item.")
                                                                        );

                                                                        return 0;

                                                                    }

                                                                    ((IItemStackExtension) (Object) stack).admintools$addEnchantment(
                                                                            enchantment.value(),
                                                                            level
                                                                    );

                                                                    context.getSource().sendSuccess(
                                                                            () -> Component.literal(
                                                                                    "Applied "
                                                                                    + enchantment.value().getDescriptionId()
                                                                                    + " level "
                                                                                    + level
                                                                                    + " to "
                                                                                    + stack.getHoverName().getString()
                                                                            ),
                                                                            true
                                                                    );

                                                                    return 1;

                                                                })
                                                )
                                        )
                        )
        );
    }
}
