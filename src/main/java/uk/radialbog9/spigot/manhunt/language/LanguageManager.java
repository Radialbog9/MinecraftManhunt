package uk.radialbog9.spigot.manhunt.language;

import java.util.HashMap;
import java.util.Properties;

public class LanguageManager {

    private final HashMap<String, String> language = new HashMap<>();

    /**
     * Gets the property from the language map.
     * If the property does not exist, the default value is returned.
     * @param key The key to get
     * @param defaultValue The default value to return if the key does not exist
     * @return The value of the key or the default value if the key does not exist
     */
    public String getProperty(String key, String defaultValue) {
        return language.getOrDefault(key, defaultValue);
    }

    /**
     * Gets the property from the language map.
     * @param key The key to get
     * @return The value of the key or null if the key does not exist
     */
    public String getProperty(String key) {
        return language.get(key);
    }

    /**
     * Loads the language properties into the language map.
     * This will replace any existing properties.
     * @param languageProperties The language properties to load
     */
    public void loadLanguage(Properties languageProperties) {
        languageProperties.forEach((key, value) ->
                language.put((String) key, (String) value)
        );
    }

    public void loadLanguage(HashMap<String, String> languageProperties) {
        language.putAll(languageProperties);
    }

    /**
     * Sets a property in the language map.
     * This will replace any existing property with the same key.
     * @param key the key to set
     * @param value the value to set
     */
    public void setProperty(String key, String value) {
        language.put(key, value);
    }
}
