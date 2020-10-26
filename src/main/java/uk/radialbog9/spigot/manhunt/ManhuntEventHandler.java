package uk.radialbog9.spigot.manhunt;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ManhuntEventHandler implements Listener {
    /**
     * Called when anyone dies, the code then checks if it's a runner.
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
}
