/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPLv3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */
package uk.radialbog9.spigot.manhunt.commands;

import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameStartEvent;
import uk.radialbog9.spigot.manhunt.settings.SettingsMenu;
import uk.radialbog9.spigot.manhunt.utils.GameEndCause;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@SuppressWarnings("ConstantConditions")
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
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a No command specified. Type /manhunt help for command help.")); //TODO
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Command Help:"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt help &r&a- &r&eShows help"));
            if(Utils.hasNoManhuntPermissions(sender)) sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-subcommand-perm")));
            if(sender.hasPermission("manhunt.add")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt hunter <player> &r&a- &r&eSets a player to hunter"));
            if(sender.hasPermission("manhunt.add")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt runner <player> &r&a- &r&eSets a player to runner"));
            if(sender.hasPermission("manhunt.remove")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt remove <player> &r&a- &r&eRemoves a player from hunter and runner"));
            if(sender.hasPermission("manhunt.start")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt start &r&a- &r&eStarts the manhunt"));
            if(sender.hasPermission("manhunt.stop")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt stop &r&a- &r&eStops the game"));
            if(sender.hasPermission("manhunt.list")) sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt list &r&a- &r&eList all runners and hunters"));
            if(sender.hasPermission("manhunt.spectate")) sender.sendMessage(Utils.getMsgColor("&a- &c/spectate <player> &r&a- &r&eSpectates players"));
        } else if (args[0].equalsIgnoreCase("hunter")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.not-enough-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt hunter <player>")));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.too-many-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt hunter <player>")));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.getVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);
                        if(playerExists && canSee) {
                            //player exists
                            //check if already hunter
                            if(!ManhuntVars.isHunter(pl)) {
                                //player isn't hunter already - add them
                                if(ManhuntVars.isRunner(pl)) ManhuntVars.removeRunner(pl);
                                ManhuntVars.addHunter(pl);
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-now-hunter"), pl.getDisplayName())));
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-hunter")));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-already-hunter"), pl.getDisplayName())));
                            }
                        } else {
                            //player does not exist/is not online
                            String a = Manhunt.getInstance().getConfig().getString("language.player-not-online");
                            if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, args[1])));
                        }
                    } else {
                        //cannot change hunters in game
                        sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-change-hunter-in-game")));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else if (args[0].equalsIgnoreCase("runner")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.not-enough-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt runner <player>")));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.too-many-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt runner <player>")));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.getVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);
                        if(playerExists && canSee) {
                            //player exists
                            //check if already runner
                            if(!ManhuntVars.isRunner(pl)) {
                                //player isn't runner already - add them
                                if(ManhuntVars.isHunter(pl)) ManhuntVars.removeHunter(pl);
                                ManhuntVars.addRunner(pl);
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-now-runner"), pl.getDisplayName())));
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-runner")));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-already-hunter"), pl.getDisplayName())));
                            }
                        } else {
                            //player does not exist/is not online
                            String a = Manhunt.getInstance().getConfig().getString("language.player-not-online");
                            if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, args[1])));
                        }
                    } else {
                        //cannot change runners in game
                        sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-change-runner-in-game")));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else if (args[0].equalsIgnoreCase("remove")) {
            if(sender.hasPermission("manhunt.remove")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.not-enough-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt remove <player>")));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.too-many-args")));
                    String a = Manhunt.getInstance().getConfig().getString("language.usage");
                    if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, "/manhunt remove <player>")));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);

                        boolean playerExists = pl != null;
                        boolean canSee = true;
                        if(playerExists && ManhuntVars.getVanishEnabled() && sender instanceof Player)
                            canSee = VanishAPI.canSee((Player) sender, pl);

                        if(playerExists && canSee) {
                            //player exists
                            //check if runner or hunter
                            if(ManhuntVars.isHunter(pl)) {
                                //set as spectator
                                ManhuntVars.removeHunter(pl);
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-now-spectator"), pl.getDisplayName())));
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-spectator")));
                            } else if(ManhuntVars.isRunner(pl)) {
                                ManhuntVars.removeRunner(pl);
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-now-spectator"), pl.getDisplayName())));
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-spectator")));
                            } else {
                                //player is already spectator
                                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.p-already-spectator"), pl.getDisplayName())));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.player-not-online"), args[0])));
                        }
                    } else {
                        //cannot remove players in a game
                        sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-remove-in-game")));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else if (args[0].equalsIgnoreCase("start")) {
            if(sender.hasPermission("manhunt.start")) {
                //can start game but game might be running
                //check if game is running
                if(!ManhuntVars.isGameStarted()) {
                    //game is not started, start it if there is enough players
                    if (ManhuntVars.getHunters().size() >= 1 && ManhuntVars.getRunners().size() >= 1) {
                        Event event = new ManhuntGameStartEvent();
                        Bukkit.getServer().getPluginManager().callEvent(event);
                    } else {
                        sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.too-few-players")));
                    }
                } else {
                    //game is started, throw error!
                    sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.game-already-in-progress")));
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else if (args[0].equalsIgnoreCase("stop")) {
            if(sender.hasPermission("manhunt.stop")) {
                ManhuntGameEndEvent event = new ManhuntGameEndEvent(GameEndCause.ENDED_PREMATURELY);
                Bukkit.getServer().getPluginManager().callEvent(event);
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
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

                if(sender instanceof Player && ManhuntVars.getVanishEnabled()) {
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
                if(ManhuntVars.isGameStarted()) sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.game-is-started")));
                else sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.game-is-stopped")));
                sender.sendMessage(Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.list-count"), hunterCount, runnerCount, spectatorCount)));
                sender.sendMessage(Utils.getMsgColor(String.format(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.hunter-list")), hunters)));
                sender.sendMessage(Utils.getMsgColor(String.format(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.runner-list")), runners)));
                sender.sendMessage(Utils.getMsgColor(String.format(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.spectator-list")), spectators)));
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else if (args[0].equalsIgnoreCase("settings")) {
            if(sender instanceof Player) SettingsMenu.displayMenu((Player) sender);
        } else {
            //unknown subcommand
            sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.unknown-subcommand")));
        }
        return true;
    }
}
