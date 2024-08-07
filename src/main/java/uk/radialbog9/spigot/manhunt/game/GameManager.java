/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameStartEvent;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.playerdata.DataUtils;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioListener;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.scenario.config.RunnableRequiredConfig;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.utils.CompassTrackable;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;
import uk.radialbog9.spigot.manhunt.utils.LibsDisguisesUtils;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;

public class GameManager {
    private enum WinType {
        RUNNERS,
        HUNTERS
    }

    @Getter
    private static Game game = new Game();

    private static final ArrayList<BukkitRunnable> enabledRunnables = new ArrayList<>();

    private static BukkitRunnable gameTimerRunnable = new GameTimerRunnable();

    public static void startGame() {
        ManhuntGameStartEvent event = new ManhuntGameStartEvent();
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled()) return; // if event is cancelled by another plugin do not proceed

        // Set settings
        game.setGameObjective(ManhuntSettings.getObjective());

        // Get the world spawn location
        Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (game.isRunner(p) || game.isHunter(p)) {
                // HUNTERS AND RUNNERS
                // set gamemode to survival
                p.setGameMode(GameMode.SURVIVAL);
                // clear inventory
                p.getInventory().clear();
                // set health, hunger and XP
                AttributeInstance maxHealthAttribute = p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH);
                double maxHealth = maxHealthAttribute != null ? maxHealthAttribute.getValue() : 20;
                p.setHealth(maxHealth);
                p.setLevel(0);
                p.setExp(0);
                p.setFoodLevel(20);
                // Clear potion effects
                p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
                // TP to spawn
                p.teleport(spawn);
                if(game.isHunter(p)) {
                    //give blindness and weakness for head start time
                    if(ManhuntSettings.isHeadStartEnabled()) {
                        new PotionEffect(PotionEffectType.WEAKNESS, ManhuntSettings.getHeadStartTime() * 20, 10, false, false).apply(p);
                        new PotionEffect(PotionEffectType.BLINDNESS, ManhuntSettings.getHeadStartTime() * 20, 10, false, false).apply(p);
                    }
                    // give player compass
                    p.getInventory().addItem(new ItemStack(Material.COMPASS));
                }
            } else {
                // SPECTATORS
                // Set spectator gamemode
                p.setGameMode(GameMode.SPECTATOR);
            }
        }

        // Scenarios
        ArrayList<String> scenarios = game.getActiveScenarios();
        for (String scenarioType : scenarios) {
            try {
                Class<?> scenario = Manhunt.getScenarioLoader().getAvailableScenarios().get(scenarioType);
                // Create new instance of scenario (loading cfg if it exists)
                Object scenarioInstance = ScenarioUtils.getScenarioInstance(scenarioType, scenario);

                if (scenario.getAnnotation(ScenarioListener.class) != null) {
                    // Is a listener
                    Listener scenariolistener = (Listener) scenarioInstance; //create a new class instance as a listener
                    Manhunt.getInstance().getServer().getPluginManager().registerEvents(scenariolistener, Manhunt.getInstance());
                }
                if (scenario.getAnnotation(ScenarioRunnable.class) != null) {
                    // Is a runnable
                    BukkitRunnable runnable = (BukkitRunnable) scenarioInstance; //create a new BukkitRunnable object from the scenario's class

                    ScenarioConfigurable scen = (ScenarioConfigurable) scenarioInstance;
                    RunnableRequiredConfig config = (RunnableRequiredConfig) scen.getConfig();

                    runnable.runTaskTimer(Manhunt.getInstance(),
                            config.getTime() * 20L,
                            config.getTime() * 20L
                    );
                    enabledRunnables.add(runnable);
                }
            } catch (Exception e) {
                Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't start scenario " + scenarioType + " because the class wouldn't load!");
                Manhunt.getInstance().getLogger().log(Level.WARNING, e.getMessage());
            }
        }

        // Timer if game is set to timer mode
        if(game.getGameObjective() == Objective.SURVIVE) {
            LocalDateTime ldt = LocalDateTime.now();
            game.setGameEndTime(ldt.plusSeconds(ManhuntSettings.getSurviveGameLength()));
            gameTimerRunnable.runTaskTimer(Manhunt.getInstance(), 0, 10);
        }

        // Set game as started
        game.setGameStarted(true);

        Utils.broadcastServerMessage(LanguageTranslator.translate("game-started"));
    }

    public static void endGame(GameEndCause e) {
        ManhuntGameEndEvent event = new ManhuntGameEndEvent(e);
        Bukkit.getServer().getPluginManager().callEvent(event);

        WinType winType = null;

        // check win causes, modify player data, and then end the game
        if (e == GameEndCause.ALL_RUNNERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.no-more-runners-left"));
            winType = WinType.HUNTERS;
        }
        else if (e == GameEndCause.RUNNERS_ALL_DIE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.all-runners-dead"));
            winType = WinType.HUNTERS;
        }
        else if (e == GameEndCause.ALL_HUNTERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.no-more-hunters-left"));
            winType = WinType.RUNNERS;
        }
        else if (e == GameEndCause.RUNNERS_KILL_DRAGON) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.runners-kill-ender-dragon"));
            winType = WinType.RUNNERS;
        }
        else if (e == GameEndCause.TIME_UP) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.time-up"));
            winType = WinType.RUNNERS;
        }
        else if (e == GameEndCause.ENDED_PREMATURELY) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.game-ended-prematurely"));
        }

        if (winType == WinType.HUNTERS) {
            // Hunters win, add to stats
            for (Player p : game.getHunters()) {
                DataUtils.getPlayerData(p).addHunterWin();
                DataUtils.getPlayerData(p).save();
            }
            for (Player p : game.getRunners()) {
                DataUtils.getPlayerData(p).addRunnerDeath();
                DataUtils.getPlayerData(p).save();
            }
        } else if (winType == WinType.RUNNERS) {
            // Runners win, add to stats
            for (Player p : game.getRunners()) {
                DataUtils.getPlayerData(p).addRunnerWin();
                DataUtils.getPlayerData(p).save();
            }
            for (Player p : game.getHunters()) {
                DataUtils.getPlayerData(p).addHunterLoss();
                DataUtils.getPlayerData(p).save();
            }
        } // else nobody wins, no stats to add

        // set all players to spectator
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (game.isRunner(p) || game.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }

        // reset runners and hunters
        game.getHunters().clear();
        game.getRunners().clear();

        game.getDeadRunners().clear();

        // Clear compass hidden players
        CompassTrackable.getHiddenPlayers().clear();

        // Clear active scenarios
        game.getActiveScenarios().clear();

        // Cancel runnables
        for(BukkitRunnable runnable : enabledRunnables) {
            runnable.cancel();
        }
        enabledRunnables.clear();

        // Reset game timer
        gameTimerRunnable = new GameTimerRunnable();

        // Libsdiguises undisguise
        if (DependencySupport.isLibsDisguisesEnabled()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                // Undisguise player
                LibsDisguisesUtils.undisguisePlayer(p);
            }
        }

        // Broadcast game ended message
        Utils.broadcastServerMessage(LanguageTranslator.translate("game-ended"));

        //fully end game
        game.setGameStarted(false);

        //Reset game
        game = new Game();
    }

}
