package com.multibana.enchantingreimagined.mixin;

import com.multibana.enchantingreimagined.config.Config;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin {
    @Inject(method = "canAccept", at=@At("HEAD"), cancellable = true)
    private void infinityAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if(other instanceof PowerEnchantment && Config.rules.get("power_no_infinity")){
            cir.setReturnValue(false);
        }
    }
}
