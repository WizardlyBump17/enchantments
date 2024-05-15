package com.wizardlybump17.enchantments.paper.loader;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.loader.EnchantmentLoader;
import lombok.NonNull;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Optional;

public class PaperEnchantmentLoader extends EnchantmentLoader {

    @Override
    public @NonNull Optional<Enchantment> load(@NonNull File file) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Enchantment enchantment = configuration.getObject("data", Enchantment.class);
        return Optional.ofNullable(enchantment);
    }
}
