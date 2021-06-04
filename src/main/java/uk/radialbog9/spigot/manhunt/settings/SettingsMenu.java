/*
 * Copyright (c) 2021 Radialbog9 and contributors.
 * You are allowed to use this code under the GPLv3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.Utils;

public class SettingsMenu {
    public void displayMenu(Player p) {
        p.sendMessage(Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.title")));
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start")),
                "/manhunt settings headstart toggle",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-toggle"))
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.head-start-timer")),
                "/manhunt settings headstarttimer " + ManhuntSettings.getHeadStartTime(),
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-change"))
        ));

        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.options.start-game")),
                "/manhunt start",
                Utils.getMsgColor(Manhunt.getInstance().getConfig().getString("language.settingsmenu.click-to-start"))
        ));
    }
}
