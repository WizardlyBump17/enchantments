package com.wizardlybump17.enchantments.api.enchantment;

import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.id.Identifiable;
import com.wizardlybump17.enchantments.api.listener.EnchantmentListener;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Data
@SuperBuilder
public abstract class Enchantment implements Identifiable {

    private final @NonNull Id id;
    private final String name;
    private final int maxLevel;
    private final @NonNull @Builder.Default Map<Object, List<EnchantmentListener>> listeners = new HashMap<>();

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
        return listeners.getOrDefault(listener.getKey(), Collections.emptyList()).contains(listener);
    }

    public void clearListeners(@NonNull Object key) {
        for (EnchantmentListener listener : listeners.remove(key))
            listener.unregister(this);
    }

    public final boolean isValid() {
        return !(this instanceof InvalidEnchantment);
    }

    public abstract static class EnchantmentBuilder {

        public @NonNull EnchantmentBuilder addListener(@NonNull EnchantmentListener listener) {
            listeners$value.put(listener.getKey(), Collections.singletonList(listener));
            return this;
        }

        public @NonNull EnchantmentBuilder removeListener(@NonNull EnchantmentListener listener) {
            listeners$value.computeIfPresent(listener.getKey(), (key, listeners) -> {
                listeners.remove(listener);
                return listeners;
            });
            return this;
        }
    }
}
