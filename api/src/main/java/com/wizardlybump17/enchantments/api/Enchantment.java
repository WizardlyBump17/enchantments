package com.wizardlybump17.enchantments.api;

import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.id.Identifiable;
import lombok.Data;
import lombok.NonNull;

@Data
public abstract class Enchantment implements Identifiable {

    private final @NonNull Id id;
    private String name;
    private int maxLevel;
}
