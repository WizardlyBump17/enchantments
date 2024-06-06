package com.wizardlybump17.enchantments.api.activation;

import lombok.Data;

/**
 * <p>
 *     This is what makes an {@link com.wizardlybump17.enchantments.api.enchantment.Enchantment} activate, be used.
 * </p>
 */
@Data
public class EnchantmentActivation {

    private final int level;
    private final double chance;

    public boolean roll() {
        return Math.random() <= chance;
    }
}
