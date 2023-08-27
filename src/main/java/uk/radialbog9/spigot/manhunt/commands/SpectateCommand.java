/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.Utils;

public class SpectateCommand {
    @CommandMethod("spectate <player>")
    @CommandPermission("manhunt.spectate")
    public void mSpectate(@NotNull CommandSender sender, @NotNull @Argument(value = "player", suggestions = "player-sv-ingame") Player existingPlayer) {
        if (!(sender instanceof Player)) {
            //console is executing command, deny it
            sender.sendMessage(LanguageTranslator.translate("console-cannot-spectate"));
            return;
        }

        Player p = (Player) sender;

        if (!Utils.vanishCanSee(sender, existingPlayer)) {
            sender.sendMessage(LanguageTranslator.translate("player-not-online", existingPlayer.getName()));
            return;
        }

        if (!GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("no-game-in-progress"));
            return;
        }

        if (GameManager.getGame().isRunner(p) || GameManager.getGame().isHunter(p)) {
            //not spectator
            p.sendMessage(LanguageTranslator.translate("not-spectator"));
            return;
        }

        if (!GameManager.getGame().isRunner(existingPlayer) && !GameManager.getGame().isHunter(existingPlayer)) {
            p.sendMessage(LanguageTranslator.translate("player-not-in-game"));
            return;
        }

        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(existingPlayer);
        p.sendMessage(LanguageTranslator.translate("now-spectating-player", existingPlayer.getDisplayName()));
    }
}
