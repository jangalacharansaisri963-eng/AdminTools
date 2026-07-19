package com.yourname.admintools.mixin;

import com.yourname.admintools.api.IItemStackExtension;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(ItemStack.class)
public class ItemStackMixin implements IItemStackExtension {

    @Unique
    @Override
    public void admintools$addEnchantment(Enchantment enchantment, int level) {
        // Cast 'this' to ItemStack to interact with it as a proper object
        ItemStack self = (ItemStack) (Object) this;
        
        // Get the current enchantments on the item
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(self);
        
        // Add the new enchantment (this will overwrite existing levels of the same enchantment)
        enchantments.put(enchantment, level);
        
        // Save the updated map back to the item stack
        EnchantmentHelper.setEnchantments(enchantments, self);
    }
}
