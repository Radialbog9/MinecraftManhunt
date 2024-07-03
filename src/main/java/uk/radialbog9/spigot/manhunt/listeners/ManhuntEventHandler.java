/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.game.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.Utils;

public class ManhuntEventHandler implements Listener {
    /**
     * Detects death for runners
     * @param e the event
     */
    @EventHandler
    public void runnerDeathEvent(PlayerDeathEvent e) {
        if (GameManager.getGame().isGameStarted()) {
            Player p = e.getEntity();
            //Is runner?
            if (GameManager.getGame().isRunner(p)) {
                //The death was a runner
                //Set gamemode to spectator
                p.setGameMode(GameMode.SPECTATOR);
                //Remove from runners
                GameManager.getGame().getRunners().remove(p);
                //Check if that was the last runner alive
                if (GameManager.getGame().getRunners().isEmpty()) {
                    GameManager.endGame(GameEndCause.RUNNERS_ALL_DIE);
                } else {
                    //Else say they died and how many runners remain.
                    Utils.broadcastServerMessage(LanguageTranslator.translate(
                            "runner-died",
                            p.getDisplayName(),
                            String.valueOf(GameManager.getGame().getRunners().size())
                    ));
                    //add them to die list so they can be revived
                    GameManager.getGame().getDeadRunners().add(p);
                }
            }
        }
    }

    /**
     * Detects when hunters respawn and gives them a compass
     * @param e the event
     */
    @EventHandler
    public void hunterRespawnEvent(PlayerRespawnEvent e) {
        if (GameManager.getGame().isGameStarted()) {
            Player p = e.getPlayer();
            if (GameManager.getGame().isHunter(p)) {
                p.getInventory().addItem(new ItemStack(Material.COMPASS));
            }
        }
    }

    /**
     * Detects compass right click for hunters.
     * @param e the event
     */
    @EventHandler
    public void compassRightClickEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS && GameManager.getGame().isGameStarted() && GameManager.getGame().isHunter(p)) {
            double closest = Double.MAX_VALUE;
            Player closestPlayer = null;
            for (Player i : GameManager.getGame().getRunners()){
                if (i.getUniqueId() != p.getUniqueId() && i.getWorld().getName().equals(p.getWorld().getName())) {
                    double dist = i.getLocation().distance(p.getLocation());
                    if ((closest == Double.MAX_VALUE || dist < closest)){
                        closestPlayer = i;
                        closest = dist;
                    }
                }
            }
            if (closestPlayer == null) {
                //No runners nearby in the same world
                p.sendMessage(LanguageTranslator.translate("no-players-to-track"));
            } else {
                //the closest runner has been found
                p.setCompassTarget(closestPlayer.getLocation());
                p.sendMessage(LanguageTranslator.translate(
                        "tracking-player",
                        closestPlayer.getDisplayName(),
                        String.valueOf(Math.floor(closestPlayer.getLocation().distance(p.getLocation())))
                ));
            }
        }
    }

    /**
     * Detects when player joins server while the server is running a manhunt game.
     * @param e the event
     */
    @EventHandler
    public void inGamePlayerJoinEvent(PlayerJoinEvent e) {
        if (GameManager.getGame().isGameStarted()) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(LanguageTranslator.translate("join-in-progress"));
        }
    }

    /**
     * Detects when hunter/runner leaves the server and sets them as a spectator
     * @param e the event
     */
    @EventHandler
    public void inGamePlayerLeaveEvent(PlayerQuitEvent e) {
        if (GameManager.getGame().isGameStarted()) {
            if (GameManager.getGame().isRunner(e.getPlayer())) {
                GameManager.getGame().getRunners().remove(e.getPlayer());
                GameManager.getGame().getDeadRunners().remove(e.getPlayer());
                Utils.broadcastServerMessage(LanguageTranslator.translate("runner-disconnected", e.getPlayer().getDisplayName()));
                if (GameManager.getGame().getRunners().isEmpty()) {
                    //If so broadcast event
                    GameManager.endGame(GameEndCause.ALL_RUNNERS_LEAVE);
                }
            }
            if (GameManager.getGame().isHunter(e.getPlayer())) {
                GameManager.getGame().getHunters().remove(e.getPlayer());
                Utils.broadcastServerMessage(LanguageTranslator.translate("hunter-disconnected", e.getPlayer().getDisplayName()));
                if(GameManager.getGame().getHunters().isEmpty()) {
                    //If so broadcast event
                    GameManager.endGame(GameEndCause.ALL_HUNTERS_LEAVE);
                }
            }
        }
    }

    /**
     * Detects ender dragon death.
     * @param e the event
     */
    @EventHandler
    public void enderDragonDeathEvent(EntityDeathEvent e) {
        if (GameManager.getGame().isGameStarted()) {
            //game is running, check for ender dragon death
            if(e.getEntityType() == EntityType.ENDER_DRAGON) {
                //If so broadcast event
                GameManager.endGame(GameEndCause.RUNNERS_KILL_DRAGON);
            }
        }
    }

    /**
     * Detects player joining when game is not started
     * @param e the event
     */
    @EventHandler
    public void noGamePlayerJoinEvent(PlayerJoinEvent e) {
        if(!GameManager.getGame().isGameStarted() && Manhunt.getInstance().getConfig().getBoolean("join-message.enabled")) {
            if(e.getPlayer().hasPermission("manhunt.admin")) {
               e.getPlayer().sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("join-message.perm")));
            } else {
                e.getPlayer().sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("join-message.noperm")));
            }
        }
    }

    /**
     * Stops the hunters from being able to damage the Ender Dragon
     * @param e the event
     */
    @EventHandler
    public void hunterDragonDamageEvent(EntityDamageByEntityEvent e) {
        if(GameManager.getGame().isGameStarted() && !ManhuntSettings.isAllowHunterDamageDragon()) {
            if(e.getEntityType() == EntityType.ENDER_DRAGON && e.getDamager().getType() == EntityType.PLAYER) {
                if(GameManager.getGame().isHunter(((Player) e.getDamager()))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    /**
     * Stops the hunters from being able to damage the End Crystals
     * @param e the event
     */
    @EventHandler
    public void hunterCrystalDamageEvent(EntityDamageByEntityEvent e) {
        if(GameManager.getGame().isGameStarted() && !ManhuntSettings.isAllowHunterDamageCrystal()) {
            if(e.getEntityType() == EntityType.ENDER_CRYSTAL && e.getDamager().getType() == EntityType.PLAYER) {
                if(GameManager.getGame().isHunter(((Player) e.getDamager()))) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
