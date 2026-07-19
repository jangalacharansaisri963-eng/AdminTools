package com.yourname.admintools.mixin;

import com.yourname.admintools.api.IItemStackExtension;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;
import java.util.UUID;

@Mixin(ItemStack.class)
public class ItemStackMixin implements IItemStackExtension {

    @Unique
    private static final UUID GOD_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    @Unique
    @Override
    public void admintools$addEnchantment(Enchantment enchantment, int level) {
        ItemStack self = (ItemStack) (Object) this;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(self);
        enchantments.put(enchantment, level);
        EnchantmentHelper.setEnchantments(enchantments, self);
    }

    @Unique
    @Override
    public void admintools$applyGodPower(int level) {
        ItemStack self = (ItemStack) (Object) this;
        
        // Remove existing attribute to prevent stacking
        self.removeAttributeModifier(Attributes.ATTACK_DAMAGE, GOD_DAMAGE_UUID);
        
        // Add new damage attribute
        self.addAttributeModifier(
            Attributes.ATTACK_DAMAGE, 
            new AttributeModifier(GOD_DAMAGE_UUID, "GodPower", (double) level, AttributeModifier.Operation.ADDITION), 
            EquipmentSlot.MAINHAND
        );
    }
}
