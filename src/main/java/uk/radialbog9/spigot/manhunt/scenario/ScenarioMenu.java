/*
 * Copyright (c) 2021 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows commercial use, distribution, modification, and licensed works, providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.utils.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.ManhuntVars;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ScenarioMenu {
    public static void displayMenu(Player p) {
        p.sendMessage(LanguageTranslator.translate("scenariomenu.title"));

        p.sendMessage(Utils.getMsgColor("&cWARNING: Scenarios DO NOT work! Don't try and use them."));

        String enabled = LanguageTranslator.translate("scenariomenu.enabled");
        String disabled = LanguageTranslator.translate("scenariomenu.disabled");
        String unavailable = LanguageTranslator.translate("scenariomenu.unavailable");
        String clickToEnable = LanguageTranslator.translate("scenariomenu.click-to-enable");
        String clickToDisable = LanguageTranslator.translate("scenariomenu.click-to-disable");

        ArrayList<ScenarioType> unavailables = new ArrayList<>(Arrays.asList(ScenarioType.values()));

        HashMap<ScenarioType, ScenarioBase> availables = Manhunt.getScenarioLoader().getAvailableScenarios();

        for (ScenarioType scenario : availables.keySet()) {
            unavailables.remove(scenario);
            boolean scenarioenabled = ManhuntVars.getScenarioList().contains(scenario);
            p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                    LanguageTranslator.translate("scenariomenu.display-format",
                            scenarioenabled ? enabled : disabled,
                            LanguageTranslator.translate("scenario." + scenario.toString())
                    ),
                    "/manhunt scenarios " + scenario,
                    scenarioenabled ? clickToDisable : clickToEnable
            ));
        }

        for(ScenarioType scenario : unavailables) {
            p.spigot().sendMessage(Utils.genTextComponentHoverOnly(
                    LanguageTranslator.translate("scenariomenu.display-format",
                            unavailable,
                            LanguageTranslator.translate("scenario." + scenario.toString())
                    ),
                    LanguageTranslator.translate("scenariomenu.not-available.generic-load-error")
            ));
        }
    }
}
