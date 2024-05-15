package com.wizardlybump17.enchantments.paper.listener;

import com.wizardlybump17.enchantments.api.Enchantment;
import com.wizardlybump17.enchantments.api.listener.EnchantmentListener;
import lombok.Builder;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

@Builder
public class EventListener<E extends Event> implements EnchantmentListener, Listener {

    private final @NonNull Class<E> eventClass;
    private final @NonNull Plugin plugin;
    private final @NonNull Consumer<E> executor;
    private final @NonNull @Builder.Default EventPriority priority = EventPriority.NORMAL;
    private final boolean ignoreCancelled;

    @Override
    public @NonNull Object getKey() {
        return eventClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean register(@NonNull Enchantment enchantment) {
        Bukkit.getPluginManager().registerEvent(eventClass, this, priority, (listener, event) -> executor.accept((E) event), plugin, ignoreCancelled);
        return true;
    }

    @Override
    public void unregister(@NonNull Enchantment enchantment) {
        HandlerList.unregisterAll(this);
    }
}
