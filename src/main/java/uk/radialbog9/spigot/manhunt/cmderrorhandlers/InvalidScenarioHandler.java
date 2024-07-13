/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.cmderrorhandlers;

import cloud.commandframework.exceptions.ArgumentParseException;
import org.bukkit.command.CommandSender;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

import java.util.function.BiConsumer;

public class InvalidScenarioHandler implements BiConsumer<CommandSender, ArgumentParseException> {
    @Override
    public void accept(CommandSender sender, ArgumentParseException e) {
        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-invalid"));
    }
}
