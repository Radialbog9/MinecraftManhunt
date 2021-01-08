package uk.radialbog9.spigot.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.ManhuntVars;
import uk.radialbog9.spigot.manhunt.Utils;

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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(sender.hasPermission("manhunt.spectate")) {
                //permission
                if(args.length == 0) {
                    //no player specified
                    p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Not enough agruments!"));
                    p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/spectate <player>"));
                } else if (args.length > 1) {
                    //too many arguments
                    p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Too many arguments!"));
                    p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/spectate <player>"));
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
                                    p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You are now spectating &c" + existingPlayer.getDisplayName() + "&r&a."));
                                }
                            } else {
                                String a = Manhunt.getInstance().getConfig().getString("language.player-not-online");
                                if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, args[0])));
                            }
                        } else {
                            //not spectator
                        }
                    } else {
                        p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a There is no game in progress."));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else {
            //console is executing command, deny it
            sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.console-cannot-spectate")));
        }
        return true;
    }
}
