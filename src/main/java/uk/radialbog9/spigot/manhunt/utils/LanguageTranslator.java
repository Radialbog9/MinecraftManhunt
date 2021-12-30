/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.utils;

import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;

/**
 * Provides functions to translate language keys to the language configuration.
 */
@SuppressWarnings({"NullArgumentToVariableArgMethod"})
public class LanguageTranslator {
    /**
     * Gets the specified key from the language configuration then replaces {0}, {1}, etc with the arguments.
     * @param key The language key
     * @param args The arguments
     * @return String of language key with replacements or language key if the language request produces null
     */
    public static String translate(@NotNull String key, String... args) {
        String out = "";
        out = Manhunt.getLanguage().getProperty(key, key); // get key or return the key if key doesn't exist in lang
        if(args != null) {
            for (int i = 0; i < args.length; i++) {
                out = out.replace("{" + i + "}", args[i]);
            }
        }
        return Utils.getMsgColor(out);
    }

    /**
     * Gets the specified key from the language configuration.
     * @param key The language key
     * @return String of language key or empty string if language key produces null
     */
    public static String translate(@NotNull String key) {
        return translate(key, null);
    }
}
