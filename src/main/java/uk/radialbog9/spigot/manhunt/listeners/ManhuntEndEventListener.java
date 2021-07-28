/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

/**
 * This class handles the event of the game ending
 */
public class ManhuntEndEventListener implements Listener {
    @EventHandler
    public void gameEndEvent(ManhuntGameEndEvent e) {
        //check win causes, modify player data, and then end the game
        if(e.getEndCause() == GameEndCause.ALL_RUNNERS_LEAVE) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.no-more-runners-left"));
            for(Player p : ManhuntVars.getHunters()) {
                ManhuntVars.getPlayerConfig(p).addHunterWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if(e.getEndCause() == GameEndCause.RUNNERS_ALL_DIE) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.all-runners-dead"));
            for(Player p : ManhuntVars.getHunters()) {
                ManhuntVars.getPlayerConfig(p).addHunterWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if(e.getEndCause() == GameEndCause.ALL_HUNTERS_LEAVE) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.no-more-hunters-left"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if(e.getEndCause() == GameEndCause.RUNNERS_KILL_DRAGON) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.runners-kill-ender-dragon"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if(e.getEndCause() == GameEndCause.TIME_UP) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.time_up"));
            for(Player p : ManhuntVars.getRunners()) {
                ManhuntVars.getPlayerConfig(p).addRunnerWin();
                ManhuntVars.getPlayerConfig(p).save();
            }
        }
        else if(e.getEndCause() == GameEndCause.ENDED_PREMATURELY) {
            Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.game-ended-prematurely"));
        }

        //set all players to spectator
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }

        //reset runners and hunters
        ManhuntVars.removeAllHunters();
        ManhuntVars.removeAllRunners();

        //fully end game
        ManhuntVars.setGameStarted(false);

        Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.game-ended"));
    }
}
