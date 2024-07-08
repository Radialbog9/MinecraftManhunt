/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.cmderrorhandlers;

import cloud.commandframework.bukkit.parsers.PlayerArgument;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

import java.util.function.BiConsumer;

public class InvalidPlayerHandler implements BiConsumer<CommandSender, PlayerArgument.PlayerParseException> {
    @Override
    public void accept(@NotNull CommandSender sender, PlayerArgument.PlayerParseException e) {
        sender.sendMessage(LanguageTranslator.translate("player-not-online", e.getInput()));
    }
}
