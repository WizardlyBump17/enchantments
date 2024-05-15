package com.wizardlybump17.enchantments.paper.holder;

import com.wizardlybump17.enchantments.api.holder.EnchantmentHolder;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;

public record PaperEnchantmentHolder(@NonNull Plugin plugin) implements EnchantmentHolder {
}
