package com.wizardlybump17.enchantments.paper.item;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.item.Item;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.enchantments.paper.enchantment.VanillaEnchantment;
import com.wizardlybump17.enchantments.paper.persistence.EnchantmentsMapType;
import com.wizardlybump17.enchantments.paper.util.Converter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class PaperItem implements Item<ItemStack> {

    public static final @NonNull NamespacedKey ENCHANTMENTS = Converter.toNamespacedKey(Id.ENCHANTMENTS);

    private final @NonNull ItemStack item;

    @Override
    public @NonNull Map<Enchantment<?>, Integer> getEnchantments() {
        if (!isValid())
            return Collections.emptyMap();
        return getEnchantments(item.getItemMeta().getPersistentDataContainer());
    }

    @Override
    public void addEnchantment(@NonNull Enchantment<?> enchantment, int level) {
        if (!isValid())
            return;

        Map<Enchantment<?>, Integer> enchantments = getEnchantments();
        if (level < 1)
            enchantments.remove(enchantment);
        else
            enchantments.put(enchantment, level);

        saveEnchantments(enchantments);

        if (!(enchantment instanceof VanillaEnchantment vanillaEnchantment))
            return;

        org.bukkit.enchantments.Enchantment vanilla = vanillaEnchantment.getVanilla();
        if (level < 1)
            item.removeEnchantment(vanilla);
        else
            item.addUnsafeEnchantment(vanilla, level);
    }

    @Override
    public void removeEnchantment(@NonNull Enchantment<?> enchantment) {
        if (!isValid())
            return;

        Map<Enchantment<?>, Integer> enchantments = getEnchantments();
        enchantments.remove(enchantment);
        saveEnchantments(enchantments);
        if (enchantment instanceof VanillaEnchantment vanillaEnchantment)
            item.removeEnchantment(vanillaEnchantment.getVanilla());
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
        if (!isValid())
            return;

        ItemMeta meta = item.getItemMeta();
        setEnchantments(meta.getPersistentDataContainer(), enchantments);
        item.setItemMeta(meta);
    }

    @Override
    public @NonNull ItemStack getHandle() {
        return item;
    }

    @Override
    public boolean isValid() {
        return item.getItemMeta() != null;
    }

    public static @NonNull PaperItem of(@Nullable ItemStack item) {
        return new PaperItem(item == null ? new ItemStack(Material.AIR) : item);
    }

    public static @NonNull Map<Enchantment<?>, Integer> getEnchantments(@NonNull PersistentDataContainer container) {
        Map<Enchantment<?>, Integer> enchantments = container.get(ENCHANTMENTS, EnchantmentsMapType.INSTANCE);
        return enchantments == null ? new HashMap<>() : enchantments;
    }


    public static void setEnchantments(@NonNull PersistentDataContainer container, @NonNull Map<Enchantment<?>, Integer> enchantments) {
        container.set(ENCHANTMENTS, EnchantmentsMapType.INSTANCE, enchantments);
    }
}
