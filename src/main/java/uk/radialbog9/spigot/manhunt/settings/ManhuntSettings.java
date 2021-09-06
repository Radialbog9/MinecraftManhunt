/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import uk.radialbog9.spigot.manhunt.Manhunt;

/**
 * Basic class to store settings for Manhunt
 */
public class ManhuntSettings {
    private static boolean headStartEnabled = true;
    private static int headStartTime = 20;

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

    public static boolean getHeadStartEnabled() {
        return ManhuntSettings.headStartEnabled;
    }
    public static void setHeadStartEnabled(boolean headStartEnabled) {
        ManhuntSettings.headStartEnabled = headStartEnabled;
    }

    public static int getHeadStartTime() {
        return ManhuntSettings.headStartTime;
    }
    public static void setHeadStartTime(int headStartTime) {
        ManhuntSettings.headStartTime = headStartTime;
    }


    public static boolean isDreamModeEnabled() {
        return dreamModeEnabled;
    }

    public static void setDreamModeEnabled(boolean dreamModeEnabled) {
        ManhuntSettings.dreamModeEnabled = dreamModeEnabled;
    }
}
