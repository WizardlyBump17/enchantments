package com.wizardlybump17.enchantments.paper.persistence;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.enchantments.paper.util.Converter;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentsMapIdType implements PersistentDataType<PersistentDataContainer, Map<Id, Integer>> {

    public static final @NonNull EnchantmentsMapIdType INSTANCE = new EnchantmentsMapIdType();

    @Override
    public @NonNull Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull Class<Map<Id, Integer>> getComplexType() {
        return (Class<Map<Id, Integer>>) (Class<?>) Map.class;
    }

    @Override
    public @NonNull PersistentDataContainer toPrimitive(@NonNull Map<Id, Integer> enchantments, @NonNull PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        enchantments.forEach((id, level) -> container.set(Converter.toNamespacedKey(id), INTEGER, level));
        return container;
    }

    public @NonNull PersistentDataContainer toPrimitiveEnchantment(@NonNull Map<Enchantment<?>, Integer> enchantments, @NonNull PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        enchantments.forEach((enchantment, level) -> container.set(Converter.toNamespacedKey(enchantment.getId()), INTEGER, level));
        return container;
    }

    @Override
    public @NonNull Map<Id, Integer> fromPrimitive(@NonNull PersistentDataContainer container, @NonNull PersistentDataAdapterContext context) {
        Map<Id, Integer> enchantments = new HashMap<>();
        for (NamespacedKey key : container.getKeys())
            enchantments.put(Converter.toId(key), container.get(key, INTEGER));
        return enchantments;
    }

    public @NonNull Map<Enchantment<?>, Integer> fromPrimitiveEnchantment(@NonNull PersistentDataContainer container, @NonNull PersistentDataAdapterContext context) {
        Map<Enchantment<?>, Integer> enchantments = new HashMap<>();
        for (NamespacedKey key : container.getKeys())
            enchantments.put(EnchantmentRegistry.INSTANCE.get(Converter.toId(key)), container.get(key, INTEGER));
        return enchantments;
    }
}
