package com.multibana.enchantingreimagined;

import com.multibana.enchantingreimagined.config.Config;
import net.fabricmc.api.ModInitializer;
public class NoEXPEnchantingReimagined implements ModInitializer {
	@Override
	public void onInitialize() {
		Config.createIfAbsent();
		Config.reload();

	}
}