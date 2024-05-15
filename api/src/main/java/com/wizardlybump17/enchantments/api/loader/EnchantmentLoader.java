package com.wizardlybump17.enchantments.api.loader;

import com.wizardlybump17.enchantments.api.enchantment.Enchantment;
import lombok.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class EnchantmentLoader {

    private static EnchantmentLoader instance;

    public static void setInstance(@NonNull EnchantmentLoader instance) {
        if (EnchantmentLoader.instance != null)
            throw new IllegalStateException("The instance is already set");
        EnchantmentLoader.instance = instance;
    }

    public static @NonNull EnchantmentLoader getInstance() {
        return instance;
    }

    public abstract @NonNull Optional<Enchantment> load(@NonNull File file);

    public @NonNull List<Enchantment> loadAll(@NonNull File folder) {
        File[] files = folder.listFiles();
        if (files == null)
            return Collections.emptyList();

        List<Enchantment> enchantments = new ArrayList<>();
        for (File file : files) {
            if (!isValidFile(file))
                continue;

            if (file.isDirectory()) {
                enchantments.addAll(loadAll(file));
                continue;
            }

            Optional<Enchantment> optional = load(file);
            optional.ifPresent(enchantment -> {
                if (enchantment.register())
                    enchantments.add(enchantment);
            });
        }

        return enchantments;
    }

    protected boolean isValidFile(@NonNull File file) {
        return !file.getName().startsWith(".");
    }
}
