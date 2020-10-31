package uk.radialbog9.spigot.manhunt;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ManhuntEventHandler implements Listener {
    /**
     * Detects death for runners
     * @param e PlayerDeathEvent the event
     */
    @EventHandler
    public void runnerDeathEvent(PlayerDeathEvent e) {
        if(ManhuntVars.isGameStarted()) {
            //Is runner?
            if(ManhuntVars.isRunner(e.getEntity())) {
                //The death was a runner
                //Set gamemode to spectator
                e.getEntity().setGameMode(GameMode.SPECTATOR);
                //Remove from runners
                ManhuntVars.removeRunner(e.getEntity());
                //Check if that was the last runner alive
                if(ManhuntVars.getRunners().isEmpty()) {
                    //If so say hunters win
                    ServerMessages.broadcastServerMessage("&6[Manhunt]&r&a Hunters Win!");
                    //End the game
                    ManhuntVars.setGameStarted(false);
                } else {
                    //Else say they died and how many runners remain.
                    ServerMessages.broadcastServerMessage("&6[Manhunt]&r&a &c" + e.getEntity().getDisplayName() + "&r&a died. There are now &r&c" + ManhuntVars.getRunners().size() + "&r&a remaining.");
                }
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
                p.sendMessage(ChatColors.getMsgColor("&6[Manhunt]&r&a &cNo players found to track."));
            }
            else{
                //the closest runner has been found
                p.setCompassTarget(p.getLocation());
                p.sendMessage(ChatColors.getMsgColor("&6[Manhunt]&r&a Tracking player &c" + closestp.getDisplayName() + "&r&a."));
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
        }
    }
}
