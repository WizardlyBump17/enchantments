package com.wizardlybump17.enchantments.api.enchantment;

import com.wizardlybump17.enchantments.api.activation.EnchantmentActivation;
import com.wizardlybump17.enchantments.api.holder.EnchantmentHolder;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.id.Identifiable;
import com.wizardlybump17.enchantments.api.listener.EnchantmentListener;
import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Data
public abstract class Enchantment<A extends EnchantmentActivation> implements Identifiable {

    private final @NonNull EnchantmentHolder holder;
    private final @NonNull Id id;
    private final String name;
    private final int maxLevel;
    private final @NonNull Map<Object, List<EnchantmentListener>> listeners;
    private final @NonNull Map<Integer, A> activations = new TreeMap<>();

    public Enchantment(@NonNull EnchantmentHolder holder, @NonNull Id id, String name, int maxLevel, @NonNull Map<Object, List<EnchantmentListener>> listeners, @NonNull List<A> activations) {
        this.holder = holder;
        this.id = id;
        this.name = name;
        this.maxLevel = maxLevel;
        this.listeners = listeners;
        for (A activation : activations)
            this.activations.put(activation.getLevel(), activation);

        registerListeners();
    }

    protected void registerListeners() {
        for (List<EnchantmentListener> listeners : listeners.values())
            listeners.removeIf(listener -> !listener.register(this));
    }

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

    public void clearListeners() {
        listeners.values().forEach(listeners -> listeners.forEach(listener -> listener.unregister(this)));
        listeners.clear();
    }

    public final boolean isValid() {
        return !(this instanceof InvalidEnchantment);
    }

    public boolean register() {
        return true;
    }

    public void unregister() {
        clearListeners();
    }

    public @NonNull Optional<A> getActivation(int level) {
        return Optional.ofNullable(activations.get(level));
    }
}
