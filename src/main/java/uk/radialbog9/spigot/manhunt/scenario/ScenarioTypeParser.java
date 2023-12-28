/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import cloud.commandframework.annotations.parsers.Parser;
import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.captions.Caption;
import cloud.commandframework.captions.CaptionVariable;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.exceptions.parsing.ParserException;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import uk.radialbog9.spigot.manhunt.Manhunt;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("unused")
public class ScenarioTypeParser {
    public static class ScenarioParseException extends ParserException {
        private final String input;

        protected ScenarioParseException(@NonNull String input, @NonNull CommandContext<?> context) {
            super(ScenarioTypeParser.class, context, Caption.of("argument.parse.failure.scenario"), CaptionVariable.of("input", input));
            this.input = input;
        }

        public @NonNull String getInput() {
            return this.input;
        }
    }

    @Parser(suggestions = "scenario-type")
    public String scenarioType(CommandContext<CommandSender> sender, Queue<String> input) {
        String scenarioName = input.peek();
        assert scenarioName != null;
        String scenarioType = scenarioName.toUpperCase();
        if(!Manhunt.getScenarioLoader().getAvailableScenarios().containsKey(scenarioType)) {
            throw new ScenarioParseException(scenarioName, sender);
        }
        input.remove();
        return scenarioType;
    }

    @Suggestions("scenario-type")
    public List<String> scenarioTypeSuggestions(CommandContext<CommandSender> sender, String input) {
        List<String> candidates = new ArrayList<>(Manhunt.getScenarioLoader().getAvailableScenarios().keySet());
        candidates.removeIf(candidate -> !candidate.startsWith(input.toUpperCase()));
        return candidates;
    }
}
