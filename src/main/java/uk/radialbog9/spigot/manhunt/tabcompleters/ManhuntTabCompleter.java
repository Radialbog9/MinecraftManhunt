/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.tabcompleters;

import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.utils.DependencySupport;

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
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String alias, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("manhunt")) return null;

        // /manhunt <command> command
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
            if(sender.hasPermission("manhunt.help")) arguments.add("help");
            if(sender.hasPermission("manhunt.start")) arguments.add("start");
            if(sender.hasPermission("manhunt.stop")) arguments.add("stop");
            if(sender.hasPermission("manhunt.list")) arguments.add("list");
            if(sender.hasPermission("manhunt.reset")) arguments.add("reset");
            if(sender.hasPermission("manhunt.remove")) arguments.add("remove");
            if(sender.hasPermission("manhunt.settings")) {
                arguments.add("settings");
                arguments.add("scenarios");
            }
            if(sender.hasPermission("manhunt.add")) {
                arguments.add("hunter");
                arguments.add("runner");
            }
            if(sender.hasPermission("manhunt.revive")) arguments.add("revive");
            return arguments;
        }

        // /manhunt hunter <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("hunter") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!GameManager.getGame().isHunter(p)) {
                    if(sender instanceof Player && DependencySupport.isVanishEnabled()) {
                        if(VanishAPI.canSee((Player) sender, p)) {
                            players.add(name);
                        }
                    } else {
                        players.add(name);
                    }
                }
            }
            return players;
        }

        // /manhunt runner <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("runner") && sender.hasPermission("manhunt.add")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(!GameManager.getGame().isRunner(p)) {
                    if(sender instanceof Player && DependencySupport.isVanishEnabled()) {
                        if(VanishAPI.canSee((Player) sender, p)) {
                            players.add(name);
                        }
                    } else {
                        players.add(name);
                    }
                }
            }
            return players;
        }

        // /manhunt remove <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("remove") && sender.hasPermission("manhunt.remove")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(GameManager.getGame().isHunter(p) || GameManager.getGame().isRunner(p)) {
                    if(sender instanceof Player && DependencySupport.isVanishEnabled()) {
                        if(VanishAPI.canSee((Player) sender, p)) {
                            players.add(name);
                        }
                    } else {
                        players.add(name);
                    }
                }
            }
            return players;
        }

        // /manhunt revive <player> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("revive") && sender.hasPermission("manhunt.revive")) {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                String name = p.getName();
                if(GameManager.getGame().getDeadRunners().contains(p)) {
                    if(sender instanceof Player && DependencySupport.isVanishEnabled()) {
                        if(VanishAPI.canSee((Player) sender, p)) {
                            players.add(name);
                        }
                    } else {
                        players.add(name);
                    }
                }
            }
            return players;
        }

        // /manhunt settings <args> command
        else if(args.length == 2 && args[0].equalsIgnoreCase("settings") && sender.hasPermission("manhunt.settings")) {
            List<String> cmds = new ArrayList<>();
            cmds.add("headstarttoggle");
            cmds.add("headstarttime");
            return cmds;
        }

        else return new ArrayList<>();
    }
}
