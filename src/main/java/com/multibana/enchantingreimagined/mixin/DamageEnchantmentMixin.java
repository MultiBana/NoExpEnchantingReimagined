package com.multibana.enchantingreimagined.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.multibana.enchantingreimagined.config.Config;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
	@ModifyReturnValue(method = "getMaxLevel", at=@At("RETURN"))
	private int sharpnessMaxLevel(int original){
		return Config.rules.get("nerf_sharpness") ? 3 : original;
	}
	@Inject(method = "isAcceptableItem", at=@At("HEAD"), cancellable = true)
	private void sharpnessAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
		if(stack.getItem() instanceof TridentItem && Config.rules.get("enable_trident_sharpness")){
			cir.setReturnValue(true);
		}
	}
}