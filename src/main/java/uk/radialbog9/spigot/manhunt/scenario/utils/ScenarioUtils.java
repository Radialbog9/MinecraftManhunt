package uk.radialbog9.spigot.manhunt.scenario.utils;

import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;

public class ScenarioUtils {
    public static boolean isScenarioEnabled(Object instance) {
        String scenarioName = instance.getClass().getAnnotation(Scenario.class).value();
        return GameManager.getGame().isGameStarted() && GameManager.getGame().getActiveScenarios().contains(scenarioName);
    }
}
