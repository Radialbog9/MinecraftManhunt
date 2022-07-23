/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;

public class SpectateCommand implements CommandExecutor {

    /**
     * Spectate command
     * <code>/spectate &lt;player&gt;</code>
     * Allows people who are not in a game to spectate the hunters/runners.
     * @param sender CommandSender command sender
     * @param cmd Command command
     * @param label String label
     * @param args String[] arguments
     * @return boolean always true
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(sender.hasPermission("manhunt.spectate")) {
                //permission
                if(args.length == 0) {
                    //no player specified
                    p.sendMessage(LanguageTranslator.translate("not-enough-args"));
                    p.sendMessage(LanguageTranslator.translate("usage", "/spectate <player>"));
                } else if (args.length > 1) {
                    //too many arguments
                    p.sendMessage(LanguageTranslator.translate("too-many-args"));
                    p.sendMessage(LanguageTranslator.translate("usage", "/spectate <player>"));
                } else {
                    //player given
                    //check if in game
                    if(ManhuntVars.isGameStarted()) {
                        if(!(ManhuntVars.isRunner(p) | ManhuntVars.isHunter(p))) {
                            //they are spectator
                            //check the player
                            Player existingPlayer = Bukkit.getPlayer(args[0]);
                            if(existingPlayer != null) {
                                //player exists
                                if(ManhuntVars.isRunner(existingPlayer) || ManhuntVars.isHunter(existingPlayer)) {
                                    p.setGameMode(GameMode.SPECTATOR);
                                    p.teleport(existingPlayer);
                                    p.sendMessage(LanguageTranslator.translate("now-spectating-player", existingPlayer.getDisplayName()));
                                } else {
                                    p.sendMessage(LanguageTranslator.translate("player-not-in-game"));
                                }
                            } else {
                                p.sendMessage(LanguageTranslator.translate("player-not-online", args[0]));
                            }
                        } else {
                            //not spectator
                            p.sendMessage(LanguageTranslator.translate("not-spectator"));
                        }
                    } else {
                        //no game in progress
                        p.sendMessage(LanguageTranslator.translate("no-game-in-progress"));
                    }
                }
            } else {
                //no perm
                p.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else {
            //console is executing command, deny it
            sender.sendMessage(LanguageTranslator.translate("console-cannot-spectate"));
        }
        return true;
    }
}
