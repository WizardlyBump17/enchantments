package com.wizardlybump17.enchantments.paper.wlib.reader;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import com.wizardlybump17.enchantments.api.id.Id;
import com.wizardlybump17.enchantments.api.registry.EnchantmentRegistry;
import com.wizardlybump17.wlib.command.args.reader.ArgsReader;
import org.jetbrains.annotations.Nullable;

public class EnchantmentReader extends ArgsReader<Enchantment<?>> {

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable Class<Enchantment<?>> getType() {
        return (Class<Enchantment<?>>) (Class<?>) Enchantment.class;
    }

    @Override
    public Enchantment<?> read(String id) {
        Enchantment<?> enchantment = EnchantmentRegistry.INSTANCE.get(Id.fromString(id));
        return enchantment.isValid() ? enchantment : null;
    }
}
