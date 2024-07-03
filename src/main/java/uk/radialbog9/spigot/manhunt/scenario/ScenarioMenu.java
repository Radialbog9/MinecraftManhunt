/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.HashMap;

public class ScenarioMenu {
    public static void displayMenu(Player p, int page) {
        p.sendMessage(LanguageTranslator.translate("scenariomenu.title"));

        String enabled = LanguageTranslator.translate("scenariomenu.enabled");
        String disabled = LanguageTranslator.translate("scenariomenu.disabled");
        String clickToEnable = LanguageTranslator.translate("scenariomenu.click-to-enable");
        String clickToDisable = LanguageTranslator.translate("scenariomenu.click-to-disable");

        HashMap<String, Class<?>> availables = Manhunt.getScenarioLoader().getAvailableScenarios();

        int totalValues = availables.size();
        int totalPages = (int) Math.ceil((double) totalValues / 5);
        if (page < 1 || page > totalPages) {
            p.sendMessage(LanguageTranslator.translate("scenariomenu.invalid-page"));
            return;
        }

        for (String scenario : availables.keySet()) {
            boolean scenarioenabled = GameManager.getGame().getActiveScenarios().contains(scenario);
            String scenarioenabledstring = scenarioenabled ? clickToDisable : clickToEnable;
            p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                    LanguageTranslator.translate("scenariomenu.display-format",
                            scenarioenabled ? enabled : disabled,
                            LanguageTranslator.translate("scenario." + scenario)
                    ),
                    "/manhunt scenarios " + scenario + " --page " + page,
                    scenarioenabledstring + "\n&7Class name: " + availables.get(scenario).getSimpleName()
            ));
        }
    }
}
