/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt;

import cloud.commandframework.CommandManager;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.standard.IntegerArgument;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.bukkit.parsers.PlayerArgument;
import cloud.commandframework.exceptions.ArgumentParseException;
import cloud.commandframework.exceptions.NoPermissionException;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import de.jeff_media.updatechecker.UpdateChecker;
import de.jeff_media.updatechecker.UserAgentBuilder;
import io.leangen.geantyref.TypeToken;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.radialbog9.spigot.manhunt.cmderrorhandlers.InvalidArgsHandler;
import uk.radialbog9.spigot.manhunt.cmderrorhandlers.InvalidIntegerHandler;
import uk.radialbog9.spigot.manhunt.cmderrorhandlers.InvalidPlayerHandler;
import uk.radialbog9.spigot.manhunt.cmderrorhandlers.NoPermissionHandler;
import uk.radialbog9.spigot.manhunt.commands.LeaderboardCommand;
import uk.radialbog9.spigot.manhunt.commands.ManhuntCommand;
import uk.radialbog9.spigot.manhunt.commands.SpectateCommand;
import uk.radialbog9.spigot.manhunt.game.Objective;
import uk.radialbog9.spigot.manhunt.language.LanguageManager;
import uk.radialbog9.spigot.manhunt.listeners.ManhuntEventHandler;
import uk.radialbog9.spigot.manhunt.playerdata.DataUtils;
import uk.radialbog9.spigot.manhunt.playerdata.Leaderboard;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioLoader;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioSuggestions;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.tabcompleters.ManhuntSuggestions;
import uk.radialbog9.spigot.manhunt.tabcompleters.ObjectiveParser;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.io.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * Main Manhunt plugin class
 */

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})

public class Manhunt extends JavaPlugin {
    @Getter
    private static Manhunt instance;

    @Accessors(fluent = true)
    @Getter
    private static boolean areScenariosLoaded;

    @Getter
    private static ScenarioLoader scenarioLoader;

    @Getter
    private static LanguageManager language = new LanguageManager();

    @Getter
    private CommandManager<CommandSender> commandManager;

    @Getter
    private AnnotationParser<CommandSender> annotationParser;

    private static final int SPIGOT_RESOURCE_ID = 97765;
    private static final int BSTATS_ID = 9573;

    @Getter
    private static final Leaderboard leaderboard = new Leaderboard();

    /**
     * Called when plugin is enabled
     */
    @Override
    public void onEnable() {
        // Set instance
        instance = this;

        // Enable config
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        if(!new File(getDataFolder(), "config.yml").exists()) saveResource("config.yml", false);
        reloadConfig();

        // Load language
        loadLanguage();

        // Register event listeners
        getServer().getPluginManager().registerEvents(new ManhuntEventHandler(), this);

        // Register Cloud command handler
        try {
            commandManager = new BukkitCommandManager<>(
                    this,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, e.getMessage());
        }

        annotationParser = new AnnotationParser<>(commandManager, CommandSender.class, parameters -> SimpleCommandMeta.empty());

        // Register parsers and suggestions
        annotationParser.parse(new ScenarioSuggestions());
        annotationParser.parse(new ManhuntSuggestions());
        commandManager.parserRegistry().registerParserSupplier(
                TypeToken.get(Objective.class),
                options -> new ObjectiveParser<>()
        );

        // Register exception handlers
        commandManager.registerExceptionHandler(NoPermissionException.class, new NoPermissionHandler());
        commandManager.registerExceptionHandler(ArgumentParseException.class, new InvalidArgsHandler());
        commandManager.registerExceptionHandler(PlayerArgument.PlayerParseException.class, new InvalidPlayerHandler());
        commandManager.registerExceptionHandler(IntegerArgument.IntegerParseException.class, new InvalidIntegerHandler());

        // Register commands
        annotationParser.parse(new ManhuntCommand());
        annotationParser.parse(new SpectateCommand());
        annotationParser.parse(new LeaderboardCommand());

        // Register bStats
        Metrics metrics = new Metrics(this, BSTATS_ID);

        // SV/PV check
        DependencySupport.setVanishEnabled(
                getServer().getPluginManager().isPluginEnabled("SuperVanish") || getServer().getPluginManager().isPluginEnabled("PremiumVanish")
        );
        // LibsDisguises check
        DependencySupport.setLibsDisguisesEnabled(
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
            DataUtils.getPlayerData(p).save();
        }
        //Log message to console
        getLogger().log(Level.INFO, Utils.getMsgColor("Manhunt has been disabled!"));
    }

    public void loadLanguage() {
        try {
            // Load default language.properties
            Properties defaultLanguage = new Properties();
            InputStream dLanguageStream = getResource("language.properties");
            defaultLanguage.load(new InputStreamReader(dLanguageStream));
            language.loadLanguage(defaultLanguage); // Load default language to language manager

            // Load custom language.properties
            Properties customLang = new Properties();
            Reader langReader = null;

            String languageSpecified = getConfig().getString("language");

            if (languageSpecified.equals("custom")) {
                // Load custom language file
                File customFile = new File(getDataFolder(), "language.properties");
                if(!customFile.exists()) saveResource("language.properties", false);
                langReader = new FileReader(customFile);
            } else if (!languageSpecified.equals("none")) {
                // Load the language specified
                InputStream languageStream = getResource("language-" + languageSpecified + ".properties");
                if(languageStream == null) languageStream = getResource("language.properties");
                langReader = new InputStreamReader(languageStream);
            }

            if(langReader != null) {
                customLang.load(langReader);
                language.loadLanguage(customLang);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
