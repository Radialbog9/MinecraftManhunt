/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import lombok.Getter;
import lombok.Setter;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;

import java.util.ArrayList;

/**
 * Basic class to store settings for Manhunt
 */
public class ManhuntSettings {
    @Getter
    @Setter
    private static boolean headStartEnabled = true;
    @Getter
    @Setter
    private static int headStartTime = 20;

    @Getter
    @Setter
    private static boolean dreamModeEnabled = false;

    public static void save() {
        Manhunt.getInstance().getConfig().set("head-start.enabled", headStartEnabled);
        Manhunt.getInstance().getConfig().set("head-start.length", headStartTime);
        Manhunt.getInstance().saveConfig();
    }

    public static void loadFromCfg() {
        setHeadStartEnabled(Manhunt.getInstance().getConfig().getBoolean("head-start.enabled"));
        setHeadStartTime(Manhunt.getInstance().getConfig().getInt("head-start.length"));
    }
}
