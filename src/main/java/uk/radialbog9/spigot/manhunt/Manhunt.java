/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt;

import com.google.common.base.Charsets;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import uk.radialbog9.spigot.manhunt.listeners.ManhuntEventHandler;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioLoader;
import uk.radialbog9.spigot.manhunt.tabcompleters.ManhuntTabCompleter;
import uk.radialbog9.spigot.manhunt.tabcompleters.SpectateTabCompleter;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.logging.Level;

/**
 * Main Manhunt plugin class
 */

@SuppressWarnings({"ConstantConditions", "unused"})

@Plugin(name = "Manhunt", version = "2.0.1")
@ApiVersion(ApiVersion.Target.v1_16)
@Author("Radialbog9")
@Description("Play Dream's iconic Manhunt game!")
@SoftDependsOn({
        @SoftDependency("SuperVanish"),
        @SoftDependency("PremiumVanish"),
        @SoftDependency("LibsDisguises"),
        @SoftDependency("ProtocolLib")
})

@Commands({
        @Command(name = "manhunt", desc = "Main manhunt command", usage = "/<command> [help|hunter <player>|runner <player>|remove <player>|revive [player]|start|stop|list]"),
        @Command(name = "spectate", desc = "Spectates a player while in a game", usage = "/<command> <player>")
})

@Permissions({
        @Permission(name = "manhunt.*", desc = "Allows player to use all manhunt commands", defaultValue = PermissionDefault.OP, children = {
                @ChildPermission(name = "manhunt.add"),
                @ChildPermission(name = "manhunt.remove"),
                @ChildPermission(name = "manhunt.start"),
                @ChildPermission(name = "manhunt.stop"),
                @ChildPermission(name = "manhunt.list"),
                @ChildPermission(name = "manhunt.spectate"),
                @ChildPermission(name = "manhunt.admin"),
                @ChildPermission(name = "manhunt.settings"),
                @ChildPermission(name = "manhunt.revive")
        }),
        @Permission(name = "manhunt.add", desc = "Allows player to add others to hunters/runners", defaultValue =  PermissionDefault.OP),
        @Permission(name = "manhunt.remove", desc = "Allows player to remove others from the game", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.start", desc = "Allows player to start the game", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.stop", desc = "Allows player to stop the game", defaultValue =  PermissionDefault.OP),
        @Permission(name = "manhunt.list", desc = "Allows player to list other players in the game", defaultValue = PermissionDefault.TRUE),
        @Permission(name = "manhunt.spectate", desc = "Allows player to spectate hunters/runner while in a game", defaultValue = PermissionDefault.TRUE),
        @Permission(name = "manhunt.admin", desc = "Sends join messages for admins on joining (if enabled)", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.settings", desc = "Allows player to use settings menu", defaultValue = PermissionDefault.OP),
        @Permission(name = "manhunt.revive", desc = "Allows player to ", defaultValue = PermissionDefault.OP)
})
public class Manhunt extends JavaPlugin {
    private static Manhunt instance;

    public static File langFile;
    public static FileConfiguration languageConfig;

    @Accessors(fluent = true)
    @Getter
    private static boolean areScenariosLoaded;

    @Getter
    private static ScenarioLoader scenarioLoader;

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
        // Load language
        loadLang();
        // Register event listeners
        getServer().getPluginManager().registerEvents(new ManhuntEventHandler(), this);
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
        //LibsDisguises check
        ManhuntVars.setLibsDisguisesEnabled(
                getServer().getPluginManager().isPluginEnabled("LibsDisguises")
        );
        //Load scenarios
        try {
            scenarioLoader = new ScenarioLoader();
            areScenariosLoaded = true;
        } catch (URISyntaxException e) {
            getLogger().log(Level.WARNING, "Could not load scenarios! Scenarios will be disabled. Error:");
            e.printStackTrace();
            areScenariosLoaded = false;
        }
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

    /**
     * Loads the language configuration fron lang.yml
     */
    public static void loadLang() {
        langFile = new File(Manhunt.getInstance().getDataFolder(), "lang.yml");
        if(!langFile.exists()) Manhunt.getInstance().saveResource("lang.yml", false);
        languageConfig = YamlConfiguration.loadConfiguration(langFile);
        try {
            languageConfig.save(langFile);
        } catch (IOException e) {
            Manhunt.getInstance().getLogger().log(Level.SEVERE, "Could not save language to " + langFile, e);
        }
        reloadLang();
    }

    /**
     * Loads the language configuration from lang.yml
     */
    public static void reloadLang() {
        languageConfig = YamlConfiguration.loadConfiguration(langFile);
        final InputStream defConfigStream = Manhunt.getInstance().getResource("lang.yml");
        if (defConfigStream == null) {
            return;
        }
        languageConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }

    public static FileConfiguration getLang() {
        if(languageConfig == null) {
            reloadLang();
        }
        return languageConfig;
    }
}
