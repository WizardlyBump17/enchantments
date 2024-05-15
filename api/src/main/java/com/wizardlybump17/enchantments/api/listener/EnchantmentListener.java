package com.wizardlybump17.enchantments.api.listener;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import lombok.NonNull;

public interface EnchantmentListener {

    @NonNull
    Object getKey();

    boolean register(@NonNull Enchantment enchantment);

    void unregister(@NonNull Enchantment enchantment);
}
