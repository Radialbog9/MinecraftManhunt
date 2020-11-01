package uk.radialbog9.spigot.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateCommand implements CommandExecutor {

    /**
     * Spectate command
     * <code>/spectate &lt;player&gt;</code>
     * @param sender CommandSender command sender
     * @param cmd Command command
     * @param label String label
     * @param args String[] arguments
     * @return boolean always true
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("manhunt.spectate")) {
            //permission
            if(args.length == 0) {
                //no player specified
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Not enough agruments!"));
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/spectate <player>"));
            } else if (args.length > 1) {
                //too many arguments
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Too many arguments!"));
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/spectate <player>"));
            } else {
                //player given
                //check the player
                Player existingPlayer = null;

            }
        } else {
            //no permission
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
        }
        return true;
    }
}
