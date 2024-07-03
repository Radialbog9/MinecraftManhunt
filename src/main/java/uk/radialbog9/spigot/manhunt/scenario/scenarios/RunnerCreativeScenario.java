/*
 * Copyright (c) 2020-2023 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.Scenario;
import uk.radialbog9.spigot.manhunt.scenario.ScenarioRunnable;
import uk.radialbog9.spigot.manhunt.scenario.utils.template.CreativeScenarioTemplate;

import java.util.List;

@Scenario("RUNNER_CREATIVE")
@ScenarioRunnable
@SuppressWarnings({"unused"})
public class RunnerCreativeScenario extends CreativeScenarioTemplate {
    @Override
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getRunners();
    }
}

