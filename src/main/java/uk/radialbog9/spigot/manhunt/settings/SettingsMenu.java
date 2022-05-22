/*
 * Copyright (c) 2020-2022 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.settings;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.Utils;

/**
 * Class for settings menu
 */
public class SettingsMenu {
    /**
     * Displays settings menu to player
     * @param p Player the player
     */
    public static void displayMenu(Player p) {
        p.sendMessage(LanguageTranslator.translate("settingsmenu.title"));
        String headStartTickCross = ManhuntSettings.isHeadStartEnabled() ?
                LanguageTranslator.translate("settingsmenu.enabled") :
                LanguageTranslator.translate("settingsmenu.disabled");
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                LanguageTranslator.translate("settingsmenu.options.head-start", headStartTickCross),
                "/manhunt settings headstarttoggle",
                LanguageTranslator.translate("settingsmenu.click-to-toggle")
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                LanguageTranslator.translate("settingsmenu.options.head-start-timer", String.valueOf(ManhuntSettings.getHeadStartTime())),
                "/manhunt settings headstarttime ",
                LanguageTranslator.translate("settingsmenu.click-to-change")
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                LanguageTranslator.translate("settingsmenu.options.add-runner"),
                "/manhunt runner ",
                LanguageTranslator.translate("settingsmenu.click-to-add")
        ));
        p.spigot().sendMessage(Utils.genTextComponentSuggestCommand(
                LanguageTranslator.translate("settingsmenu.options.add-hunter"),
                "/manhunt hunter ",
                LanguageTranslator.translate("settingsmenu.click-to-add")
        ));
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                LanguageTranslator.translate("settingsmenu.options.scenarios"),
                "/manhunt scenarios",
                LanguageTranslator.translate("settingsmenu.click-to-change")
        ));
        p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                LanguageTranslator.translate("settingsmenu.options.start-game"),
                "/manhunt start",
                LanguageTranslator.translate("settingsmenu.click-to-start")
        ));
    }
}
