/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import com.google.gson.Gson;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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

    public static String toConfig(ScenarioConfiguration config) {
        Gson gson = new Gson();
        return gson.toJson(config);
    }

    public static ScenarioConfiguration fromConfig(String config, Class<? extends ScenarioConfiguration> clazz) {
        return new Gson().fromJson(config, clazz);
    }

    public static void loadConfigFromScenario(String scenarioName, Class<?> scenarioClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (Arrays.asList(scenarioClass.getInterfaces()).contains(ScenarioConfigurable.class)) {
            ScenarioConfigurable scenarioConfigurable = (ScenarioConfigurable) scenarioClass.getDeclaredConstructor().newInstance();
            String configJson = Manhunt.getInstance().getManhuntConfiguration().scenarios.get(scenarioName);
            ScenarioConfiguration config = ScenarioUtils.fromConfig(configJson, scenarioConfigurable.getConfig().getClass());
            if(config != null) {
                // Set the config if it exists
                scenarioConfigurable.setConfig(config);
            } else {
                Manhunt.getInstance().getManhuntConfiguration().scenarios.put(scenarioName, ScenarioUtils.toConfig(scenarioConfigurable.getConfig()));
            }
            scenarioConfigurable.setConfig(config);
        }
    }

    public static void loadConfigAllScenarios() {
        for (String scenarioName : Manhunt.getScenarioLoader().getAvailableScenarios().keySet()) {
            try {
                ScenarioUtils.loadConfigFromScenario(scenarioName, Manhunt.getScenarioLoader().getAvailableScenarios().get(scenarioName));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
