/*
 * Copyright (c) 2020-2025 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.game.Objective;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.settings.SettingsMenu;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@SuppressWarnings("unused")
public class ManhuntSettingsCommand {
    @CommandMethod("manhunt settings")
    @CommandPermission("manhunt.settings")
    public void mSettingsMenu(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            if (!GameManager.getGame().isGameStarted())
                SettingsMenu.displayMenu((Player) sender);
            else
                sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
        } else {
            sender.sendMessage(Utils.getMsgColor("&cYou can't use the settings menu as console!"));
        }
    }

    @CommandMethod("manhunt settings headstarttoggle")
    @CommandPermission("manhunt.settings")
    public void mSettingsHeadStartToggle(@NotNull CommandSender sender) {
        if(GameManager.getGame().isGameStarted()){
            sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
            return;
        }
        if (ManhuntSettings.isHeadStartEnabled()) {
            //head start enabled so disable it
            ManhuntSettings.setHeadStartEnabled(false);
            sender.sendMessage(LanguageTranslator.translate("head-start-disabled"));
        } else {
            //head start disabled so enable it
            ManhuntSettings.setHeadStartEnabled(true);
            sender.sendMessage(LanguageTranslator.translate("head-start-enabled"));
        }
        if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
    }

    @CommandMethod("manhunt settings headstarttime <time>")
    @CommandPermission("manhunt.settings")
    public void mSettingsHeadStartTime(@NotNull CommandSender sender, @Argument("time") int time) {
        if(GameManager.getGame().isGameStarted()){
            sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
            return;
        }
        if (time <= 0) {
            sender.sendMessage(LanguageTranslator.translate("invalid-integer"));
            return;
        }
        ManhuntSettings.setHeadStartTime(time);
        sender.sendMessage(LanguageTranslator.translate("head-start-timer-set", String.valueOf(time)));
        if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
    }

    @CommandMethod("manhunt settings objective <objective>")
    @CommandPermission("manhunt.settings")
    public void mObjective(@NotNull CommandSender sender,
                           @Argument(value = "objective", suggestions = "objective") Objective objective) {
        if(GameManager.getGame().isGameStarted()){
            sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
            return;
        }
        ManhuntSettings.setObjective(objective);
        sender.sendMessage(LanguageTranslator.translate(
                "objective-set",
                LanguageTranslator.translate("objective." + objective.toString())
        ));
    }

    @CommandMethod("manhunt settings survivetimer <time>")
    @CommandPermission("manhunt.settings")
    public void mSettingsTimer(@NotNull CommandSender sender, @Argument("time") int time) {
        if(GameManager.getGame().isGameStarted()){
            sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
            return;
        }
        if (time <= 0) {
            sender.sendMessage(LanguageTranslator.translate("invalid-integer"));
            return;
        }
        ManhuntSettings.setSurviveGameLength(time);
        sender.sendMessage(LanguageTranslator.translate("survive-timer-set", String.valueOf(time)));
        if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
    }
}
