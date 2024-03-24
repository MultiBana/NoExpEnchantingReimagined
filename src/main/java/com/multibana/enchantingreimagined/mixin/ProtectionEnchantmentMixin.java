package com.multibana.enchantingreimagined.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.multibana.enchantingreimagined.config.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin {
    @ModifyReturnValue(method = "getMaxLevel", at=@At("RETURN"))
    private int hookMaxLevel(int original){
        return Config.rules.get("nerf_protections") ? 2 : original;
    }
    @ModifyArgs(method = "<init>", at=@At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;<init>(Lnet/minecraft/enchantment/Enchantment$Rarity;Lnet/minecraft/enchantment/EnchantmentTarget;[Lnet/minecraft/entity/EquipmentSlot;)V"))
    private static void changeSuper(Args args, Enchantment.Rarity weight, ProtectionEnchantment.Type protectionType, EquipmentSlot... slotTypes){
        switch (protectionType) {
            case PROJECTILE -> {args.set(1, EnchantmentTarget.ARMOR_HEAD); args.set(2, new EquipmentSlot[]{EquipmentSlot.HEAD});}
            case EXPLOSION -> {args.set(1, EnchantmentTarget.ARMOR_CHEST); args.set(2, new EquipmentSlot[]{EquipmentSlot.CHEST});}
            case FIRE -> {args.set(1, EnchantmentTarget.ARMOR_LEGS); args.set(2, new EquipmentSlot[]{EquipmentSlot.LEGS});}
            case FALL -> {args.set(1, EnchantmentTarget.ARMOR_FEET); args.set(2, new EquipmentSlot[]{EquipmentSlot.FEET});}
        }
    }
}
