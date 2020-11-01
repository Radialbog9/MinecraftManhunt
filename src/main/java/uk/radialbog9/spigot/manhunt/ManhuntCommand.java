package uk.radialbog9.spigot.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ManhuntCommand implements CommandExecutor {

    /**
     * Main Manhunt command
     * @param sender CommandSender command sender
     * @param cmd Command command
     * @param label String label
     * @param args String[] arguments
     * @return boolean always true
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a No command specified. Type /manhunt help for command help."));
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Command Help:"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt help &r&a- &r&eShows help"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt hunter <player> &r&a- &r&eSets a player to hunter"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt runner <player> &r&a- &r&eSets a player to runner"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt remove <player> &r&a- &r&eSets a player to runner"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt start &r&a- &r&eStarts the manhunt"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt stop &r&a- &r&eStops the game"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt list &r&a- &r&eList all runners and hunters"));
        } else if (args[0].equalsIgnoreCase("hunter")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length == 1) {
                    //no player specified
                } else {
                    //player specified
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        }
        return true;
    }
}
