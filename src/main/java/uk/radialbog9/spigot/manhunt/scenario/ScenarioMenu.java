/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;
import uk.radialbog9.spigot.manhunt.utils.Utils;

import java.util.HashMap;

public class ScenarioMenu {
    private static final int PAGE_LENGTH  = 5;

    public static void displayMenu(Player p, int page) {
        p.sendMessage(LanguageTranslator.translate("scenariomenu.title"));

        String enabled = LanguageTranslator.translate("scenariomenu.enabled");
        String disabled = LanguageTranslator.translate("scenariomenu.disabled");
        String clickToEnable = LanguageTranslator.translate("scenariomenu.click-to-enable");
        String clickToDisable = LanguageTranslator.translate("scenariomenu.click-to-disable");

        HashMap<String, Class<?>> availables = Manhunt.getScenarioLoader().getAvailableScenarios();

        int totalValues = availables.size();
        int totalPages = (int) Math.ceil((double) totalValues / PAGE_LENGTH);
        if (page < 1 || page > totalPages) {
            p.sendMessage(LanguageTranslator.translate("scenariomenu.invalid-page"));
            return;
        }

        int startIndex = (page - 1) * PAGE_LENGTH;
        int endIndex = Math.min(page * PAGE_LENGTH, totalValues);

        for (int i = startIndex; i < endIndex; i++) {
            String scenario = (String) availables.keySet().toArray()[i];
            boolean scenarioenabled = GameManager.getGame().getActiveScenarios().contains(scenario);
            String scenarioenabledstring = scenarioenabled ? clickToDisable : clickToEnable;
            p.spigot().sendMessage(Utils.genTextComponentRunCommand(
                    LanguageTranslator.translate("scenariomenu.display-format",
                            scenarioenabled ? enabled : disabled,
                            LanguageTranslator.translate("scenario." + scenario)
                    ),
                    "/manhunt scenarios toggle " + scenario + " --page " + page,
                    scenarioenabledstring + "\n&7Class name: " + availables.get(scenario).getSimpleName()
            ));
        }

        p.sendMessage(LanguageTranslator.translate("scenariomenu.page", String.valueOf(page), String.valueOf(totalPages)));
        boolean hasPreviousPage = page > 1;
        boolean hasNextPage = page < totalPages;
        ComponentBuilder component = new ComponentBuilder();
        if(hasPreviousPage) {
            component.append(Utils.genTextComponentRunCommand(
                    LanguageTranslator.translate("scenariomenu.previous-page"),
                    "/manhunt scenarios --page " + (page - 1),
                    LanguageTranslator.translate("scenariomenu.click-to-previous-page")
            ));
        } else {
            component.append(LanguageTranslator.translate("scenariomenu.no-page"));
        }
        component.append(new TextComponent(" "));
        if(hasNextPage) {
            component.append(Utils.genTextComponentRunCommand(
                    LanguageTranslator.translate("scenariomenu.next-page"),
                    "/manhunt scenarios --page " + (page + 1),
                    LanguageTranslator.translate("scenariomenu.click-to-next-page")
            ));
        } else {
            component.append(LanguageTranslator.translate("scenariomenu.no-page"));
        }
        p.spigot().sendMessage(component.create());
    }
}
