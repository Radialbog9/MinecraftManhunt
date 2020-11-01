package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ManhuntTabCompleter implements TabCompleter {
    /**
     * Completes tab list commands
     * @param sender CommandSender sender
     * @param cmd Command command
     * @param alias String alias
     * @param args String[] arguments
     * @return List&lt;String&gt;
     */
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

         // /spectate <player> command
        if(cmd.getName().equalsIgnoreCase("spectate") && args.length == 0 && sender.hasPermission("manhunt.spectate")) {
            List<String> players = new ArrayList<String>();
            Player pl = null;
            if (sender instanceof Player) {
                pl = (Player) sender;
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                boolean isPlayerSender = false;
                if (pl != null && pl.getUniqueId() == p.getUniqueId()) isPlayerSender = true;
                if (!isPlayerSender && (ManhuntVars.isHunter(p) || ManhuntVars.isRunner(p))) {
                    players.add(name);
                }
            }
            return players;
        }

         // /manhunt <command> command
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 0) {
            List<String> arguments = new ArrayList<String>();
            if(sender.hasPermission("manhunt.help")) arguments.add("help");
            if(sender.hasPermission("manhunt.start")) arguments.add("start");
            if(sender.hasPermission("manhunt.stop")) arguments.add("stop");
            if(sender.hasPermission("manhunt.list")) arguments.add("list");
            if(sender.hasPermission("manhunt.reset")) arguments.add("reset");
            if(sender.hasPermission("manhunt.remove")) arguments.add("remove");
            if(sender.hasPermission("manhunt.add")) {
                arguments.add("hunter");
                arguments.add("runner");
            }
            return arguments;
        }

         // /manhunt hunter <player> command
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0].equalsIgnoreCase("hunter") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }

        // /manhunt runner <player> command
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0].equalsIgnoreCase("runner") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }

        // /manhunt remove <player> command
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0].equalsIgnoreCase("remove") && sender.hasPermission("manhunt.remove")) {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(ManhuntVars.isHunter(p) || ManhuntVars.isRunner(p)) {
                    players.add(name);
                }
            }
            return players;
        }
        return null;
    }
}
