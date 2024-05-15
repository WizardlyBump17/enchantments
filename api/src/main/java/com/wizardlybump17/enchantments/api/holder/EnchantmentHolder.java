package com.wizardlybump17.enchantments.api.holder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public interface EnchantmentHolder {

    @NonNull
    Empty EMPTY = new Empty();

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    class Empty implements EnchantmentHolder {
    }
}
