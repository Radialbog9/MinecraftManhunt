package uk.radialbog9.spigot.manhunt.settings;

import uk.radialbog9.spigot.manhunt.Manhunt;

/**
 * Basic class to store settings for Manhunt
 */
public class ManhuntSettings {
    private static boolean headStartEnabled = true;
    private static int headStartTime = 20;

    public static void save() {
        Manhunt.getInstance().getConfig().set("head-start.enabled", headStartEnabled);
        Manhunt.getInstance().getConfig().set("head-start.length", headStartTime);
        Manhunt.getInstance().saveConfig();
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
}
