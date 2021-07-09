/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPLv3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.Utils;

@SuppressWarnings("ConstantConditions")
public class SettingsMenu {
    /**
     * Displays settings menu to player
     * @param p Player the player
     */
    public static void displayMenu(Player p) {
        p.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.title")));
        String headStartTickCross = ManhuntSettings.getHeadStartEnabled() ?
                Manhunt.getInstance().getConfig().getString("language.settingsmenu.enabled") :
                Manhunt.getInstance().getConfig().getString("language.settingsmenu.disabled");
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start"), headStartTickCross)),
                "/manhunt settings headstarttoggle",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-toggle"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(String.format(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start-timer"), ManhuntSettings.getHeadStartTime())),
                "/manhunt settings headstarttime " + ManhuntSettings.getHeadStartTime(),
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-change"))
        ));

        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.start-game")),
                "/manhunt start",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-start"))
        ));
    }
}
