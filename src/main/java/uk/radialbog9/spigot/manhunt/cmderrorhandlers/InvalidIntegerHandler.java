/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.cmderrorhandlers;

import cloud.commandframework.arguments.standard.IntegerArgument;
import org.bukkit.command.CommandSender;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

import java.util.function.BiConsumer;

public class InvalidIntegerHandler implements BiConsumer<CommandSender, IntegerArgument.IntegerParseException> {

    @Override
    public void accept(CommandSender sender, IntegerArgument.IntegerParseException e) {
        sender.sendMessage(LanguageTranslator.translate("invalid-integer"));
    }
}
