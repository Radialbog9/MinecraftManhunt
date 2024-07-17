/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.template;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.language.LanguageTranslator;

public abstract class SwapHealthTemplate extends SwapScenarioTemplate {
    @Override
    public void swap(Player player1, Player player2) {
        double player1Health = player1.getHealth();
        double player2Health = player2.getHealth();

        player1.setHealth(Math.min(player2Health, 20)); // TODO account for attributes?
        player2.setHealth(Math.min(player1Health, 20));

        player1.sendMessage(LanguageTranslator.translate("health-swap", player2.getDisplayName()));
        player2.sendMessage(LanguageTranslator.translate("health-swap", player1.getDisplayName()));
    }
}
