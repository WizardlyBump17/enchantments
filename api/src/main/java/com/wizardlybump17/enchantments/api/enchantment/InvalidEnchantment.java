package com.wizardlybump17.enchantments.api.enchantment;

import com.wizardlybump17.enchantments.api.activation.EnchantmentActivation;
import com.wizardlybump17.enchantments.api.holder.EnchantmentHolder;
import com.wizardlybump17.enchantments.api.id.Id;
import lombok.NonNull;

import java.util.Collections;

/**
 * <p>
 *     An invalid {@link Enchantment} object, so all {@link Enchantment}s can be non-null.
 * </p>
 */
public final class InvalidEnchantment extends Enchantment<EnchantmentActivation> {

    public InvalidEnchantment(@NonNull Id id) {
        super(EnchantmentHolder.EMPTY, id, "Invalid", 0, Collections.emptyMap(), Collections.emptyList());
    }
}
