/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
            "manhunt.settings",
            "manhunt.revive"
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
     * @param text contents of message
     * @param command command to run
     * @return The generated text component
     */
    public static TextComponent genTextComponentSuggestCommand(@NotNull String text, @NotNull String command) {
        return genTextComponentSuggestCommand(text, command, null);
    }

    /**
     * Generate a text component with hover
     * @param text String contents of message
     * @param hover String hover
     * @return TextComponent the generated text component
     */
    public static TextComponent genTextComponentHoverOnly(@NotNull String text, @NotNull String hover) {
        TextComponent tc = new TextComponent(getMsgColor(text));
        if(hover != null) tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getMsgColor(hover))));
        return tc;
    }

    /**
     * Generates random integers between 2 integers (inclusive)
     * @param min minimum (inclusive)
     * @param max maximum (inclusive)
     * @return Random integer between min and max
     */
    public static int getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Maximum value must be greater than minimum value!");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Get classes in specific package in a jar
     * @param jarFile The jar file to search in
     * @param packageName Name of the package to get from
     * @return Set of classes in the package
     */
    public static Set<Class<?>> getClasses(File jarFile, String packageName) {
        HashSet<Class<?>> classes = new HashSet<>();
        try {
            JarFile file = new JarFile(jarFile);
            Enumeration<JarEntry> entry = file.entries();
            while (entry.hasMoreElements()) {
                JarEntry jarEntry = entry.nextElement();
                String name = jarEntry.getName().replace("/", ".");
                if (!name.startsWith(packageName) || !name.endsWith(".class"))
                    continue;
                classes.add(Class.forName(name.substring(0, name.length() - 6)));
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

}
