/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import lombok.Getter;
import lombok.Setter;
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
        Manhunt.ManhuntConfiguration config = Manhunt.getInstance().getManhuntConfiguration();
        config.headStart = new Manhunt.ManhuntConfiguration.HeadStart(headStartEnabled, headStartTime);
        config.allowHuntersDamageEnderDragon = allowHunterDamageDragon;
        config.allowHuntersDamageEndCrystal =  allowHunterDamageCrystal;
        config.surviveGameLength = surviveGameLength;

    }

    public static void loadFromCfg() {
        Manhunt.ManhuntConfiguration config = Manhunt.getInstance().getManhuntConfiguration();
        setHeadStartEnabled(config.headStart.enabled());
        setHeadStartTime(config.headStart.length());
        setAllowHunterDamageDragon(config.allowHuntersDamageEnderDragon);
        setAllowHunterDamageCrystal(config.allowHuntersDamageEndCrystal);
        setSurviveGameLength(config.surviveGameLength);
    }
}
