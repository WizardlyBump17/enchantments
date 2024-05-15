package com.wizardlybump17.enchantments.paper.persistence;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.enchantments.paper.util.Converter;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public final class EnchantmentsMapType implements PersistentDataType<PersistentDataContainer, Map<Enchantment, Integer>> {

    public static final @NonNull EnchantmentsMapType INSTANCE = new EnchantmentsMapType();

    @Override
    public @NonNull Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull Class<Map<Enchantment, Integer>> getComplexType() {
        return (Class<Map<Enchantment, Integer>>) (Class<?>) Map.class;
    }

    @Override
    public @NonNull PersistentDataContainer toPrimitive(@NonNull Map<Enchantment, Integer> enchantments, @NonNull PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        enchantments.forEach((enchantment, level) -> container.set(Converter.toNamespacedKey(enchantment.getId()), INTEGER, level));
        return container;
    }

    @Override
    public @NonNull Map<Enchantment, Integer> fromPrimitive(@NonNull PersistentDataContainer container, @NonNull PersistentDataAdapterContext context) {
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        for (NamespacedKey key : container.getKeys())
            enchantments.put(EnchantmentRegistry.INSTANCE.get(Converter.toId(key)), container.get(key, INTEGER));
        return enchantments;
    }
}
