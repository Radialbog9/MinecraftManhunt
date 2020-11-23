package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class ManhuntEventHandler implements Listener {
    /**
     * Detects death for runners
     * @param e PlayerDeathEvent the event
     */
    @EventHandler
    public void runnerDeathEvent(PlayerDeathEvent e) {
        if(ManhuntVars.isGameStarted()) {
            Player p = e.getEntity();
            //Is runner?
            if(ManhuntVars.isRunner(p)) {
                //The death was a runner
                //Set gamemode to spectator
                p.setGameMode(GameMode.SPECTATOR);
                //Remove from runners
                ManhuntVars.removeRunner(p);
                //Check if that was the last runner alive
                if(ManhuntVars.getRunners().isEmpty()) {
                    //If so say hunters win
                    ServerMessages.broadcastServerMessage("&6[Manhunt]&r&a Hunters Win!");
                    //set all player gamemode to spectator
                    for(Player pl : Bukkit.getOnlinePlayers()) {
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
        if(ManhuntVars.isGameStarted()) {
            Player p = e.getPlayer();
            if(ManhuntVars.isHunter(p)) {
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
        if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS && ManhuntVars.isGameStarted() && ManhuntVars.isHunter(p)) {
            double closest = Double.MAX_VALUE;
            Player closestp = null;
            for(Player i : ManhuntVars.getRunners()){
                double dist = i.getLocation().distance(p.getLocation());
                if (i.getUniqueId() != p.getUniqueId() && i.getWorld().getName().equals(p.getWorld().getName()) && (closest == Double.MAX_VALUE || dist < closest)){
                    closestp = i;
                    closest = dist;
                }
            }
            if (closestp == null){
                //No runners nearby in the same world
                p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a &cNo players found to track."));
            }
            else{
                //the closest runner has been found
                p.setCompassTarget(p.getLocation());
                p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Tracking player &c" + closestp.getDisplayName() + "&r&a."));
            }
        }
    }

    /**
     * Detects when player joins server while the server is running a manhunt game.
     * @param e PlayerJoinEvent the event
     */
    @EventHandler
    public void inGamePlayerJoinEvent(PlayerJoinEvent e) {
        if(ManhuntVars.isGameStarted()) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a A game is in progress so you have been put into spectator!"));
        }
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        if(ManhuntVars.isGameStarted() && !ManhuntVars.isHunter(e.getPlayer()) && !ManhuntVars.isRunner(e.getPlayer())) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }
}
