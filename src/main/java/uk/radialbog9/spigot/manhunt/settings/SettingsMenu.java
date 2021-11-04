/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.Utils;

/**
 * Class for settings menu
 */
@SuppressWarnings("ConstantConditions")
public class SettingsMenu {
    /**
     * Displays settings menu to player
     * @param p Player the player
     */
    public static void displayMenu(Player p) {
        p.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.title")));
        String headStartTickCross = ManhuntSettings.isHeadStartEnabled() ?
                Manhunt.getInstance().getConfig().getString("language.settingsmenu.enabled") :
                Manhunt.getInstance().getConfig().getString("language.settingsmenu.disabled");
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start"), headStartTickCross)),
                "/manhunt settings headstarttoggle",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-toggle"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start-timer"), ManhuntSettings.getHeadStartTime())),
                "/manhunt settings headstarttime ",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-change"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.add-runner")),
                "/manhunt runner ",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-add"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.add-hunter")),
                "/manhunt hunter ",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-add"))
        ));
        if(false) { //alternative to commenting out code
            p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                    Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.scenarios")),
                    "/manhunt scenarios",
                    Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-change"))
            ));
        }
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.start-game")),
                "/manhunt start",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-start"))
        ));
    }
}
