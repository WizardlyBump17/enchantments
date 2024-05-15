package com.wizardlybump17.enchantments.paper.wlib.reader.com.wizardlybump17.enchantments.paper.wlib;

import com.wizardlybump17.enchantments.paper.wlib.reader.EnchantmentReader;
import com.wizardlybump17.wlib.command.args.ArgsReaderRegistry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaperWLibSupport {

    public static void start() {
        ArgsReaderRegistry.INSTANCE.add(new EnchantmentReader());
    }

    public static void stop() {
        ArgsReaderRegistry.INSTANCE.remove(EnchantmentReader.class);
    }
}
