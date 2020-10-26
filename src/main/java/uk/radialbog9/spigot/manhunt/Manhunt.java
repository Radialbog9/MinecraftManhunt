package uk.radialbog9.spigot.manhunt;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class Manhunt extends JavaPlugin {
    /**
     * Called when plugin is enabled
     */
    @Override
    public void onEnable() {
        //Log message to console
        getLogger().log(Level.INFO, ChatColors.getMsgColor("&aManhunt has been enabled!"));
    }

    /**
     * Called when plugin is disabled
     */
    @Override
    public void onDisable() {
        //Log message to console
        getLogger().log(Level.INFO, ChatColors.getMsgColor("&aManhunt has been disabled!"));
    }
}
