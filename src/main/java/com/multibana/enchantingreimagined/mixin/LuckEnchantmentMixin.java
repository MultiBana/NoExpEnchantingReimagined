package com.multibana.enchantingreimagined.mixin;

import com.multibana.enchantingreimagined.config.Config;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.LuckEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LuckEnchantment.class)
public class LuckEnchantmentMixin {
    @ModifyReturnValue(method = "getMaxLevel", at=@At("RETURN"))
    private int hookMaxLevel(int original){
        return Config.rules.get("nerf_fortune_and_looting") ? 1 : original;
    }
}
