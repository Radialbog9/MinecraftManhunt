/*
 * Copyright (c) 2020-2025 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */
package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.game.Objective;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioMenu;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioUtils;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.settings.SettingsMenu;
import uk.radialbog9.spigot.manhunt.game.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unused"})
public class ManhuntCommand {

    @CommandMethod("manhunt")
    public void mManhunt(@NotNull CommandSender sender) {
        sender.sendMessage(LanguageTranslator.translate("no-command-specified"));
    }

    @CommandMethod("manhunt help")
    public void mHelp(@NotNull CommandSender sender) {
        sender.sendMessage(LanguageTranslator.translate("command-help"));
        sender.sendMessage(LanguageTranslator.translate(
                "help-format",
                "/manhunt help",
                LanguageTranslator.translate("help.help")
        ));
        if (Utils.hasNoManhuntPermissions(sender))
            sender.sendMessage(LanguageTranslator.translate("no-subcommand-perm"));
        if (sender.hasPermission("manhunt.add"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt hunter " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.hunter")
            ));
        if (sender.hasPermission("manhunt.add"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt runner " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.runner")
            ));
        if (sender.hasPermission("manhunt.remove"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt remove " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.remove")
            ));
        if (sender.hasPermission("manhunt.start"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt start",
                    LanguageTranslator.translate("help.start")
            ));
        if (sender.hasPermission("manhunt.stop"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt stop",
                    LanguageTranslator.translate("help.stop")
            ));
        if (sender.hasPermission("manhunt.list"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt list",
                    LanguageTranslator.translate("help.list")
            ));
        if (sender.hasPermission("manhunt.settings"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt settings",
                    LanguageTranslator.translate("help.settings")
            ));
        if (sender.hasPermission("manhunt.revive"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt revive " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.revive")
            ));
        if (sender.hasPermission("manhunt.scenarios"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt scenarios",
                    LanguageTranslator.translate("help.scenarios")
            ));
        if (sender.hasPermission("manhunt.reload")) {
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt reload",
                    LanguageTranslator.translate("help.reload")
            ));
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt saveconfig",
                    LanguageTranslator.translate("help.saveconfig")
            ));
        }
        if (sender.hasPermission("manhunt.spectate"))
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/spectate " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.spectate")
            ));
        if (sender.hasPermission("manhunt.leaderboard")) {
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/leaderboard",
                    LanguageTranslator.translate("help.leaderboard")
            ));
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/leaderboard " + LanguageTranslator.translate("player-placeholder"),
                    LanguageTranslator.translate("help.leaderboard.player")
            ));
        }

    }

    @CommandMethod("manhunt reload")
    @CommandPermission("manhunt.reload")
    public void mReload(@NotNull CommandSender sender) {
        // Check if game is started
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("no-reload-ingame"));
            return;
        }
        // Reload the config
        Manhunt.getInstance().reloadManhuntConfig();
        // Reload the settings
        ManhuntSettings.loadFromCfg();
        // Reload scenarios config
        ScenarioUtils.loadConfigAllScenarios();
        // Reload the language
        Manhunt.getInstance().loadLanguage();
        sender.sendMessage(LanguageTranslator.translate("reload-successful"));
    }

    @CommandMethod("manhunt saveconfig")
    @CommandPermission("manhunt.reload")
    public void mSaveConfig(@NotNull CommandSender sender) {
        // Save the settings
        ManhuntSettings.save();
        //save the config
        Manhunt.getInstance().saveManhuntConfig();
        sender.sendMessage(LanguageTranslator.translate("save-successful"));
    }

    @CommandMethod("manhunt hunter <player>")
    @CommandPermission("manhunt.add")
    public void mHunter(@NotNull CommandSender sender, @NotNull @Argument(value = "player", suggestions = "player-sv-nothunter") Player pl) {
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("no-change-hunter-in-game"));
            return;
        }

        if (!Utils.vanishCanSee(sender, pl)) {
            sender.sendMessage(LanguageTranslator.translate("player-not-online", pl.getName()));
            return;
        }

        if (GameManager.getGame().isHunter(pl)) {
            //already a hunter
            sender.sendMessage(LanguageTranslator.translate("p-already-hunter", pl.getDisplayName()));
            return;
        }

        //player isn't hunter already - add them
        if (GameManager.getGame().isRunner(pl)) GameManager.getGame().getRunners().remove(pl);
        GameManager.getGame().getHunters().add(pl);
        sender.sendMessage(LanguageTranslator.translate("p-now-hunter", pl.getDisplayName()));
        pl.sendMessage(LanguageTranslator.translate("now-hunter"));
    }

    @CommandMethod("manhunt runner <player>")
    @CommandPermission("manhunt.add")
    public void mRunner(@NotNull CommandSender sender, @NotNull @Argument(value = "player", suggestions = "player-sv-notrunner") Player pl) {
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("no-change-runner-in-game"));
            return;
        }

        if (!Utils.vanishCanSee(sender, pl)) {
            sender.sendMessage(LanguageTranslator.translate("player-not-online", pl.getName()));
            return;
        }

        if (GameManager.getGame().isRunner(pl)) {
            //already a hunter
            sender.sendMessage(LanguageTranslator.translate("p-already-runner", pl.getDisplayName()));
            return;
        }

        //player isn't hunter already - add them
        if (GameManager.getGame().isHunter(pl)) GameManager.getGame().getHunters().remove(pl);
        GameManager.getGame().getRunners().add(pl);
        sender.sendMessage(LanguageTranslator.translate("p-now-runner", pl.getDisplayName()));
        pl.sendMessage(LanguageTranslator.translate("now-runner"));
    }

    @CommandMethod("manhunt remove <player>")
    @CommandPermission("manhunt.remove")
    public void mRemove(@NotNull CommandSender sender, @NotNull @Argument(value = "player", suggestions = "player-sv-ingame") Player pl) {
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("no-remove-in-game"));
            return;
        }


        if (!Utils.vanishCanSee(sender, pl)) {
            sender.sendMessage(LanguageTranslator.translate("player-not-online", pl.getName()));
            return;
        }

        //player exists
        //check if runner or hunter
        if (!GameManager.getGame().isHunter(pl) && !GameManager.getGame().isRunner(pl)) {
            //player is already spectator
            sender.sendMessage(LanguageTranslator.translate("p-already-spectator", pl.getDisplayName()));
            return;
        }

        //set as spectator
        GameManager.getGame().getHunters().remove(pl);
        GameManager.getGame().getRunners().remove(pl);
        sender.sendMessage(LanguageTranslator.translate("p-now-spectator", pl.getDisplayName()));
        pl.sendMessage(LanguageTranslator.translate("now-spectator"));
    }

    @CommandMethod("manhunt revive <player>")
    @CommandPermission("manhunt.revive")
    public void mRevive(@NotNull CommandSender sender, @NotNull @Argument(value = "player", suggestions = "player-sv-deadrunner") Player pl) {
        if (!GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("cannot-revive-game-not-started"));
            return;
        }

        if (!Utils.vanishCanSee(sender, pl)) {
            sender.sendMessage(LanguageTranslator.translate("player-not-online", pl.getName()));
            return;
        }

        if(!GameManager.getGame().getDeadRunners().contains(pl)) {
            sender.sendMessage(LanguageTranslator.translate("cannot-revive-player", pl.getDisplayName()));
        }
        pl.teleport(pl.getBedSpawnLocation() != null ? pl.getBedSpawnLocation() : pl.getWorld().getSpawnLocation());
        GameManager.getGame().getRunners().add(pl);
        pl.setGameMode(GameMode.SURVIVAL);

        //broadcast messages
        pl.sendMessage(LanguageTranslator.translate("you-revived"));
        sender.sendMessage(LanguageTranslator.translate("revived-player", pl.getDisplayName()));
        Utils.broadcastServerMessage(LanguageTranslator.translate(
                "player-revived-bc",
                pl.getDisplayName(),
                String.valueOf(GameManager.getGame().getRunners().size()
                )));

    }

    @CommandMethod("manhunt start")
    @CommandPermission("manhunt.start")
    public void mStart(@NotNull CommandSender sender) {
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("game-already-in-progress"));
            return;
        }

        if (GameManager.getGame().getRunners().isEmpty() || GameManager.getGame().getHunters().isEmpty()) {
            sender.sendMessage(LanguageTranslator.translate("too-few-players"));
            return;
        }

        GameManager.startGame();
    }

    @CommandMethod("manhunt stop")
    @CommandPermission("manhunt.stop")
    public void mStop(@NotNull CommandSender sender) {
        GameManager.endGame(GameEndCause.ENDED_PREMATURELY);
    }

    @CommandMethod("manhunt list")
    @CommandPermission("manhunt.list")
    public void mList(@NotNull CommandSender sender) {
        //init vars
        StringBuilder hunters = new StringBuilder();
        StringBuilder runners = new StringBuilder();
        StringBuilder spectators = new StringBuilder();
        int hunterCount = GameManager.getGame().getHunters().size();
        int runnerCount = GameManager.getGame().getRunners().size();
        int spectatorCount = Bukkit.getOnlinePlayers().size() - (hunterCount + runnerCount);

        // Generate player list
        // All hunters and runners are shown
        // Spectators are shown if they are not in vanish
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (GameManager.getGame().isHunter(p)) {
                if (hunters.toString().isEmpty())
                    hunters.append(LanguageTranslator.translate("player-format-first", p.getDisplayName()));
                else hunters.append(LanguageTranslator.translate("player-format", p.getDisplayName()));
            } else if (GameManager.getGame().isRunner(p)) {
                if (runners.toString().isEmpty())
                    runners.append(LanguageTranslator.translate("player-format-first", p.getDisplayName()));
                else runners.append(LanguageTranslator.translate("player-format", p.getDisplayName()));
            } else {
                if (!Utils.vanishCanSee(sender, p)) continue; // Prevent spectators from being shown if in vanish
                if (spectators.toString().isEmpty())
                    spectators.append(LanguageTranslator.translate("player-format-first", p.getDisplayName()));
                else spectators.append(LanguageTranslator.translate("player-format", p.getDisplayName()));
            }
        }

        if (hunters.toString().isEmpty()) hunters.append(LanguageTranslator.translate("none-list"));
        if (runners.toString().isEmpty()) runners.append(LanguageTranslator.translate("none-list"));
        if (spectators.toString().isEmpty()) spectators.append(LanguageTranslator.translate("none-list"));

        sender.sendMessage(GameManager.getGame().isGameStarted() ?
                LanguageTranslator.translate("game-is-started")
                : LanguageTranslator.translate("game-is-stopped"));
        sender.sendMessage(LanguageTranslator.translate("list-count", String.valueOf(hunterCount), String.valueOf(runnerCount), String.valueOf(spectatorCount)));
        sender.sendMessage(LanguageTranslator.translate("hunter-list", hunters.toString()));
        sender.sendMessage(LanguageTranslator.translate("runner-list", runners.toString()));
        sender.sendMessage(LanguageTranslator.translate("spectator-list", spectators.toString()));
    }

    @CommandMethod("manhunt randomiseplayers")
    @CommandPermission("manhunt.add")
    public void mPlayerRandomise(@NotNull CommandSender sender) {
        if(GameManager.getGame().isGameStarted()){
            sender.sendMessage(LanguageTranslator.translate("settingsmenu.no-change-ingame"));
            return;
        }

        // Clear all players
        GameManager.getGame().getHunters().clear();
        GameManager.getGame().getRunners().clear();

        // Create a new array of players
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        Collections.shuffle(playerList);

        // Loop through all players
        // Set every other player as hunters/runners respectively
        int i = 0;
        for (Player p : playerList) {
            if (i % 2 == 0) {
                GameManager.getGame().getHunters().add(p);
                sender.sendMessage(LanguageTranslator.translate("p-now-hunter", p.getDisplayName()));
                p.sendMessage(LanguageTranslator.translate("now-hunter"));
            } else {
                GameManager.getGame().getRunners().add(p);
                sender.sendMessage(LanguageTranslator.translate("p-now-runner", p.getDisplayName()));
                p.sendMessage(LanguageTranslator.translate("now-runner"));
            }
            i++;
        }
    }
}
