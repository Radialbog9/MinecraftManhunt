/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;

public class GameManager {
    @Getter
    private static Game game = new Game();

    private static final ArrayList<BukkitRunnable> enabledRunnables = new ArrayList<>();

    private static BukkitRunnable gameTimerRunnable = new GameTimerRunnable();

    public static void startGame() {
        ManhuntGameStartEvent event = new ManhuntGameStartEvent();
        Bukkit.getServer().getPluginManager().callEvent(event);

        if(event.isCancelled()) return; //if event is cancelled by another plugin do not proceed

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(game.isRunner(p) || game.isHunter(p)) {
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
                if(game.isHunter(p)) {
                    //give blindness and weakness for head start time
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
        ArrayList<String> scenarios = game.getActiveScenarios();
        for (String scenarioType : scenarios) {
            Class<?> scenario = Manhunt.getScenarioLoader().getAvailableScenarios().get(scenarioType);
            if (scenario.getAnnotation(ScenarioListener.class) != null) {
                //Is a listener
                try {
                    Listener scenariolistener = (Listener) scenario.getConstructor().newInstance(); //create a new class instance as a listener
                    Manhunt.getInstance().getServer().getPluginManager().registerEvents(scenariolistener, Manhunt.getInstance());
                } catch (Exception e) {
                    Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't start scenario " + scenario.getSimpleName() + " because the listener wouldn't load!");
                    e.printStackTrace();
                }
            }
            else if (scenario.getAnnotation(ScenarioRunnable.class) != null) {
                //Is a runnable
                try {
                    BukkitRunnable runnable = (BukkitRunnable) scenario.getConstructor().newInstance(); //create a new BukkitRunnable object from the scenario's class
                    runnable.runTaskTimer(Manhunt.getInstance(),
                            Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioType + ".time", 300) * 20L,
                            Manhunt.getInstance().getConfig().getInt("scenarios." + scenarioType + ".time", 300) * 20L
                    );
                    enabledRunnables.add(runnable);
                } catch (Exception e) {
                    Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't start scenario " + scenario.getSimpleName() + " because the runnable wouldn't load!");
                    e.printStackTrace();
                }
            }
        }

        // Timer if game is set to timer mode
        if(game.getGameObjective() == Objective.SURVIVE) {
            LocalDateTime ldt = LocalDateTime.now();
            game.setGameEndTime(ldt.plusSeconds(ManhuntSettings.getSurviveGameLength()));
            gameTimerRunnable.runTaskTimer(Manhunt.getInstance(), 0, 10);
        }

        //set game as started
        game.setGameStarted(true);

        Utils.broadcastServerMessage(LanguageTranslator.translate("game-started"));
    }

    public static void endGame(GameEndCause e) {
        ManhuntGameEndEvent event = new ManhuntGameEndEvent(e);
        Bukkit.getServer().getPluginManager().callEvent(event);

        //check win causes, modify player data, and then end the game
        if (e == GameEndCause.ALL_RUNNERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.no-more-runners-left"));
            for(Player p : GameManager.getGame().getHunters()) {
                DataUtils.getPlayerData(p).addHunterWin();
                DataUtils.getPlayerData(p).save();
            }
        }
        else if (e == GameEndCause.RUNNERS_ALL_DIE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.all-runners-dead"));
            for(Player p : GameManager.getGame().getHunters()) {
                DataUtils.getPlayerData(p).addHunterWin();
                DataUtils.getPlayerData(p).save();
            }
        }
        else if (e == GameEndCause.ALL_HUNTERS_LEAVE) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.no-more-hunters-left"));
            for(Player p : GameManager.getGame().getRunners()) {
                DataUtils.getPlayerData(p).addRunnerWin();
                DataUtils.getPlayerData(p).save();
            }
        }
        else if (e == GameEndCause.RUNNERS_KILL_DRAGON) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.runners-kill-ender-dragon"));
            for(Player p : GameManager.getGame().getRunners()) {
                DataUtils.getPlayerData(p).addRunnerWin();
                DataUtils.getPlayerData(p).save();
            }
        }
        else if (e == GameEndCause.TIME_UP) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.time-up"));
            for(Player p : GameManager.getGame().getRunners()) {
                DataUtils.getPlayerData(p).addRunnerWin();
                DataUtils.getPlayerData(p).save();
            }
        }
        else if (e == GameEndCause.ENDED_PREMATURELY) {
            Utils.broadcastServerMessage(LanguageTranslator.translate("endcause.game-ended-prematurely"));
        }

        //set all players to spectator
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (game.isRunner(p) || game.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }

        //reset runners and hunters
        game.getHunters().clear();
        game.getRunners().clear();

        game.getDeadRunners().clear();

        //Clear active scenarios
        game.getActiveScenarios().clear();

        //Cancel runnables
        for(BukkitRunnable runnable : enabledRunnables) {
            runnable.cancel();
        }
        enabledRunnables.clear();

        if(game.getGameObjective() == Objective.SURVIVE) {
            gameTimerRunnable.cancel();
        }

        Utils.broadcastServerMessage(LanguageTranslator.translate("game-ended"));

        //fully end game
        game.setGameStarted(false);

        //Reset game
        game = new Game();
    }

}
