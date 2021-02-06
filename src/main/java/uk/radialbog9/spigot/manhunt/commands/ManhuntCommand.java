package uk.radialbog9.spigot.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.events.ManhuntGameEndEvent;
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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a No command specified. Type /manhunt help for command help."));
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
                        if(pl != null) {
                            //player exists
                            //check if already hunter
                            if(!ManhuntVars.isHunter(pl)) {
                                //player isn't hunter already - add them
                                if(ManhuntVars.isRunner(pl)) ManhuntVars.removeRunner(pl);
                                ManhuntVars.addHunter(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a hunter!")); //TODO
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-hunter")));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a hunter!")); //TODO
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
                        if(pl != null) {
                            //player exists
                            //check if already runner
                            if(!ManhuntVars.isRunner(pl)) {
                                //player isn't runner already - add them
                                if(ManhuntVars.isHunter(pl)) ManhuntVars.removeHunter(pl);
                                ManhuntVars.addRunner(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a runner!")); //TODO
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-runner")));
                            } else {
                                //already a hunter
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a runner!")); //TODO
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
                        if(pl != null) {
                            //player exists
                            //check if runner or hunter
                            if(ManhuntVars.isHunter(pl)) {
                                //set as spectator
                                ManhuntVars.removeHunter(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a spectator!")); //TODO
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-spectator")));
                            } else if(ManhuntVars.isRunner(pl)) {
                                ManhuntVars.removeRunner(pl);
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is now a spectator!")); //TODO
                                pl.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.now-spectator")));
                            } else {
                                //player is already spectator
                                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a The player &r&c" + pl.getDisplayName() + "&r&a is already a spectator!")); //TODO
                            }
                        } else {
                            //player does not exist/is not online
                            String a = Manhunt.getInstance().getConfig().getString("language.player-not-online");
                            if(a != null) sender.sendMessage(Utils.getMsgColor(String.format(a, args[0])));
                        }
                    } else {
                        //cannot remove players in a game
                        sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a You can not remove players while the game is ongoing.")); //TODO
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
                        //enough players
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            if(ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) {
                                //HUNTERS AND RUNNERS
                                //set gamemode to survival
                                p.setGameMode(GameMode.SURVIVAL);
                                //clear inventory
                                p.getInventory().clear();
                                //set health, hunger and XP
                                p.setHealth(20);
                                p.setLevel(0);
                                p.setExp(0);
                                p.setFoodLevel(20);
                                //TP to spawn
                                p.teleport(p.getWorld().getSpawnLocation());
                                if(ManhuntVars.isHunter(p)) {
                                    //give blindness and weakness for 5 seconds
                                    if(Manhunt.getInstance().getConfig().getBoolean("head-start.enabled")) {
                                        new PotionEffect(PotionEffectType.WEAKNESS, Manhunt.getInstance().getConfig().getInt("head-start.length") * 20, 10, false, false).apply(p);
                                        new PotionEffect(PotionEffectType.BLINDNESS, Manhunt.getInstance().getConfig().getInt("head-start.length") * 20, 10, false, false).apply(p);
                                    }
                                    //give player compass
                                    p.getInventory().addItem(new ItemStack(Material.COMPASS));
                                }
                            } else {
                                //SPECTATORS
                                //Set spectator gamemode
                                p.setGameMode(GameMode.SPECTATOR);
                            }
                        }
                        //set game as started
                        ManhuntVars.setGameStarted(true);
                        Utils.broadcastServerMessage(Manhunt.getInstance().getConfig().getString("language.game-started"));
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
                //loop all players for list and spectator count
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
                if(ManhuntVars.isGameStarted()) sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.game-is-started")));
                else sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.game-is-stopped")));
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a There are &r&c" + hunterCount + "&r&a hunters, &r&c" + runnerCount + "&r&a runners, and &r&c" + spectatorCount + "&r&a spectators.")); //TODO
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Hunters: " + hunters)); //TODO
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Runners: " + runners)); //TODO
                sender.sendMessage(Utils.getMsgColor("&6[Manhunt]&r&a Spectators: " + spectators)); //TODO
            } else {
                //no perm
                sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.no-permission")));
            }
        } else {
            //unknown subcommand
            sender.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.unknown-subcommand")));
        }
        return true;
    }
}
