package uk.radialbog9.spigot.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ManhuntCommand extends Command {
    protected ManhuntCommand(String name) {
        super(name);
    }

    /**
     * Main Manhunt command
     * @param sender CommandSender
     * @param cmd String
     * @param args String[]
     * @return true
     */
    public boolean execute(CommandSender sender, String cmd, String[] args) {

        return true;
    }
}
