/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 *  You are allowed to use this code under the GPL3 license, which allows
 *  commercial use, distribution, modification, and licensed works,
 *  providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.tabcompleters;

import cloud.commandframework.annotations.parsers.Parser;
import cloud.commandframework.arguments.parser.ArgumentParseResult;
import cloud.commandframework.arguments.parser.ArgumentParser;
import cloud.commandframework.captions.Caption;
import cloud.commandframework.captions.CaptionVariable;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.exceptions.parsing.ParserException;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.game.Objective;

import java.util.Queue;

public class ObjectiveParser<C> implements ArgumentParser<C, Objective> {
    /**
     * Exception thrown when an objective cannot be parsed.
     */
    public static final class ObjectiveParseException extends ParserException {

        private ObjectiveParseException(final @NonNull String input, final @NonNull CommandContext<?> context) {
            super(ObjectiveParser.class, context, Caption.of("argument.parse.failure.objective"), CaptionVariable.of("input", input));
        }
    }

    /**
     * Parses a player from a string.
     * @param context The command context.
     * @param input The input queue.
     * @return The player.
     */
    @Override
    public @NotNull ArgumentParseResult<Objective> parse(@NonNull CommandContext<@NonNull C> context, @NotNull Queue<String> input) {
        String oName = input.peek();
        if (oName == null) throw new ObjectiveParseException("", context);
        Objective objective;
        try {
            objective = Objective.valueOf(oName);
        } catch (IllegalArgumentException e) {
            return ArgumentParseResult.failure(new ObjectiveParseException(oName, context));
        }

        input.remove();
        return ArgumentParseResult.success(objective);
    }
}

