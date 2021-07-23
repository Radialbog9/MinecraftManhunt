/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.kits.Kit;
import uk.radialbog9.spigot.manhunt.kits.KitType;

import java.util.ArrayList;

/**
 * Utilities that are used throughout the plugin
 */
@SuppressWarnings({"ConstantConditions", "unused"})
public class Utils {

    private static final String[] manhuntPermissions = {
            "manhunt.add",
            "manhunt.remove",
            "manhunt.start",
            "manhunt.stop",
            "manhunt.list",
            "manhunt.spectate",
            "manhunt.admin",
            "manhunt.settings"
    };

    /**
     * Shorter way to get chat message colors
     * @param msg String Chat message
     * @return String Decoded message
     */
    public static String getMsgColor(String msg) {
        //Get colored message and return it
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Broadcasts message to all players
     * @param msg Message
     */
    public static void broadcastServerMessage(String msg) {
        String msgSend = Utils.getMsgColor(msg);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(msgSend);
        }
    }

    /**
     * Checks for all manhunt permissions
     * @param s CommandSender
     * @return boolean if sender has no manhunt permissions
     */
    public static boolean hasNoManhuntPermissions(CommandSender s) {
        boolean hasNoMHP = true;
        for(String perm : manhuntPermissions) {
            if(s.hasPermission(perm)) hasNoMHP = false;
        }
        return hasNoMHP;
    }

    /**
     * Resets the game
     * @deprecated New event method
     */
    @Deprecated
    public static void resetGame() {
        //set all players to spectator
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (ManhuntVars.isRunner(p) || ManhuntVars.isHunter(p)) p.setGameMode(GameMode.SPECTATOR);
        }
        //reset runners and hunters
        ManhuntVars.removeAllHunters();
        ManhuntVars.removeAllRunners();
        //fully end game
        ManhuntVars.setGameStarted(false);
    }

    /**
     * Generate a text component with hover and run command click event
     * @param text String contents of message
     * @param command String command to run
     * @param hover String hover
     * @return TextComponent the generated text component
     */
    public static TextComponent genTextComponentRunCommand(@NotNull String text, @NotNull String command, String hover) {
        TextComponent tc = new TextComponent(getMsgColor(text));
        if(hover != null) tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getMsgColor(hover))));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return tc;
    }
    /**
     * Generate a text component and run command click event
     * @param text String contents of message
     * @param command String command to run
     * @return TextComponent the generated text component
     */
    public static TextComponent genTextComponentRunCommand(@NotNull String text, @NotNull String command) {
        return genTextComponentRunCommand(text, command, null);
    }

    /**
     * Generate a text component with hover and run suggest command click event
     * @param text String contents of message
     * @param command String command to run
     * @param hover String hover
     * @return TextComponent the generated text component
     */
    public static TextComponent genTextComponentSuggestCommand(@NotNull String text, @NotNull String command, String hover) {
        TextComponent tc = new TextComponent(getMsgColor(text));
        if(hover != null) tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getMsgColor(hover))));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        return tc;
    }
    /**
     * Generate a text component and run suggest command click event
     * @param text String contents of message
     * @param command String command to run
     * @return TextComponent the generated text component
     */
    public static TextComponent genTextComponentSuggestCommand(@NotNull String text, @NotNull String command) {
        return genTextComponentSuggestCommand(text, command, null);
    }

    /**
     * Retrieves hunter kits from the config
     * @see Kit
     * @return ArrayList&lt;Kit&gt; the kit list
     */
    public static ArrayList<Kit> getHunterKits() {
        ArrayList<Kit> kits = new ArrayList<>();
        ConfigurationSection hunterKitsSection = Manhunt.getInstance().getConfig().getConfigurationSection("kits.hunters");
        for (String key : hunterKitsSection.getKeys(false)) {
            ConfigurationSection kitsec = hunterKitsSection.getConfigurationSection(key);
            ArrayList<ItemStack> itemStacks = new ArrayList<>();
            for (String stack : kitsec.getConfigurationSection("items").getKeys(false)) {
                int amount = Math.min(kitsec.getConfigurationSection(stack).getInt("quantity"), 64);
                ItemStack itemStack = new ItemStack(Material.getMaterial(kitsec.getConfigurationSection(stack).getString("item")), amount);
                itemStacks.add(itemStack);
            }
            Kit thisKit = new Kit(key, KitType.HUNTER, itemStacks);
        }
        return kits;
    }

    /**
     * Retrieves runner kits from the config
     * @see Kit
     * @return ArrayList&lt;Kit&gt; the kit list
     */
    public static ArrayList<Kit> getRunnerKits() {
        ArrayList<Kit> kits = new ArrayList<>();
        ConfigurationSection runnerKitsSection = Manhunt.getInstance().getConfig().getConfigurationSection("kits.runners");
        for (String key : runnerKitsSection.getKeys(false)) {
            ConfigurationSection kitsec = runnerKitsSection.getConfigurationSection(key);
            ArrayList<ItemStack> itemStacks = new ArrayList<>();
            for (String stack : kitsec.getConfigurationSection("items").getKeys(false)) {
                int amount = Math.min(kitsec.getConfigurationSection(stack).getInt("quantity"), 64);
                ItemStack itemStack = new ItemStack(Material.getMaterial(kitsec.getConfigurationSection(stack).getString("item")), amount);
                itemStacks.add(itemStack);
            }
            Kit thisKit = new Kit(key, KitType.RUNNER, itemStacks);
        }
        return kits;
    }
}
