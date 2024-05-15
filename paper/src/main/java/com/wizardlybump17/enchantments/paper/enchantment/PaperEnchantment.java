package com.wizardlybump17.enchantments.paper.enchantment;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.listener.EnchantmentListener;
import com.wizardlybump17.enchantments.paper.holder.PaperEnchantmentHolder;
import lombok.NonNull;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class PaperEnchantment extends Enchantment implements ConfigurationSerializable {

    public PaperEnchantment(@NonNull PaperEnchantmentHolder holder, @NonNull Id id, String name, int maxLevel, @NonNull Map<Object, List<EnchantmentListener>> listeners) {
        super(holder, id, name, maxLevel, listeners);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", getId().toString());
        map.put("name", getName());
        map.put("max-level", getMaxLevel());
        return map;
    }
}
