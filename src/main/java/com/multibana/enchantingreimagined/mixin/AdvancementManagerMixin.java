package com.multibana.enchantingreimagined.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.multibana.enchantingreimagined.config.Config;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AdvancementManager.class)
public class AdvancementManagerMixin {
    // never load the enchant_item achievement (it requires enchantment table)
    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/Advancement$Builder;build(Lnet/minecraft/util/Identifier;)Lnet/minecraft/advancement/Advancement;"), cancellable = true)
    private void noAdvancements(Map<Identifier, Advancement.Builder> advancements, CallbackInfo ci, @Local Identifier identifier){
        if(identifier.toString().contains("enchant_item") && Config.rules.get("remove_enchant_achievement")){
            ci.cancel();
        }
    }
}
