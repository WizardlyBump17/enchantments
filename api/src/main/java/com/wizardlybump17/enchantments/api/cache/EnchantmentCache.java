package com.wizardlybump17.enchantments.api.cache;

import com.wizardlybump17.enchantments.api.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.wlib.object.Cache;
import com.wizardlybump17.wlib.object.Pair;
import org.jetbrains.annotations.NotNull;

public class EnchantmentCache extends Cache<Id, Enchantment, Enchantment> {

    @Override
    public @NotNull Pair<Id, Enchantment> apply(Enchantment enchantment) {
        return new Pair<>(enchantment.getId(), enchantment);
    }
}
