package com.wizardlybump17.enchantments.paper.activation;

import com.wizardlybump17.enchantments.api.activation.EnchantmentActivation;
import lombok.NonNull;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class PaperEnchantmentActivation extends EnchantmentActivation implements ConfigurationSerializable {

    public PaperEnchantmentActivation(int level, double chance) {
        super(level, chance);
    }

    @Override
    public @NonNull Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("level", getLevel());
        map.put("chance", getChance());
        return map;
    }
}
