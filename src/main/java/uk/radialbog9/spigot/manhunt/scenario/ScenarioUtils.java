/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario;

import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.config.ScenarioConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        Method[] methods = config.getClass().getDeclaredMethods();
        StringBuilder builder = new StringBuilder();
        for(Method method : methods) {
            if(method.getName().startsWith("get")) {
                try {
                    builder.append(method.getName().substring(3)).append(":").append(method.invoke(config));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public static ScenarioConfiguration fromConfig(String config, ScenarioConfiguration instance) {
        String[] lines = config.split(";");
        for(String line : lines) {
            String[] parts = line.split(":");
            try {
                Field field = instance.getClass().getField(parts[0]);
                field.setAccessible(true);
                field.set(instance, parts[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
