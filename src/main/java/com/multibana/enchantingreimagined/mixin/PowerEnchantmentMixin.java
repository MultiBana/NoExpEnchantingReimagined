package com.multibana.enchantingreimagined.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.multibana.enchantingreimagined.config.Config;
import net.minecraft.enchantment.PowerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowerEnchantment.class)
public class PowerEnchantmentMixin {
    @ModifyReturnValue(method = "getMaxLevel", at=@At("RETURN"))
    private int hookMaxLevel(int original){
        return Config.rules.get("nerf_power") ? 3 : original;
    }
}
