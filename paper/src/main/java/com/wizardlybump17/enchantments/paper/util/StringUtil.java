package com.wizardlybump17.enchantments.paper.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public static @NonNull String word(@NonNull String string) {
        StringBuilder builder = new StringBuilder(string.length());
        boolean capitalize = true;

        for (char c : string.toLowerCase().toCharArray()) {
            if (c == '_') {
                builder.append(' ');
                capitalize = true;
                continue;
            }

            if (capitalize) {
                if (Character.isDigit(c))
                    builder.append(c);
                else
                    builder.append(Character.toUpperCase(c));
                capitalize = false;
                continue;
            }

            builder.append(Character.toLowerCase(c));
        }

        return builder.toString();
    }
}
