/*
 * Copyright (c) 2020-2024 Radialbog9/TheJoeCoder and contributors.
 * You are allowed to use this code under the GPL3 license, which allows
 * commercial use, distribution, modification, and licensed works,
 * providing that you distribute your code under the same or similar license.
 */

package uk.radialbog9.spigot.manhunt.scenario.scenarios;

import org.bukkit.entity.Player;
import uk.radialbog9.spigot.manhunt.game.GameManager;
import uk.radialbog9.spigot.manhunt.scenario.*;
import uk.radialbog9.spigot.manhunt.scenario.template.NoFallScenarioTemplate;

import java.util.List;

@Scenario("HUNTER_NO_FALL")
@ScenarioListener
@SuppressWarnings("unused")
public class HunterNoFallScenario extends NoFallScenarioTemplate {
    @Override
    public List<Player> getPlayerSet() {
        return GameManager.getGame().getHunters();
    }
}
