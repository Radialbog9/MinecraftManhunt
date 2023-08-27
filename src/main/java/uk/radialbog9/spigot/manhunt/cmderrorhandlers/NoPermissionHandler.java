/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.cmderrorhandlers;

import cloud.commandframework.exceptions.NoPermissionException;
import org.bukkit.command.CommandSender;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

import java.util.function.BiConsumer;

public class NoPermissionHandler implements BiConsumer<CommandSender, NoPermissionException> {
    @Override
    public void accept(CommandSender sender, NoPermissionException e) {
        sender.sendMessage(LanguageTranslator.translate("no-permission"));
    }
}
