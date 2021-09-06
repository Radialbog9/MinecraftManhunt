/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameStartEvent;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

/**
 * The main class that starts the game when the start event is broadcast.
 */
public class ManhuntStartEventListener implements Listener {
    @EventHandler
    public void gameStartEvent(ManhuntGameStartEvent e) {
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
                    if(ManhuntSettings.getHeadStartEnabled()) {
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
        //set game as started
        ManhuntVars.setGameStarted(true);
        //new RandDisgScenario().runTaskTimer(Manhunt.getInstance(), 600, 6000);
        Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.game-started"));
    }
}
