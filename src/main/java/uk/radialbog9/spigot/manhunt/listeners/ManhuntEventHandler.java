/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@SuppressWarnings("ConstantConditions")
public class ManhuntEventHandler implements Listener {
    /**
     * Detects death for runners
     * @param e the event
     */
    @EventHandler
    public void runnerDeathEvent(PlayerDeathEvent e) {
        if (ManhuntVars.isGameStarted()) {
            Player p = e.getEntity();
            //Is runner?
            if (ManhuntVars.isRunner(p)) {
                //The death was a runner
                //Set gamemode to spectator
                p.setGameMode(GameMode.SPECTATOR);
                //Remove from runners
                ManhuntVars.removeRunner(p);
                //Check if that was the last runner alive
                if (ManhuntVars.getRunners().isEmpty()) {
                    ManhuntGameEndEvent event = new ManhuntGameEndEvent(GameEndCause.RUNNERS_ALL_DIE);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                } else {
                    //Else say they died and how many runners remain.
                    Utils.broadcastServerMessage(String.format(Manhunt.getInstance().getConfig().getString("language.runner-died"), p.getDisplayName(), ManhuntVars.getRunners().size()));
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
        if (ManhuntVars.isGameStarted()) {
            Player p = e.getPlayer();
            if (ManhuntVars.isHunter(p)) {
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
        if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS && ManhuntVars.isGameStarted() && ManhuntVars.isHunter(p)) {
            double closest = Double.MAX_VALUE;
            Player closestPlayer = null;
            for (Player i : ManhuntVars.getRunners()){
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
                p.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-players-to-track")));
            } else {
                //the closest runner has been found
                p.setCompassTarget(closestPlayer.getLocation());
                p.sendMessage(Utils.getMsgColor(
                        String.format(Manhunt.getInstance().getConfig().getString("language.tracking-player"), closestPlayer.getDisplayName())
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
        if (ManhuntVars.isGameStarted()) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.join-in-progress")));
        }
    }

    /**
     * Detects when hunter/runner leaves the server and sets them as a spectator
     * @param e the event
     */
    @EventHandler
    public void inGamePlayerLeaveEvent(PlayerQuitEvent e) {
        if (ManhuntVars.isGameStarted()) {
            if (ManhuntVars.isRunner(e.getPlayer())) {
                ManhuntVars.removeRunner(e.getPlayer());
                Utils.broadcastServerMessage(String.format(Manhunt.getInstance().getConfig().getString("language.runner-disconnected"), e.getPlayer().getDisplayName()));
                if (ManhuntVars.getRunners().isEmpty()) {
                    //If so broadcast event
                    ManhuntGameEndEvent event = new ManhuntGameEndEvent(GameEndCause.ALL_RUNNERS_LEAVE);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                }
            }
            if (ManhuntVars.isHunter(e.getPlayer())) {
                ManhuntVars.removeHunter(e.getPlayer());
                Utils.broadcastServerMessage(String.format(Manhunt.getInstance().getConfig().getString("language.hunter-disconnected"), e.getPlayer().getDisplayName()));
                if(ManhuntVars.getHunters().isEmpty()) {
                    //If so broadcast event
                    ManhuntGameEndEvent event = new ManhuntGameEndEvent(GameEndCause.ALL_HUNTERS_LEAVE);
                    Bukkit.getServer().getPluginManager().callEvent(event);
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
        if (ManhuntVars.isGameStarted()) {
            //game is running, check for ender dragon death
            if(e.getEntityType() == EntityType.ENDER_DRAGON) {
                //If so broadcast event
                ManhuntGameEndEvent event = new ManhuntGameEndEvent(GameEndCause.RUNNERS_KILL_DRAGON);
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }
    }

    /**
     * Allows use of "dream mode" to increase drop rates
     * @param e the event
     */
    @EventHandler
    public void dreamModeKillEvent(EntityDeathEvent e) {
        if(ManhuntSettings.isDreamModeEnabled()) {
            if(e.getEntityType() == EntityType.BLAZE && e.getEntity().getKiller() != null) {
                Player killer = e.getEntity().getKiller();
                if (killer.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
                    e.getDrops().clear();
                    e.getDrops().add(
                            new ItemStack(
                                    Material.BLAZE_ROD,
                                    Math.min(killer.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) + 1, 64)
                            )
                    );
                } else {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.BLAZE_ROD));
                }
                e.getEntity().getKiller().sendMessage("kill blaze");
            } else if (e.getEntityType() == EntityType.ENDERMAN && e.getEntity().getKiller() != null) {
                Player killer = e.getEntity().getKiller();
                if (killer.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
                    e.getDrops().clear();
                    e.getDrops().add(
                            new ItemStack(
                                    Material.ENDER_PEARL,
                                    Math.min(killer.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) + 1, 16)
                            )
                    );
                } else {
                    e.getDrops().clear();
                    e.getDrops().add(new ItemStack(Material.ENDER_PEARL));
                }
                e.getEntity().getKiller().sendMessage("kill enderman");
            }
        }
    }

    /**
     * Detects player joining when game is not started
     * @param e the event
     */
    @EventHandler
    public void noGamePLayerJoinEvent(PlayerJoinEvent e) {
        if(!ManhuntVars.isGameStarted() && Manhunt.getInstance().getConfig().getBoolean("join-message.enabled")) {
            if(e.getPlayer().hasPermission("manhunt.admin")) {
               e.getPlayer().sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("join-message.perm")));
            } else {
                e.getPlayer().sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("join-message.noperm")));
            }
        }
    }
}
