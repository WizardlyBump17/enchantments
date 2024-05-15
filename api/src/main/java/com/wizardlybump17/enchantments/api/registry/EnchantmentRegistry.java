package com.wizardlybump17.enchantments.api.registry;

import com.wizardlybump17.enchantments.api.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnchantmentRegistry {

    public static final @NonNull EnchantmentRegistry INSTANCE = new EnchantmentRegistry();

    private final @NonNull Map<Id, Enchantment> enchantments = new TreeMap<>();

    public @Nullable Enchantment register(@NonNull Enchantment enchantment) {
        return enchantments.put(enchantment.getId(), enchantment);
    }

    public @Nullable Enchantment unregister(@NonNull Enchantment enchantment) {
        return enchantments.remove(enchantment.getId());
    }

    public @Nullable Enchantment unregister(@NonNull Id id) {
        return enchantments.remove(id);
    }

    public @Nullable Enchantment get(@NonNull Id id) {
        return enchantments.get(id);
    }

    public boolean has(@NonNull Enchantment enchantment) {
        return enchantments.containsKey(enchantment.getId());
    }

    public boolean has(@NonNull Id id) {
        return enchantments.containsKey(id);
    }

    public @NonNull Map<Id, Enchantment> getEnchantments() {
        return Collections.unmodifiableMap(enchantments);
    }
}
