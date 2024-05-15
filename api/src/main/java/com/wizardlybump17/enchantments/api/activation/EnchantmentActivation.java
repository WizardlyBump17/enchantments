package com.wizardlybump17.enchantments.api.activation;

import lombok.Data;

@Data
public abstract class EnchantmentActivation {

    private final int level;
    private final double chance;

    public boolean roll() {
        return Math.random() <= chance;
    }
}
