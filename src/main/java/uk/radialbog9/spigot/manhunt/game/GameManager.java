/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameStartEvent;
import uk.radialbog9.spigot.manhunt.scenario.*;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.logging.Level;

public class GameManager {
    public static void startGame() {
        ManhuntGameStartEvent event = new ManhuntGameStartEvent();
        Bukkit.getServer().getPluginManager().callEvent(event);

        if(event.isCancelled()) return; //if event is cancelled by another plugin do not proceed

        ManhuntVars.getPreviousRunners().clear();

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) {
                //HUNTERS AND RUNNERS
                //set gamemode to survival
                p.setGameMode(GameMode.SURVIVAL);
                //clear inventory
                p.getInventory().clear();
                //set health, hunger and XP
                p.setHealth(20);
                p.setLevel(0);
                p.setExp(0);
                p.setFoodLevel(20);
                //TP to spawn
                p.teleport(p.getWorld().getSpawnLocation());
                if(ManhuntVars.isHunter(p)) {
                    //give blindness and weakness for 5 seconds
                    if(ManhuntSettings.isHeadStartEnabled()) {
                        new PotionEffect(PotionEffectType.WEAKNESS, ManhuntSettings.getHeadStartTime() * 20, 10, false, false).apply(p);
                        new PotionEffect(PotionEffectType.BLINDNESS, ManhuntSettings.getHeadStartTime() * 20, 10, false, false).apply(p);
                    }
                    //give player compass
                    p.getInventory().addItem(new ItemStack(Material.COMPASS));
                }
            } else {
                //SPECTATORS
                //Set spectator gamemode
                p.setGameMode(GameMode.SPECTATOR);
            }
        }
        //Scenarios
        ArrayList<ScenarioType> scenarios = ManhuntVars.getScenarioList();
        for (ScenarioType scenarioType : scenarios) {
            ScenarioBase scenario = Manhunt.getScenarioLoader().getAvailableScenarios().get(scenarioType);
            if (scenario.getClass().getAnnotation(ScenarioListener.class) != null) {
                //Is a listener
                try {
                    Manhunt.getInstance().getServer().getPluginManager().registerEvents((ListenerScenario) scenario, Manhunt.getInstance());
                } catch (ClassCastException e) {
                    Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't start scenario " + scenario.getClass().getSimpleName() + " because the listener wouldn't load!");
                    e.printStackTrace();
                }
            }
            else if (scenario.getClass().getAnnotation(ScenarioRunnable.class) != null) {
                //Is a runnable
                try {
                    ((RunnableScenario) scenario).runTaskTimer(Manhunt.getInstance(),
                            Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioType + ".time", 300) * 20L,
                            Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioType + ".time", 300) * 20L
                    );
                } catch (ClassCastException e) {
                    Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't start scenario " + scenario.getClass().getSimpleName() + " because the runnable wouldn't load!");
                    e.printStackTrace();
                }
            }
        }

        //set game as started
        ManhuntVars.setGameStarted(true);

        Utils.broadcastServerMessage(LanguageTranslator.translate("game-started"));
    }

    public static void endGame(GameEndCause e) {
        ManhuntGameEndEvent event = new ManhuntGameEndEvent(e);
        Bukkit.getServer().getPluginManager().callEvent(event);

        //check win causes, modify player data, and then end the game
        if (e == GameEndCause.ALL_RUNNERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("no-more-runners-left"));
            for(Player p : ManhuntVars.getHunters()) {
                ManhuntVars.getPlayerConfig(p).addHunterWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if (e == GameEndCause.RUNNERS_ALL_DIE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("all-runners-dead"));
            for(Player p : ManhuntVars.getHunters()) {
                ManhuntVars.getPlayerConfig(p).addHunterWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if (e == GameEndCause.ALL_HUNTERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("no-more-hunters-left"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if (e == GameEndCause.RUNNERS_KILL_DRAGON) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("runners-kill-ender-dragon"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if (e == GameEndCause.TIME_UP) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("time_up"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if (e == GameEndCause.ENDED_PREMATURELY) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("game-ended-prematurely"));
        }

        //set all players to spectator
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }

        //reset runners and hunters
        ManhuntVars.removeAllHunters();
        ManhuntVars.removeAllRunners();

        ManhuntVars.getPreviousRunners().clear();

        //fully end game
        ManhuntVars.setGameStarted(false);

        Utils.broadcastServerMessage(LanguageTranslator.translate("game-ended"));
    }

}