/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPLv3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;

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
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {


        // /manhunt <command> command
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
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
        else if(args.length == 2 && args[0].equalsIgnoreCase("hunter") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }

        // /manhunt runner <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("runner") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!ManhuntVars.isHunter(p)) {
                    players.add(name);
                }
            }
            return players;
        }

        // /manhunt remove <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("remove") && sender.hasPermission("manhunt.remove")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(ManhuntVars.isHunter(p) || ManhuntVars.isRunner(p)) {
                    players.add(name);
                }
            }
            return players;
        }
        else return null;
    }
}
