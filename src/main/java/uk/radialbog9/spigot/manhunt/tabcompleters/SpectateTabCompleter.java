/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;

import java.util.ArrayList;
import java.util.List;

public class SpectateTabCompleter implements TabCompleter {
    /**
     * Tab-completes spectator command
     * @param sender CommandSender sender
     * @param cmd Command command
     * @param alias String alias
     * @param args String[] arguments
     * @return List&lt;String&gt;
     */
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        List<String> players = new ArrayList<>();
        Player pl = null;
        if (sender instanceof Player) {
            pl = (Player) sender;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            String name = p.getName();
            boolean isPlayerSender = pl != null && pl.getUniqueId() == p.getUniqueId();
            if (!isPlayerSender && (ManhuntVars.isHunter(p) || ManhuntVars.isRunner(p))) {
                players.add(name);
            }
        }
        return players;
    }
}
