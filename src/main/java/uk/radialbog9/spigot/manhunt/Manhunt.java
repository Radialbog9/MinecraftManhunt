package uk.radialbog9.spigot.manhunt;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class Manhunt extends JavaPlugin {
    /**
     * Called when plugin is enabled
     */
    @Override
    public void onEnable() {
        //Register events
        getServer().getPluginManager().registerEvents(new ManhuntEventHandler(), this);
        //Register commands
        this.getCommand("manhunt").setExecutor(new ManhuntCommand());
        this.getCommand("spectate").setExecutor(new SpectateCommand());
        //Register tab completer
        this.getCommand("manhunt").setTabCompleter(new ManhuntTabCompleter());
        this.getCommand("spectate").setTabCompleter(new ManhuntTabCompleter());
        //register bstats
        int bstatsId = 9573;
        Metrics metrics = new Metrics(this, bstatsId);
        //Log message to console
        getLogger().log(Level.INFO, Utils.getMsgColor("&aManhunt has been enabled!"));
    }

    /**
     * Called when plugin is disabled
     */
    @Override
    public void onDisable() {
        //Log message to console
        getLogger().log(Level.INFO, Utils.getMsgColor("&aManhunt has been disabled!"));
    }
}
