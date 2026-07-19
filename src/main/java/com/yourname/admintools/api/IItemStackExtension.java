package com.yourname.admintools.api;

import net.minecraft.world.item.enchantment.Enchantment;

public interface IItemStackExtension {
    // Keeps your original enchantment method
    void admintools$addEnchantment(Enchantment enchantment, int level);
    
    // Adds the new power method
    void admintools$applyGodPower(int level);
}
