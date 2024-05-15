package com.wizardlybump17.enchantments.api.listener;

import lombok.NonNull;

public interface EnchantmentListener {

    @NonNull
    Object getKey();

    boolean register();

    void unregister();
}
