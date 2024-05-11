package com.wizardlybump17.enchantments.api;

import lombok.Data;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;

@Data
public class Enchantment implements Listener {

    public static final @NonNull String NAMESPACE = "WB17-Enchantments";
    public static final @NonNull NamespacedKey ENCHANTMENTS = new NamespacedKey(NAMESPACE, "Enchantments");

    private final @NonNull NamespacedKey key;
    private String name;
    private int maxLevel;
}
