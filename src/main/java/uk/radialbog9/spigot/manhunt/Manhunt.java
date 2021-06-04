/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPLv3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.dependency.SoftDependency;
import org.bukkit.plugin.java.annotation.dependency.SoftDependsOn;
import org.bukkit.plugin.java.annotation.permission.ChildPermission;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.permission.Permissions;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import uk.radialbog9.spigot.manhunt.commands.ManhuntCommand;
import uk.radialbog9.spigot.manhunt.commands.SpectateCommand;
import uk.radialbog9.spigot.manhunt.listeners.ManhuntEndEventListener;
import uk.radialbog9.spigot.manhunt.listeners.ManhuntEventHandler;
import uk.radialbog9.spigot.manhunt.tabcompleters.ManhuntTabCompleter;
import uk.radialbog9.spigot.manhunt.tabcompleters.SpectateTabCompleter;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.logging.Level;

/**
 * Main Manhunt plugin class
 */

@SuppressWarnings("ConstantConditions")

@Plugin(name = "Manhunt", version = "2.0.1")
@ApiVersion(ApiVersion.Target.DEFAULT)
@Author("Radialbog9")
@Description("Play Dream's iconic Manhunt game!")
@SoftDependsOn({
        @SoftDependency("SuperVanish"),
        @SoftDependency("PremiumVanish")
})

@Commands({
        @Command(name = "manhunt", desc = "Main manhunt command", usage = "/<command> [help|hunter <player>|runner <player>|remove <player>|start|stop|list]"),
        @Command(name = "spectate", desc = "Spectates a player while in a game", usage = "/<command> <player>")
})

@Permissions({
        @Permission(name = "manhunt.*", desc = "Allows player to use all manhunt commands", defaultValue = PermissionDefault.OP, children = {
                @ChildPermission(name = "manhunt.add"),
                @ChildPermission(name = "manhunt.remove"),
                @ChildPermission(name = "manhunt.start"),
                @ChildPermission(name = "manhunt.stop"),
                @ChildPermission(name = "manhunt.list"),
                @ChildPermission(name = "manhunt.spectate")
        }),
        @Permission(name = "manhunt.add", desc = "Allows player to add others to hunters/runners", defaultValue =  PermissionDefault.OP),
        @Permission(name = "manhunt.remove", desc = "Allows player to remove others from the game", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.start", desc = "Allows player to start the game", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.stop", desc = "Allows player to stop the game", defaultValue =  PermissionDefault.OP),
        @Permission(name = "manhunt.list", desc = "Allows player to list other players in the game", defaultValue = PermissionDefault.TRUE),
        @Permission(name = "manhunt.spectate", desc = "Allows player to spectate hunters/runner while in a game", defaultValue = PermissionDefault.TRUE)
})
public class Manhunt extends JavaPlugin {
    private static Manhunt instance;

    /**
     * Called when plugin is enabled
     */
    @Override
    public void onEnable() {
        // Set instance
        setInstance(this);
        // Enable config
        saveDefaultConfig();
        saveConfig();
        reloadConfig();
        // Register events
        getServer().getPluginManager().registerEvents(new ManhuntEventHandler(), this);
        getServer().getPluginManager().registerEvents(new ManhuntEndEventListener(), this);
        // Register commands
        this.getCommand("manhunt").setExecutor(new ManhuntCommand());
        this.getCommand("spectate").setExecutor(new SpectateCommand());
        // Register tab completer
        this.getCommand("manhunt").setTabCompleter(new ManhuntTabCompleter());
        this.getCommand("spectate").setTabCompleter(new SpectateTabCompleter());
        // Register bStats
        int bStatsId = 9573;
        Metrics metrics = new Metrics(this, bStatsId);
        // SV/PV check
        ManhuntVars.setVanishEnabled(
                getServer().getPluginManager().isPluginEnabled("SuperVanish") || getServer().getPluginManager().isPluginEnabled("PremiumVanish")
        );
        // Log start message to console
        getLogger().log(Level.INFO, Utils.getMsgColor("Manhunt has been enabled!"));
    }

    /**
     * Called when plugin is disabled
     */
    @Override
    public void onDisable() {
        // Save config
        saveConfig();
        // Save player data
        for(Player p : Bukkit.getOnlinePlayers()) {
            ManhuntVars.getPlayerConfig(p).save();
        }
        //Log message to console
        getLogger().log(Level.INFO, Utils.getMsgColor("Manhunt has been disabled!"));
    }

    /**
     * Gets the instance of the plugin
     * @return Manhunt instance
     */
    public static Manhunt getInstance() {
        return instance;
    }

    /**
     * Sets the instance of the plugin
     * @param instance the instance
     */
    public static void setInstance(Manhunt instance) {
        Manhunt.instance = instance;
    }

}
