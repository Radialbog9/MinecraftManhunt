/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import org.bukkit.command.CommandSender;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ScenarioSuggestions {
    @Suggestions("scenariotype")
    public List<String> scenarioTypeSuggestions(CommandContext<CommandSender> sender, String input) {
        List<String> candidates = new ArrayList<>(Manhunt.getScenarioLoader().getAvailableScenarios().keySet());
        candidates.removeIf(candidate -> !candidate.startsWith(input.toUpperCase()));
        return candidates;
    }
}
