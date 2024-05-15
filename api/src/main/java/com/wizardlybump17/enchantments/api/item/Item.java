package com.wizardlybump17.enchantments.api.item;

import com.wizardlybump17.enchantments.api.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import lombok.NonNull;

import java.util.Map;

public interface Item {

    @NonNull
    Map<Enchantment, Integer> getEnchantments();

    void addEnchantment(@NonNull Enchantment enchantment, int level);

    void removeEnchantment(@NonNull Enchantment enchantment);

    void removeEnchantment(@NonNull Id id);

    void clearEnchantments();

    boolean hasEnchantment(@NonNull Enchantment enchantment);

    boolean hasEnchantment(@NonNull Id id);

    int getEnchantmentLevel(@NonNull Enchantment enchantment);

    int getEnchantmentLevel(@NonNull Id id);
}
