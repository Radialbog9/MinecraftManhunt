/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.playerdata.DataUtils;
import uk.radialbog9.spigot.manhunt.playerdata.PlayerData;

import java.util.HashMap;

public class LeaderboardCommand {
    @CommandMethod("leaderboard")
    @CommandDescription("View the leaderboard")
    @CommandPermission("manhunt.leaderboard")
    public void leaderboard(CommandSender sender) {
        // Get the total wins leaderboard
        HashMap<OfflinePlayer, Integer> lb = Manhunt.getLeaderboard().getTotalWinsLeaderboard();

        // Send the leaderboard title
        sender.sendMessage(LanguageTranslator.translate("leaderboard.title"));

        // If the leaderboard is empty, send a message to the sender
        if (lb.isEmpty()) {
            sender.sendMessage(LanguageTranslator.translate("leaderboard.no-data"));
            return;
        }

        // Send the leaderboard entries
        int i = 1;
        for (OfflinePlayer p : lb.keySet()) {
            if(i > 10) break; // Only show the top 10 players
            sender.sendMessage(LanguageTranslator.translate("leaderboard.entry",
                    String.valueOf(i), // Place
                    p.getName(), // Player name
                    String.valueOf(lb.get(p)) // Wins
            ));
            i++;
        }
    }

    @CommandMethod("leaderboard <player>")
    @CommandDescription("View the leaderboard for a specific player")
    @CommandPermission("manhunt.leaderboard")
    public void leaderboard(CommandSender sender, @Argument("player") OfflinePlayer player) {
        PlayerData pd = DataUtils.getPlayerData(player);
        if(pd == null) {
            sender.sendMessage(LanguageTranslator.translate("leaderboard.data-not-found", player.getName()));
            return;
        }
        sender.sendMessage(LanguageTranslator.translate("leaderboard.player-data",
                player.getName() // Player name
        ));
        sender.sendMessage(LanguageTranslator.translate(
                "leaderboard.player-wins-hunter", String.valueOf(pd.getHunterWins())
        ));
        sender.sendMessage(LanguageTranslator.translate(
                "leaderboard.player-wins-runner", String.valueOf(pd.getRunnerWins())
        ));
        sender.sendMessage(LanguageTranslator.translate(
                "leaderboard.player-losses-hunter", String.valueOf(pd.getHunterLosses())
        ));
        sender.sendMessage(LanguageTranslator.translate(
                "leaderboard.player-losses-runner", String.valueOf(pd.getRunnerDeaths())
        ));
    }
}
