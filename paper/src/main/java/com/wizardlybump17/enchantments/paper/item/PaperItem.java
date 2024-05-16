package com.wizardlybump17.enchantments.paper.item;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.item.Item;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.enchantments.paper.persistence.EnchantmentsMapType;
import com.wizardlybump17.enchantments.paper.util.Converter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class PaperItem implements Item {

    public static final @NonNull NamespacedKey ENCHANTMENTS = Converter.toNamespacedKey(Id.ENCHANTMENTS);

    private final @NonNull ItemStack item;

    @Override
    public @NonNull Map<Enchantment<?>, Integer> getEnchantments() {
        Map<Enchantment<?>, Integer> enchantments = item.getItemMeta().getPersistentDataContainer().get(ENCHANTMENTS, EnchantmentsMapType.INSTANCE);
        return enchantments == null ? new HashMap<>() : enchantments;
    }

    @Override
    public void addEnchantment(@NonNull Enchantment<?> enchantment, int level) {
        Map<Enchantment<?>, Integer> enchantments = getEnchantments();
        enchantments.put(enchantment, level);
        saveEnchantments(enchantments);
    }

    @Override
    public void removeEnchantment(@NonNull Enchantment<?> enchantment) {
        Map<Enchantment<?>, Integer> enchantments = getEnchantments();
        enchantments.remove(enchantment);
        saveEnchantments(enchantments);
    }

    @Override
    public void removeEnchantment(@NonNull Id id) {
        removeEnchantment(EnchantmentRegistry.INSTANCE.get(id));
    }

    @Override
    public void clearEnchantments() {
        saveEnchantments(Collections.emptyMap());
    }

    @Override
    public boolean hasEnchantment(@NonNull Enchantment<?> enchantment) {
        return getEnchantments().containsKey(enchantment);
    }

    @Override
    public boolean hasEnchantment(@NonNull Id id) {
        return hasEnchantment(EnchantmentRegistry.INSTANCE.get(id));
    }

    @Override
    public int getEnchantmentLevel(@NonNull Enchantment<?> enchantment) {
        return getEnchantments().getOrDefault(enchantment, 0);
    }

    @Override
    public int getEnchantmentLevel(@NonNull Id id) {
        return getEnchantmentLevel(EnchantmentRegistry.INSTANCE.get(id));
    }

    protected void saveEnchantments(@NonNull Map<Enchantment<?>, Integer> enchantments) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(ENCHANTMENTS, EnchantmentsMapType.INSTANCE, enchantments);
        item.setItemMeta(meta);
    }
}
