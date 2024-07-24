/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import com.google.gson.Gson;
import org.apache.commons.lang3.ClassUtils;
import uk.radialbog9.spigot.manhunt.Manhunt;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfigurable;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

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

    /**
     * Loads the configuration for the scenario,
     * the intent being to add it to the configuration file if it doesn't exist.
     * @param scenarioName The name of the scenario
     * @param scenarioClass The class of the scenario
     * @throws NoSuchMethodException If the method doesn't exist
     * @throws InvocationTargetException If the method can't be invoked
     * @throws InstantiationException If the class can't be instantiated
     * @throws IllegalAccessException If the class can't be accessed
     */
    public static void loadConfigFromScenario(String scenarioName, Class<?> scenarioClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        getScenarioInstance(scenarioName, scenarioClass);
    }

    /**
     * Creates the instance of the scenario and sets the config if it exists.
     * @param scenarioName The name of the scenario
     * @param scenarioClass The class of the scenario
     * @return The instance of the scenario
     * @throws NoSuchMethodException If the method doesn't exist
     * @throws InvocationTargetException If the method can't be invoked
     * @throws InstantiationException If the class can't be instantiated
     * @throws IllegalAccessException If the class can't be accessed
     */
    public static Object getScenarioInstance(String scenarioName, Class<?> scenarioClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (ClassUtils.getAllInterfaces(scenarioClass).contains(ScenarioConfigurable.class)) {
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
            return scenarioConfigurable;
        }
        return scenarioClass.getDeclaredConstructor().newInstance();
    }

    /**
     * Load the configuration for all scenarios
     */
    public static void loadConfigAllScenarios() {
        for (String scenarioName : Manhunt.getScenarioLoader().getAvailableScenarios().keySet()) {
            try {
                ScenarioUtils.loadConfigFromScenario(scenarioName, Manhunt.getScenarioLoader().getAvailableScenarios().get(scenarioName));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                Manhunt.getInstance().getLogger().log(Level.WARNING, "Can't load scenario " + scenarioName + " configuration because the class wouldn't load!");
                Manhunt.getInstance().getLogger().log(Level.WARNING, e.getMessage());
            }
        }
    }
}
