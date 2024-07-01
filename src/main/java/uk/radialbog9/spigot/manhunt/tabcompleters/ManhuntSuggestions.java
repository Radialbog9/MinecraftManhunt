/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.tabcompleters;

import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.game.Objective;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ManhuntSuggestions {
    private @NotNull List<Player> getVisibleOnlinePlayers(@NotNull CommandSender sender) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        ArrayList<Player> playerList = new ArrayList<>(players);
        playerList.removeIf(player -> !Utils.vanishCanSee(sender, player));
        return playerList;
    }

    private @NotNull List<String> getFilterPlayerSuggestions(@NotNull CommandSender sender, @NotNull String input, @Nullable Predicate<Player> excludeFilter) {
        // Get online players
        List<Player> players = getVisibleOnlinePlayers(sender);

        // Filter out players that are hunters
        if(excludeFilter != null) players.removeIf(excludeFilter);

        // Convert player list to list of names
        List<String> completions = players.stream().map(CommandSender::getName).collect(Collectors.toList());

        // Remove players that do not start with the input
        completions.removeIf(s -> !s.toLowerCase().startsWith(input.toLowerCase()));

        // Return final array
        return completions;
    }

    @Suggestions("player-sv-generic")
    public List<String> playerGenericSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        return getFilterPlayerSuggestions(context.getSender(), input, null);
    }

    @Suggestions("player-sv-nothunter")
    public List<String> playerNotHunterSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        return getFilterPlayerSuggestions(context.getSender(), input, GameManager.getGame()::isHunter);
    }

    @Suggestions("player-sv-notrunner")
    public List<String> playerNotRunnerSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        return getFilterPlayerSuggestions(
                context.getSender(),
                input,
                GameManager.getGame()::isRunner
        );
    }

    @Suggestions("player-sv-ingame")
    public List<String> playerInGameSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        return getFilterPlayerSuggestions(
                context.getSender(),
                input,
                p -> !GameManager.getGame().isHunter(p)
                        && !GameManager.getGame().isRunner(p)
        );
    }

    @Suggestions("player-sv-deadrunner")
    public List<String> playerDeadRunnerSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        return getFilterPlayerSuggestions(
                context.getSender(),
                input,
                p -> !GameManager.getGame().isDeadRunner(p)
        );
    }

    @Suggestions("objective")
    public List<String> objectiveSuggestions(@NotNull CommandContext<CommandSender> context, String input) {
        List<String> completions = new ArrayList<>();
        for(Objective objective : Objective.values()) {
            completions.add(objective.name());
        }
        completions.removeIf(c -> !c.toLowerCase().startsWith(input.toLowerCase()));
        return completions;
    }
}
