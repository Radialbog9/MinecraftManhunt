/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */
package uk.radialbog9.spigot.manhunt.commands;

import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioMenu;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioType;
import uk.radialbog9.spigot.manhunt.settings.ManhuntSettings;
import uk.radialbog9.spigot.manhunt.settings.SettingsMenu;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

public class ManhuntCommand implements CommandExecutor {

    /**
     * Main Manhunt command
     * @param sender CommandSender command sender
     * @param cmd Command command
     * @param label String label
     * @param args String[] arguments
     * @return boolean always true (argument and permission messages handled in command)
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(LanguageTranslator.translate("no-command-specified"));
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(LanguageTranslator.translate("command-help"));
            sender.sendMessage(LanguageTranslator.translate(
                    "help-format",
                    "/manhunt help",
                    LanguageTranslator.translate("help.help")
            ));
            if(Utils.hasNoManhuntPermissions(sender)) sender.sendMessage(LanguageTranslator.translate("no-subcommand-perm"));
            if(sender.hasPermission("manhunt.add"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt hunter " + LanguageTranslator.translate("player-placeholder"),
                        LanguageTranslator.translate("help.hunter")
                ));
            if(sender.hasPermission("manhunt.add"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt runner " + LanguageTranslator.translate("player-placeholder"),
                        LanguageTranslator.translate("help.runner")
                ));
            if(sender.hasPermission("manhunt.remove"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt remove " + LanguageTranslator.translate("player-placeholder"),
                        LanguageTranslator.translate("help.remove")
                ));
            if(sender.hasPermission("manhunt.start"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt start",
                        LanguageTranslator.translate("help.start")
                ));
            if(sender.hasPermission("manhunt.stop"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt stop",
                        LanguageTranslator.translate("help.stop")
                ));
            if(sender.hasPermission("manhunt.list"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt list",
                        LanguageTranslator.translate("help.list")
                ));
            if(sender.hasPermission("manhunt.revive"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/manhunt revive " + LanguageTranslator.translate("player-placeholder"),
                        LanguageTranslator.translate("help.revive")
                ));
            if(sender.hasPermission("manhunt.spectate"))
                sender.sendMessage(LanguageTranslator.translate(
                        "help-format",
                        "/spectate " + LanguageTranslator.translate("player-placeholder"),
                        LanguageTranslator.translate("help.spectate")
                ));
        } else if (args[0].equalsIgnoreCase("hunter")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(LanguageTranslator.translate("not-enough-args"));
                    sender.sendMessage(LanguageTranslator.translate(
                            "usage",
                            "/manhunt hunter " + LanguageTranslator.translate("player-placeholder")
                    ));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                    sender.sendMessage(LanguageTranslator.translate(
                            "usage",
                            "/manhunt hunter " + LanguageTranslator.translate("player-placeholder")
                    ));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.isVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);
                        if(playerExists && canSee) {
                            //player exists
                            //check if already hunter
                            if(!ManhuntVars.isHunter(pl)) {
                                //player isn't hunter already - add them
                                if(ManhuntVars.isRunner(pl)) ManhuntVars.removeRunner(pl);
                                ManhuntVars.addHunter(pl);
                                sender.sendMessage(LanguageTranslator.translate("p-now-hunter", pl.getDisplayName()));
                                pl.sendMessage(LanguageTranslator.translate("now-hunter"));
                            } else {
                                //already a hunter
                                sender.sendMessage(LanguageTranslator.translate("p-already-hunter", pl.getDisplayName()));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(LanguageTranslator.translate(
                                    "player-not-online",
                                    args[1]
                            ));
                        }
                    } else {
                        //cannot change hunters in game
                        sender.sendMessage(LanguageTranslator.translate("no-change-hunter-in-game"));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("runner")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(LanguageTranslator.translate("not-enough-args"));
                    sender.sendMessage(LanguageTranslator.translate(
                            "usage",
                            "/manhunt runner " + LanguageTranslator.translate("player-placeholder")
                    ));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                    sender.sendMessage(LanguageTranslator.translate(
                            "usage",
                            "/manhunt runner " + LanguageTranslator.translate("player-placeholder")
                    ));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.isVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);
                        if(playerExists && canSee) {
                            //player exists
                            //check if already runner
                            if(!ManhuntVars.isRunner(pl)) {
                                //player isn't runner already - add them
                                if(ManhuntVars.isHunter(pl)) ManhuntVars.removeHunter(pl);
                                ManhuntVars.addRunner(pl);
                                sender.sendMessage(LanguageTranslator.translate("p-now-runner", pl.getDisplayName()));
                                pl.sendMessage(LanguageTranslator.translate("now-runner"));
                            } else {
                                //already a runner
                                sender.sendMessage(LanguageTranslator.translate("p-already-runner", pl.getDisplayName()));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(LanguageTranslator.translate("player-not-online", args[1]));
                        }
                    } else {
                        //cannot change runners in game
                        sender.sendMessage(LanguageTranslator.translate("no-change-runner-in-game"));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("remove")) {
            if(sender.hasPermission("manhunt.remove")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(LanguageTranslator.translate("not-enough-args"));
                    sender.sendMessage(LanguageTranslator.translate("usage", "/manhunt remove <player>"));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                    sender.sendMessage(LanguageTranslator.translate("usage", "/manhunt remove <player>"));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);

                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.isVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);

                        if(playerExists && canSee) {
                            //player exists
                            //check if runner or hunter
                            if(ManhuntVars.isHunter(pl)) {
                                //set as spectator
                                ManhuntVars.removeHunter(pl);
                                sender.sendMessage(LanguageTranslator.translate("p-now-spectator", pl.getDisplayName()));
                                pl.sendMessage(LanguageTranslator.translate("now-spectator"));
                            } else if(ManhuntVars.isRunner(pl)) {
                                ManhuntVars.removeRunner(pl);
                                sender.sendMessage(LanguageTranslator.translate("p-now-spectator", pl.getDisplayName()));
                                pl.sendMessage(LanguageTranslator.translate("now-spectator"));
                            } else {
                                //player is already spectator
                                sender.sendMessage(LanguageTranslator.translate("p-already-spectator", pl.getDisplayName()));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(LanguageTranslator.translate("player-not-online", args[0]));
                        }
                    } else {
                        //cannot remove players in a game
                        sender.sendMessage(LanguageTranslator.translate("no-remove-in-game"));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("start")) {
            if(sender.hasPermission("manhunt.start")) {
                //can start game but game might be running
                //check if game is running
                if(!ManhuntVars.isGameStarted()) {
                    //game is not started, start it if there is enough players
                    if (ManhuntVars.getHunters().size() >= 1 && ManhuntVars.getRunners().size() >= 1) {
                        GameManager.startGame();
                    } else {
                        sender.sendMessage(LanguageTranslator.translate("too-few-players"));
                    }
                } else {
                    //game is started, throw error!
                    sender.sendMessage(LanguageTranslator.translate("game-already-in-progress"));
                }
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("stop")) {
            if(sender.hasPermission("manhunt.stop")) {
                GameManager.endGame(GameEndCause.ENDED_PREMATURELY);
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("list")) {
            if(sender.hasPermission("manhunt.list")) {
                //init vars
                StringBuilder hunters = new StringBuilder();
                StringBuilder runners = new StringBuilder();
                StringBuilder spectators = new StringBuilder();
                int hunterCount = ManhuntVars.getHunters().size();
                int runnerCount = ManhuntVars.getRunners().size();
                int spectatorCount = 0;

                if(sender instanceof Player && ManhuntVars.isVanishEnabled()) {
                    //Vanish support detected and sender is player
                    Player pl = (Player) sender;
                    //Generate player list
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (ManhuntVars.isHunter(p)) {
                            if (hunters.toString().equals("")) hunters = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else hunters.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                        } else if (ManhuntVars.isRunner(p)) {
                            if (runners.toString().equals("")) runners = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else runners.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                        } else {
                            if(!VanishAPI.canSee(pl, p)) continue;
                            if (spectators.toString().equals("")) spectators = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else spectators.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                            spectatorCount ++;
                        }
                    }
                } else {
                    //Vanish support not detected or sender isn't player
                    //Generate player list
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (ManhuntVars.isHunter(p)) {
                            if (hunters.toString().equals("")) hunters = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else hunters.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                        } else if (ManhuntVars.isRunner(p)) {
                            if (runners.toString().equals("")) runners = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else runners.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                        } else {
                            if (spectators.toString().equals("")) spectators = new StringBuilder("&r&c" + p.getDisplayName() + "&r&a");
                            else spectators.append(", &r&c").append(p.getDisplayName()).append("&r&a");
                            spectatorCount ++;
                        }
                    }
                }
                sender.sendMessage(ManhuntVars.isGameStarted() ?
                        LanguageTranslator.translate("game-is-started")
                        : LanguageTranslator.translate("game-is-stopped"));
                sender.sendMessage(LanguageTranslator.translate("list-count", String.valueOf(hunterCount), String.valueOf(runnerCount), String.valueOf(spectatorCount)));
                sender.sendMessage(LanguageTranslator.translate("hunter-list", hunters.toString()));
                sender.sendMessage(LanguageTranslator.translate("runner-list", runners.toString()));
                sender.sendMessage(LanguageTranslator.translate("spectator-list", spectators.toString()));
            } else {
                //no perm
                sender.sendMessage(LanguageTranslator.translate("no-permission"));
            }
        } else if (args[0].equalsIgnoreCase("settings")) {
            if(sender.hasPermission("manhunt.settings")) {
                if (args.length == 1) {
                    if(sender instanceof Player) {
                        SettingsMenu.displayMenu((Player) sender);
                    } else {
                        sender.sendMessage(Utils.getMsgColor("&cYou can't use the settings menu as console!"));
                    }
                } else if (args[1].equalsIgnoreCase("headstarttoggle")) {
                    if(ManhuntSettings.isHeadStartEnabled()) {
                        //head start enabled so disable it
                        ManhuntSettings.setHeadStartEnabled(false);
                        sender.sendMessage(LanguageTranslator.translate("head-start-disabled"));
                    } else {
                        //head start disabled so enable it
                        ManhuntSettings.setHeadStartEnabled(true);
                        sender.sendMessage(LanguageTranslator.translate("head-start-enabled"));
                    }
                    if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
                } else if (args[1].equalsIgnoreCase("headstarttime")) {
                    if(args.length == 2) {
                        //no time has been specified
                        sender.sendMessage(LanguageTranslator.translate("not-enough-args"));
                    } else if (args.length > 3) {
                        //too many args
                        sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                    } else {
                        //check if integer
                        try {
                            int time = Integer.parseInt(args[2]);
                            if(time > 0) {
                                ManhuntSettings.setHeadStartTime(time);
                                sender.sendMessage(LanguageTranslator.translate("head-start-timer-set", String.valueOf(time)));
                            }
                            else {
                                sender.sendMessage(LanguageTranslator.translate("invalid-integer"));
                            }
                        } catch (NumberFormatException e) {
                            sender.sendMessage(LanguageTranslator.translate("invalid-integer"));
                        }
                    }
                    if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
                }
            }
        } else if (args[0].equalsIgnoreCase("revive")) {
            if(args.length < 2) {
                //no player specified
                sender.sendMessage(LanguageTranslator.translate("not-enough-args"));
                sender.sendMessage(LanguageTranslator.translate(
                        "usage",
                        "/manhunt revive " + LanguageTranslator.translate("player-placeholder")
                ));
            } else if (args.length > 2) {
                //too many players specified
                sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                sender.sendMessage(LanguageTranslator.translate(
                        "usage",
                        "/manhunt revive " + LanguageTranslator.translate("player-placeholder")
                ));
            } else {
                //player is specified
                //player specified
                //check if game is started
                if(ManhuntVars.isGameStarted()) {
                    //game is not started, we can
                    //check if player exists
                    Player pl = Bukkit.getPlayer(args[1]);

                    boolean playerExists = pl != null;
                    boolean canSee = true;
                    if(playerExists && ManhuntVars.isVanishEnabled() && sender instanceof Player)
                        canSee = VanishAPI.canSee((Player) sender, pl);

                    if (playerExists && canSee) {
                        if(ManhuntVars.getPreviousRunners().contains(pl)) {
                            pl.teleport(pl.getBedSpawnLocation() != null ? pl.getBedSpawnLocation() : pl.getWorld().getSpawnLocation());
                            ManhuntVars.addRunner(pl);
                            pl.setGameMode(GameMode.SURVIVAL);
                            //broadcast messages
                            pl.sendMessage(LanguageTranslator.translate("you-revived"));
                            sender.sendMessage(LanguageTranslator.translate("revived-player", pl.getDisplayName()));
                            Utils.broadcastServerMessage(LanguageTranslator.translate(
                                    "player-revived-bc",
                                    pl.getDisplayName(),
                                    String.valueOf(ManhuntVars.getRunners().size()
                            )));
                        } else {
                            sender.sendMessage(LanguageTranslator.translate("cannot-revive-player", pl.getDisplayName()));
                        }
                    } else {
                        sender.sendMessage(LanguageTranslator.translate("player-not-online"));
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("scenarios")) {
            if (sender instanceof Player) {
                if (Manhunt.areScenariosLoaded()) {
                    if(!ManhuntVars.isGameStarted()) {
                        if (args.length == 1) {
                            //no argument was given, display the menu
                            ScenarioMenu.displayMenu((Player) sender);
                        } else if (args.length == 2) {
                            ScenarioType value;
                            try {
                                value = ScenarioType.valueOf(args[1]);
                            } catch (IllegalArgumentException ignored) { value = null; }

                            if (value != null) {
                                //scenario is a value
                                if (Manhunt.getScenarioLoader().getAvailableScenarios().containsKey(value)) {
                                    //Scenario is available
                                    if (ManhuntVars.getScenarioList().contains(value)) {
                                        //Scenario is enabled, disable it!
                                        ManhuntVars.getScenarioList().remove(value);
                                        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-disabled", value.toString()));
                                    } else {
                                        //Scenario is disabled, enable it!
                                        ManhuntVars.getScenarioList().add(value);
                                        sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-enabled", value.toString()));
                                    }
                                    ScenarioMenu.displayMenu((Player) sender);
                                } else {
                                    sender.sendMessage(LanguageTranslator.translate("scenariomenu.scenario-unavailable", value.toString()));
                                }
                            } else {
                                sender.sendMessage(LanguageTranslator.translate("invalid-arg"));
                                sender.sendMessage(LanguageTranslator.translate(
                                        "usage",
                                        "/manhunt scenario [scenario]"
                                ));
                            }
                        } else {
                            sender.sendMessage(LanguageTranslator.translate("too-many-args"));
                            sender.sendMessage(LanguageTranslator.translate(
                                    "usage",
                                    "/manhunt scenario [scenario]"
                            ));
                        }
                    } else {
                        sender.sendMessage(LanguageTranslator.translate("scenariomenu.no-change-ingame"));
                    }
                } else {
                    sender.sendMessage(LanguageTranslator.translate("scenariomenu.could-not-load"));
                }
            } else {
                sender.sendMessage(LanguageTranslator.translate("no-run-console"));
            }
        } else {
            //unknown subcommand
            sender.sendMessage(LanguageTranslator.translate("unknown-subcommand"));
        }
        return true;
    }
}
