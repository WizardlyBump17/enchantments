package com.wizardlybump17.enchantments.paper;

import com.wizardlybump17.enchantments.api.loader.EnchantmentLoader;
import com.wizardlybump17.enchantments.paper.enchantment.VanillaEnchantment;
import com.wizardlybump17.enchantments.paper.loader.PaperEnchantmentLoader;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaperSupport {

    public static void start() {
        EnchantmentLoader.setInstance(new PaperEnchantmentLoader());
        VanillaEnchantment.registerEnchantments();
    }
}
