package uk.radialbog9.spigot.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManhuntCommand implements CommandExecutor {

    /**
     * Main Manhunt command
     * @param sender CommandSender command sender
     * @param cmd Command command
     * @param label String label
     * @param args String[] arguments
     * @return boolean always true
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a No command specified. Type /manhunt help for command help."));
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Command Help:"));
            sender.sendMessage(Utils.getMsgColor("&a- &c/manhunt help &r&a- &r&eShows help"));
            if(Utils.hasNoManhuntPermissions(sender)) sender.sendMessage(Utils.getMsgColor("&a- You do not have permission for any sub-commands."));
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
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Not enough arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt hunter <player>"));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Too many arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt hunter <player>"));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        if(pl != null) {
                            //player exists
                            //check if already hunter
                            if(!ManhuntVars.isHunter(pl)) {
                                //player isn't hunter already - add them
                                if(ManhuntVars.isRunner(pl)) ManhuntVars.removeRunner(pl);
                                ManhuntVars.addHunter(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a hunter!"));
                                pl.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You are now a hunter!"));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a hunter!"));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + args[1] + "&r&a is not online!"));
                        }
                    } else {
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You can not change hunters while the game is ongoing."));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        } else if (args[0].equalsIgnoreCase("runner")) {
            if(sender.hasPermission("manhunt.add")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Not enough arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt runner <player>"));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Too many arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt runner <player>"));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        if(pl != null) {
                            //player exists
                            //check if already runner
                            if(!ManhuntVars.isRunner(pl)) {
                                //player isn't runner already - add them
                                if(ManhuntVars.isHunter(pl)) ManhuntVars.removeHunter(pl);
                                ManhuntVars.addRunner(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a runner!"));
                                pl.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You are now a runner!"));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a runner!"));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + args[1] + "&r&a is not online!"));
                        }
                    } else {
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You can not change runners while the game is ongoing."));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        } else if (args[0].equalsIgnoreCase("remove")) {
            if(sender.hasPermission("manhunt.remove")) {
                if(args.length < 2) {
                    //no player specified
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Not enough arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt runner <player>"));
                } else if (args.length > 2) {
                    //too many players specified
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Too many arguments!"));
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Usage: &c/manhunt runner <player>"));
                } else {
                    //player specified
                    //check if game is started
                    if(!ManhuntVars.isGameStarted()) {
                        //game is not started, we can change hunters and runners
                        //check if player exists
                        Player pl = Bukkit.getPlayer(args[1]);
                        if(pl != null) {
                            //player exists
                            //check if already runner
                            if(!ManhuntVars.isRunner(pl)) {
                                //player isn't runner already - add them
                                if(ManhuntVars.isHunter(pl)) ManhuntVars.removeHunter(pl);
                                ManhuntVars.addRunner(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a runner!"));
                                pl.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You are now a runner!"));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a runner!"));
                            }
                        } else {
                            //player does not exist/is not online
                            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + args[1] + "&r&a is not online!"));
                        }
                    } else {
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You can not change runners while the game is ongoing."));
                    }
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        } else if (args[0].equalsIgnoreCase("start")) {
            if(sender.hasPermission("manhunt.start")) {
                //can start game but game might be running
                //check if game is running
                if(!ManhuntVars.isGameStarted()) {
                    //game is not started, start it if there is enough players
                    if (ManhuntVars.getHunters().size() >= 1 && ManhuntVars.getRunners().size() >= 1) {
                        //enough players
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            //set gamemode of hunter/runner to survival or if spectator set to spectator
                            if(ManhuntVars.isHunter(p) || ManhuntVars.isRunner(p)) p.setGameMode(GameMode.SURVIVAL);
                            else p.setGameMode(GameMode.SPECTATOR);
                            //clear inventory of hunters and runners
                            if(ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) p.getInventory().clear();
                        }
                        //set game as started
                        ManhuntVars.setGameStarted(true);
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Game has started!"));
                    } else {
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The game must have at least 1 hunter and 1 runner!"));
                    }
                } else {
                    //game is started, throw error!
                    sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a A game is already in progress!"));
                }
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You do not have permission to do this!"));
            }
        }
        return true;
    }
}
