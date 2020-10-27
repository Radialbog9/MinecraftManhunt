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
        if(cmd.getName().equalsIgnoreCase("spectate") && args.length == 0) {
            List<String> players = new ArrayList<String>();
            Player pl = null;
            if(sender instanceof Player) {
                pl = (Player) sender;
            }
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!(pl.getUniqueId() == p.getUniqueId())) {
                    players.add(name);
                }
            }
            return players;
        }
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 0) {
            List<String> arguments = new ArrayList<String>();
            arguments.add("start");
            arguments.add("stop");
            arguments.add("list");
            arguments.add("reset");
            arguments.add("hunter");
            arguments.add("runner");
            arguments.add("remove");
            return arguments;
        }
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0] == "hunter") {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0] == "runner") {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }
        if(cmd.getName().equalsIgnoreCase("manhunt") && args.length == 1 && args[0] == "remove") {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(ManhuntVars.isHunter(p) || ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }
        return null;
    }
}
