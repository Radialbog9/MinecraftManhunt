/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */
package uk.radialbog9.spigot.manhunt.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.Flag;
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
        // Reload scenarios config
        ScenarioUtils.loadConfigAllScenarios();
        // Reload the language
        Manhunt.getInstance().loadLanguage();
        sender.sendMessage(LanguageTranslator.translate("reload-successful"));
    }

    @CommandMethod("manhunt saveconfig")
    @CommandPermission("manhunt.reload")
    public void mSaveConfig(@NotNull CommandSender sender) {
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

        //Generate player list
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
                if (!Utils.vanishCanSee(sender, p)) continue;
                if (spectators.toString().isEmpty())
                    spectators.append(LanguageTranslator.translate("player-format-first", p.getDisplayName()));
                else spectators.append(LanguageTranslator.translate("player-format", p.getDisplayName()));
            }
        }
        sender.sendMessage(GameManager.getGame().isGameStarted() ?
                LanguageTranslator.translate("game-is-started")
                : LanguageTranslator.translate("game-is-stopped"));
        sender.sendMessage(LanguageTranslator.translate("list-count", String.valueOf(hunterCount), String.valueOf(runnerCount), String.valueOf(spectatorCount)));
        sender.sendMessage(LanguageTranslator.translate("hunter-list", hunters.toString()));
        sender.sendMessage(LanguageTranslator.translate("runner-list", runners.toString()));
        sender.sendMessage(LanguageTranslator.translate("spectator-list", spectators.toString()));
    }

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

    @CommandMethod("manhunt scenarios")
    @CommandPermission("manhunt.scenarios")
    public void mScenariosMenu(@NotNull CommandSender sender, @Flag("page") Integer page) {
        if(page == null || page < 1)
            page = 1;

        if (!(sender instanceof Player)){
            sender.sendMessage(LanguageTranslator.translate("no-run-console"));
            return;
        }
        ScenarioMenu.displayMenu((Player) sender, page);
    }

    @CommandMethod("manhunt scenarios list")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioList(@NotNull CommandSender sender) {
        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-list"));
        for (String scenario : Manhunt.getScenarioLoader().getAvailableScenarios().keySet()) {
            sender.sendMessage(LanguageTranslator.translate(
                    "scenariomenu.scenario-list-format",
                    LanguageTranslator.translate("scenario." + scenario))
            );
        }
    }

    @CommandMethod("manhunt scenarios toggle <scenario>")
    @CommandPermission("manhunt.scenarios")
    public void mScenarioToggle(
            @NotNull CommandSender sender,
            @Argument(value = "scenario", suggestions = "scenariotype") String scenario,
            @Flag("page") Integer page
    ) {
        if(page == null || page < 1)
            page = 1;

        // Check if game is started
        if (GameManager.getGame().isGameStarted()) {
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.no-change-ingame"));
            return;
        }

        // Check if scenario is available
        if(!Manhunt.getScenarioLoader().getAvailableScenarios().containsKey(scenario)) {
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-unavailable", scenario));
            return;
        }

        // Scenario is available
        if (GameManager.getGame().getActiveScenarios().contains(scenario)) {
            //Scenario is enabled, disable it!
            GameManager.getGame().getActiveScenarios().remove(scenario);
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-disabled", scenario));
        } else {
            //Scenario is disabled, enable it!
            GameManager.getGame().getActiveScenarios().add(scenario);
            sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-enabled", scenario));
        }

        if (sender instanceof Player) {
            ScenarioMenu.displayMenu((Player) sender, page);
        }
    }

}
