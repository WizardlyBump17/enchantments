package com.wizardlybump17.enchantments.api.item;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import lombok.NonNull;

import java.util.Map;

public interface Item<H> {

    @NonNull
    Map<Enchantment<?>, Integer> getEnchantments();

    @NonNull
    Map<Id, Integer> getEnchantmentIds();

    void addEnchantment(@NonNull Enchantment<?> enchantment, int level);

    void addEnchantment(@NonNull Id id, int level);

    void removeEnchantment(@NonNull Enchantment<?> enchantment);

    void removeEnchantment(@NonNull Id id);

    void clearEnchantments();

    boolean hasEnchantment(@NonNull Enchantment<?> enchantment);

    boolean hasEnchantment(@NonNull Id id);

    int getEnchantmentLevel(@NonNull Enchantment<?> enchantment);

    int getEnchantmentLevel(@NonNull Id id);

    @NonNull H getHandle();

    boolean isValid();
}
