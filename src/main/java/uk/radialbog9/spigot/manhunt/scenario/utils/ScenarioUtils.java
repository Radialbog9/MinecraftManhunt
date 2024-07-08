/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.utils;

import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;

public class ScenarioUtils {
    /**
     * Check if a scenario is enabled and the game is started
     * @param instance The instance of the scenario
     * @return True if the scenario is enabled and the game is started
     */
    public static boolean isScenarioEnabled(Object instance) {
        String scenarioName = instance.getClass().getAnnotation(Scenario.class).value();
        return GameManager.getGame().isGameStarted() && GameManager.getGame().getActiveScenarios().contains(scenarioName);
    }
}
