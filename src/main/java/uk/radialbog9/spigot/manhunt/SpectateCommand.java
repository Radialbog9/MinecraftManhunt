package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                            p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a That player is not online!"));
                        }
                    } else {
                        p.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a There is no game in progress."));
                    }
                }
            } else {
                //no permission
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        } else {
            //console is player
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Console cannot spectate players!"));
        }
        return true;
    }
}
