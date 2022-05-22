/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt;

import de.jeff_media.updatechecker.UpdateChecker;
import de.jeff_media.updatechecker.UserAgentBuilder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.radialbog9.spigot.manhunt.commands.ManhuntCommand;
import uk.radialbog9.spigot.manhunt.commands.SpectateCommand;
import uk.radialbog9.spigot.manhunt.listeners.ManhuntEventHandler;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioLoader;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.tabcompleters.ManhuntTabCompleter;
import uk.radialbog9.spigot.manhunt.tabcompleters.SpectateTabCompleter;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Main Manhunt plugin class
 */

@SuppressWarnings({"ConstantConditions", "unused"})

public class Manhunt extends JavaPlugin {
    @Getter
    private static Manhunt instance;

    @Accessors(fluent = true)
    @Getter
    private static boolean areScenariosLoaded;

    @Getter
    private static ScenarioLoader scenarioLoader;

    //@Getter
    //private static KitProvider kitProvider;

    @Getter
    private static Properties language;

    private static final int SPIGOT_RESOURCE_ID = 97765;
    private static final int BSTATS_ID = 9573;

    /**
     * Called when plugin is enabled
     */
    @Override
    public void onEnable() {
        // Set instance
        instance = this;
        // Enable config
        if(!new File(getDataFolder(), "config.yml").exists()) saveResource("config.yml", false);
        reloadConfig();
        // Load language
        try {
            language = new Properties();
            String languageSpecified = getConfig().getString("language");
            if (languageSpecified.equals("custom")) {
                File customFile = new File(getDataFolder(), "language.properties");
                if(!customFile.exists()) saveResource("language.properties", false);
                language.load(new FileReader(customFile));
            } else if (languageSpecified.equals("none")) {
                InputStream languageStream = getResource("language.properties");
                language.load(languageStream);
            } else {
                InputStream languageStream = getResource("language-" + languageSpecified + ".properties");
                if(languageStream == null) languageStream = getResource("language.properties");
                language.load(languageStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Register event listeners
        getServer().getPluginManager().registerEvents(new ManhuntEventHandler(), this);

        // Register commands
        this.getCommand("manhunt").setExecutor(new ManhuntCommand());
        this.getCommand("spectate").setExecutor(new SpectateCommand());

        // Register tab completer
        this.getCommand("manhunt").setTabCompleter(new ManhuntTabCompleter());
        this.getCommand("spectate").setTabCompleter(new SpectateTabCompleter());

        // Register bStats
        Metrics metrics = new Metrics(this, BSTATS_ID);

        // SV/PV check
        ManhuntVars.setVanishEnabled(
                getServer().getPluginManager().isPluginEnabled("SuperVanish") || getServer().getPluginManager().isPluginEnabled("PremiumVanish")
        );
        // LibsDisguises check
        ManhuntVars.setLibsDisguisesEnabled(
                getServer().getPluginManager().isPluginEnabled("LibsDisguises")
        );

        //Load settings
        ManhuntSettings.loadFromCfg();

        // Load scenarios
        try {
            scenarioLoader = new ScenarioLoader();
            areScenariosLoaded = true;
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Could not load scenarios! Scenarios will be disabled. Error:");
            e.printStackTrace();
            areScenariosLoaded = false;
        }

        // Update Check
        UpdateChecker.init(this, SPIGOT_RESOURCE_ID)
                .setDonationLink("https://buymeacoff.ee/Radialbog9")
                .setChangelogLink(SPIGOT_RESOURCE_ID)
                .setNotifyOpsOnJoin(true)
                .setNotifyByPermissionOnJoin("manhunt.updates")
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion())
                .checkEveryXHours(6)
                .checkNow();
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
}
