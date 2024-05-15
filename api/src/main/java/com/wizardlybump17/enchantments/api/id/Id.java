package com.wizardlybump17.enchantments.api.id;

import lombok.NonNull;

public record Id(@NonNull String namespace, @NonNull String key) implements Comparable<Id> {

    public static final @NonNull String NAMESPACE = "WB17_Enchantments";
    public static @NonNull Id ENCHANTMENTS = new Id(NAMESPACE, "Enchantments");

    @Override
    public @NonNull String toString() {
        return namespace + ":" + key;
    }

    @Override
    public int compareTo(@NonNull Id other) {
        return toString().compareTo(other.toString());
    }
}
