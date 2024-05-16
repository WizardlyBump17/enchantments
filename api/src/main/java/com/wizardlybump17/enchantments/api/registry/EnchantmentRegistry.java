package com.wizardlybump17.enchantments.api.registry;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.enchantment.InvalidEnchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnchantmentRegistry {

    public static final @NonNull EnchantmentRegistry INSTANCE = new EnchantmentRegistry();

    private final @NonNull Map<Id, Enchantment<?>> enchantments = new TreeMap<>();

    public boolean register(@NonNull Enchantment<?> enchantment) {
        return enchantments.put(enchantment.getId(), enchantment) == null;
    }

    public @NonNull Enchantment<?> unregister(@NonNull Enchantment<?> enchantment) {
        Enchantment<?> removed = enchantments.remove(enchantment.getId());
        return removed == null ? new InvalidEnchantment(enchantment.getId()) : removed;
    }

    public @NonNull Enchantment<?> unregister(@NonNull Id id) {
        Enchantment<?> removed = enchantments.remove(id);
        return removed == null ? new InvalidEnchantment(id) : removed;
    }

    public @NonNull Enchantment<?> get(@NonNull Id id) {
        Enchantment<?> enchantment = enchantments.get(id);
        return enchantment == null ? new InvalidEnchantment(id) : enchantment;
    }

    public boolean has(@NonNull Enchantment<?> enchantment) {
        return enchantments.containsKey(enchantment.getId());
    }

    public boolean has(@NonNull Id id) {
        return enchantments.containsKey(id);
    }

    public @NonNull Map<Id, Enchantment<?>> getEnchantments() {
        return Collections.unmodifiableMap(enchantments);
    }
}
