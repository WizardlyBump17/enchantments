package com.wizardlybump17.enchantments.api;

import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.id.Identifiable;
import com.wizardlybump17.enchantments.api.listener.EnchantmentListener;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public abstract class Enchantment implements Identifiable {

    private final @NonNull Id id;
    private String name;
    private int maxLevel;
    private final @NonNull Map<Object, List<EnchantmentListener>> listeners;

    public void addListener(@NonNull EnchantmentListener listener) {
        if (listener.register(this))
            listeners.computeIfAbsent(listener.getKey(), $ -> new ArrayList<>()).add(listener);
    }

    public void removeListener(@NonNull EnchantmentListener listener) {
        this.listeners.computeIfPresent(listener.getKey(), (key, listeners) -> {
            listeners.remove(listener);
            listener.unregister(this);
            return listeners;
        });
    }

    public boolean hasListener(@NonNull EnchantmentListener listener) {
        return listeners.computeIfAbsent(listener, $ -> Collections.emptyList()).contains(listener);
    }

    public void clearListeners(@NonNull Object key) {
        for (EnchantmentListener listener : listeners.remove(key))
            listener.unregister(this);
    }
}
