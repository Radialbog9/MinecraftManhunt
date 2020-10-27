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
            sender.sendMessage(ChatColors.getMsgColor("&6[Manhunt]&r&a No command specified. Type /manhunt help for command help."));
        }
        return true;
    }
}
