/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import uk.radialbog9.spigot.manhunt.Manhunt;

/**
 * Basic class to store settings for Manhunt
 * Settings are things that can be changed both in-game (the settings menu) and in the configuration
 */
@SuppressWarnings({"unused"})
public class ManhuntSettings {
    @Getter
    @Setter
    private static boolean headStartEnabled = true;
    @Getter
    @Setter
    private static int headStartTime = 20;

    @Getter
    @Setter
    private static boolean allowHunterDamageDragon = false;

    @Getter
    @Setter
    private static boolean allowHunterDamageCrystal = true;

    @Getter
    @Setter
    private static int surviveGameLength = 10 * 60;

    public static void save() {
        FileConfiguration config = Manhunt.getInstance().getConfig();
        config.set("head-start.enabled", headStartEnabled);
        config.set("head-start.length", headStartTime);
        config.set("allow-hunters-damage-dragon", allowHunterDamageDragon);
        config.set("allow-hunters-damage-crystal", allowHunterDamageCrystal);
        config.set("survive-game-length", surviveGameLength);
        Manhunt.getInstance().saveConfig();
    }

    public static void loadFromCfg() {
        FileConfiguration config = Manhunt.getInstance().getConfig();
        setHeadStartEnabled(config.getBoolean("head-start.enabled"));
        setHeadStartTime(config.getInt("head-start.length"));
        setAllowHunterDamageDragon(config.getBoolean("allow-hunters-damage-dragon"));
        setAllowHunterDamageCrystal(config.getBoolean("allow-hunters-damage-crystal"));
        setSurviveGameLength(config.getInt("survive-game-length"));
    }
}
