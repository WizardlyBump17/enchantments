package com.wizardlybump17.enchantments.api.enchantment;

import com.wizardlybump17.enchantments.api.id.Id;
import lombok.NonNull;

import java.util.Collections;

public final class InvalidEnchantment extends Enchantment {

    public InvalidEnchantment(@NonNull Id id) {
        super(id, Collections.emptyMap());
    }
}
