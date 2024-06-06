package com.wizardlybump17.enchantments.api.holder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * <p>
 *     An interface that represents an object that can hold {@link com.wizardlybump17.enchantments.api.enchantment.Enchantment}s.
 * </p>
 */
public interface EnchantmentHolder {

    @NonNull
    Empty EMPTY = new Empty();

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    class Empty implements EnchantmentHolder {
    }
}
