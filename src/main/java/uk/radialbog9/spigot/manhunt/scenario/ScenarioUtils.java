/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.types.ScenarioBase;

import java.util.Map;

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

    public static Object getConfigValue(ScenarioBase obj, String key) {
        // Get scenario name from annotation
        String scenarioName = obj.getClass().getAnnotation(Scenario.class).value();

        // Get the scenario config
        Map<String, Object> cfg = Manhunt.getInstance().getConfig().scenarios.get(scenarioName);

        // If the config is null, set the default config and return the default value
        if (cfg == null) {
            Manhunt.getInstance().getConfig().scenarios.put(scenarioName, obj.getDefaultConfig());
            return obj.getDefaultConfig().get(key);
        }

        // Return the value from the config
        return cfg.getOrDefault(key, obj.getDefaultConfig().get(key));
    }
}
