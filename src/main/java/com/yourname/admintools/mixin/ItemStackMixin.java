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

    // Keep @Unique here because this field is only used inside this mixin class
    @Unique
    private static final UUID GOD_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    // Removed @Unique so the public interface contract can be safely fulfilled
    @Override
    public void admintools$addEnchantment(Enchantment enchantment, int level) {
        ItemStack self = (ItemStack) (Object) this;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(self);
        enchantments.put(enchantment, level);
        EnchantmentHelper.setEnchantments(enchantments, self);
    }

    // Removed @Unique so your other mod classes can actually see and call this method
    @Override
    public void admintools$applyGodPower(int level) {
        ItemStack self = (ItemStack) (Object) this;
        
        self.addAttributeModifier(
            Attributes.ATTACK_DAMAGE, 
            new AttributeModifier(GOD_DAMAGE_UUID, "GodPower", (double) level, AttributeModifier.Operation.ADDITION), 
            EquipmentSlot.MAINHAND
        );
    }
}
