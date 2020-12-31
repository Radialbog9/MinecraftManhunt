package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
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

public class ManhuntEventHandler implements Listener {
    /**
     * Detects death for runners
     * @param e PlayerDeathEvent the event
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
                    //If so say hunters win
                    Utils.broadcastServerMessage("&6[Manhunt]&r&a Hunters Win!");
                    //set all player gamemode to spectator
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.setGameMode(GameMode.SPECTATOR);
                    }
                    //End the game
                    ManhuntVars.setGameStarted(false);
                } else {
                    //Else say they died and how many runners remain.
                    Utils.broadcastServerMessage("&6[Manhunt]&r&a &c" + p.getDisplayName() + "&r&a died. There are now &r&c" + ManhuntVars.getRunners().size() + "&r&a remaining.");
                }
            }
        }
    }

    /**
     * Detects when hunters respawn and gives them a compass
     * @param e PlayerDeathEvent the event
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
     * @param e PlayerInteractEvent the event
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
                p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a &cNo players found to track."));
            }
            else {
                //the closest runner has been found
                p.setCompassTarget(p.getLocation());
                p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Tracking player &c" + closestPlayer.getDisplayName() + "&r&a."));
            }
        }
    }

    /**
     * Detects when player joins server while the server is running a manhunt game.
     * @param e PlayerJoinEvent the event
     */
    @EventHandler
    public void inGamePlayerJoinEvent(PlayerJoinEvent e) {
        if (ManhuntVars.isGameStarted()) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a A game is in progress so you have been put into spectator!"));
        }
    }

    /**
     * Detects when hunter/runner leaves the server and sets them as a spectator
     * @param e PlayerQuitEvent the event
     */
    @EventHandler
    public void inGamePlayerLeaveEvent(PlayerQuitEvent e) {
        if (ManhuntVars.isGameStarted()) {
            if (ManhuntVars.isRunner(e.getPlayer())) {
                ManhuntVars.removeRunner(e.getPlayer());
                Utils.broadcastServerMessage("&6[Manhunt]&r&a &r&c" + e.getPlayer().getDisplayName() + "&r&a has disconnected. They are no longer a runner.");
                if (ManhuntVars.getRunners().isEmpty()) {
                    //If so say hunters win
                    Utils.broadcastServerMessage("&6[Manhunt]&r&a There are no runners left. Hunters Win!");
                    //end game
                    Utils.resetGame();
                }
            }
            if (ManhuntVars.isHunter(e.getPlayer())) {
                ManhuntVars.removeRunner(e.getPlayer());
                Utils.broadcastServerMessage("&6[Manhunt]&r&a &r&c" + e.getPlayer().getDisplayName() + "&r&a has disconnected. They are no longer a runner.");
                if(ManhuntVars.getRunners().isEmpty()) {
                    //If so say runners win
                    Utils.broadcastServerMessage("&6[Manhunt]&r&a There are no more hunters left. Runners Win!");
                    //end game
                    Utils.resetGame();
                }
            }
        }
    }

    /**
     * Detects ender dragon death.
     * @param e EntityDeathEvent the event
     */
    @EventHandler
    public void enderDragonDeathEvent(EntityDeathEvent e) {
        if (ManhuntVars.isGameStarted()) {
            //game is running, check for ender dragon death
            if(e.getEntityType() == EntityType.ENDER_DRAGON) {
                //runners win
                Utils.broadcastServerMessage("&6[Manhunt]&r&a The Ender Dragon has been defeated! Runners win!");
                //end game.
                Utils.resetGame();
            }
        }
    }


}
