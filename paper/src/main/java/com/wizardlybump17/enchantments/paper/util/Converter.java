package com.wizardlybump17.enchantments.paper.util;

import com.wizardlybump17.enchantments.api.id.Id;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;

@UtilityClass
public class Converter {

    public static @NonNull NamespacedKey toNamespacedKey(@NonNull Id id) {
        return new NamespacedKey(id.namespace(), id.key());
    }

    public static @NonNull Id toId(@NonNull NamespacedKey namespacedKey) {
        return new Id(namespacedKey.getNamespace(), namespacedKey.getKey());
    }
}
