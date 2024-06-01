package com.wizardlybump17.enchantments.paper.enchantment;

import com.wizardlybump17.enchantments.api.activation.EnchantmentActivation;
import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.holder.EnchantmentHolder;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.enchantments.paper.util.Converter;
import com.wizardlybump17.enchantments.paper.util.StringUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collections;

@Getter
public final class VanillaEnchantment extends Enchantment<EnchantmentActivation> {

    private final @NonNull org.bukkit.enchantments.Enchantment vanilla;

    public VanillaEnchantment(@NonNull org.bukkit.enchantments.Enchantment vanilla) {
        super(
                VanillaHolder.INSTANCE,
                Converter.toId(vanilla.getKey()),
                StringUtil.word(vanilla.getKey().getKey()),
                vanilla.getMaxLevel(),
                Collections.emptyMap(),
                Collections.emptyList()
        );
        this.vanilla = vanilla;
    }

    public static void registerEnchantments() {
        for (org.bukkit.enchantments.Enchantment enchantment : org.bukkit.enchantments.Enchantment.values())
            EnchantmentRegistry.INSTANCE.register(new VanillaEnchantment(enchantment));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class VanillaHolder implements EnchantmentHolder {

        public static final @NonNull VanillaHolder INSTANCE = new VanillaHolder();
    }
}
